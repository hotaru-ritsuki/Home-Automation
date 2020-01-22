package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.config.DeviceCacheConfig;
import com.softserve.lv460.device.config.PropertiesConfig;
import com.softserve.lv460.device.constant.ExceptionMassages;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.repositiry.DeviceDataRepository;
import com.softserve.lv460.device.service.DeviceDataService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Service
public class DeviceDataServiceImpl implements DeviceDataService {
  private ConcurrentLinkedQueue<DeviceData> batch;
  private DeviceDataRepository deviceDataRepository;
  private DeviceCacheConfig deviceCacheConfig;
  private PropertiesConfig propertiesConfig;

  public DeviceDataServiceImpl(DeviceDataRepository deviceDataRepository, DeviceCacheConfig deviceCacheConfig, PropertiesConfig propertiesConfig) {
    batch = new ConcurrentLinkedQueue<>();
    this.deviceDataRepository = deviceDataRepository;
    this.deviceCacheConfig = deviceCacheConfig;
    this.propertiesConfig = propertiesConfig;
  }


  @Override
  public void save(DeviceData deviceData) throws ExecutionException {
    DeviceData validData = deviceCacheConfig.validateData(deviceData);
    addToBatch(validData);
  }

  @Override
  public DeviceData getLastByUuId(String uuId) {
    return deviceDataRepository.findFirstByUuIdOrderByTimestampAsc(uuId)
            .orElseThrow(() -> new IllegalArgumentException(String.format
                    (ExceptionMassages.DEVICE_DATA_NOT_FOUND_BY_UUID, uuId)));
  }


  private void addToBatch(DeviceData validDeviceData) {
    batch.add(validDeviceData);
    if (batch.size() == propertiesConfig.getBatchSize()) {
      deviceDataRepository.saveAll(batch);
      batch.clear();
    }
  }

  @PostConstruct
  private void saveAfterDelay() {
    final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    ses.scheduleWithFixedDelay(() -> {
      if (!batch.isEmpty()) {
        deviceDataRepository.saveAll(batch);
        batch.clear();
      }
    }, 0, propertiesConfig.getBatchTime(), TimeUnit.SECONDS);
  }


}

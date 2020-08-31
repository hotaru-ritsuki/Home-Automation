package com.ritsuki.device.service.impl;

import com.ritsuki.device.service.DeviceDataService;
import com.ritsuki.device.config.PropertiesConfig;
import com.ritsuki.device.config.cache.DeviceCacheConfig;
import com.ritsuki.device.constant.ExceptionMassages;
import com.ritsuki.device.document.DeviceData;
import com.ritsuki.device.repository.DeviceDataRepository;
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

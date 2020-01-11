package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.config.DeviceCacheConfig;
import com.softserve.lv460.device.config.PropertiesConfig;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.repositiry.DeviceDataRepository;
import com.softserve.lv460.device.service.DeviceDataService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Service
public class DeviceDataServiceImpl implements DeviceDataService {
  private ConcurrentLinkedQueue<DeviceData> batch = new ConcurrentLinkedQueue<>();
  private DeviceDataRepository deviceDataRepository;
  private DeviceCacheConfig deviceCacheConfig;
  private PropertiesConfig propertiesConfig;

  public DeviceDataServiceImpl(DeviceDataRepository deviceDataRepository, DeviceCacheConfig deviceCacheConfig, PropertiesConfig propertiesConfig) {
    this.deviceDataRepository = deviceDataRepository;
    this.deviceCacheConfig = deviceCacheConfig;
    this.propertiesConfig = propertiesConfig;
  }


  @Override
  public void save(DeviceData deviceData) throws ExecutionException {
    addToBatch(deviceCacheConfig.validateData(deviceData));
  }

  private void addToBatch(DeviceData ValidDeviceData) {
    batch.add(ValidDeviceData);
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

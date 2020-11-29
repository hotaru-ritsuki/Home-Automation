package com.ritsuki.device.service.impl;

import com.ritsuki.device.service.DeviceDataService;
import com.ritsuki.device.config.PropertiesConfig;
import com.ritsuki.device.config.cache.DeviceCacheConfig;
import com.ritsuki.device.constant.ExceptionMessages;
import com.ritsuki.device.document.DeviceData;
import com.ritsuki.device.repository.DeviceDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.concurrent.*;

@Service("deviceDataService")
@RequiredArgsConstructor
public class DeviceDataServiceImpl implements DeviceDataService {

  private final ConcurrentLinkedQueue<DeviceData> batch = new ConcurrentLinkedQueue<>();
  private final DeviceDataRepository deviceDataRepository;
  private final DeviceCacheConfig deviceCacheConfig;
  private final PropertiesConfig propertiesConfig;

  @Override
  public void save(DeviceData deviceData) throws ExecutionException {
    DeviceData validData = deviceCacheConfig.validateData(deviceData);
    addToBatch(validData);
  }

  @Override
  public DeviceData getLastByUuId(String uuId) {
    return deviceDataRepository.findFirstByUuIdOrderByTimestampAsc(uuId)
            .orElseThrow(() -> new IllegalArgumentException(String.format
                    (ExceptionMessages.DEVICE_DATA_NOT_FOUND_BY_UUID, uuId)));
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
      if (!CollectionUtils.isEmpty(batch)) {
        deviceDataRepository.saveAll(batch);
        batch.clear();
      }
    }, 0, propertiesConfig.getBatchTime(), TimeUnit.SECONDS);
  }


}

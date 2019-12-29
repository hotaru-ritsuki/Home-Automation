package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.config.DeviceCacheConfig;
import com.softserve.lv460.device.constant.DataServiceConstants;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.exceptions.DeviceNotRegisteredException;
import com.softserve.lv460.device.exceptions.Massages;
import com.softserve.lv460.device.repositiry.DeviceDataRepository;
import com.softserve.lv460.device.service.DeviceDataService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@AllArgsConstructor
public class DeviceDataServiceImpl implements DeviceDataService {
  private List<DeviceData> batch = new ArrayList<>();
  private DeviceDataRepository deviceDataRepository;
  private DeviceCacheConfig deviceCacheConfig;

  @Override
  public void save(DeviceData deviceData) throws ExecutionException {
    if (deviceCacheConfig.isKeyValid(deviceData.getUuId())) {
      addToBatch(deviceData);
    } else throw new DeviceNotRegisteredException(Massages.DEVICE_NOT_REGISTERED);
  }

  private void addToBatch(DeviceData deviceData) {
    batch.add(deviceData);
    if (batch.size() == DataServiceConstants.BATCH_SIZE) {
      deviceDataRepository.saveAll(batch);
      batch.clear();
    }
  }

  @Bean
  private void timer() {
    final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
    ses.scheduleWithFixedDelay(() -> {
      if (!batch.isEmpty()) {
        deviceDataRepository.saveAll(batch);
        batch.clear();
      }
    }, 0, DataServiceConstants.BATCH_TIME, TimeUnit.SECONDS);
  }


}

package com.softserve.lv460.device.config.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.softserve.lv460.device.config.PropertiesConfig;
import com.softserve.lv460.device.constant.ExceptionMassages;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.dto.device.LocalDeviceDto;
import com.softserve.lv460.device.exception.exceptions.DeviceNotRegisteredException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
public class DeviceCacheConfig {

  private final PropertiesConfig propertiesConfig;
  private LoadingCache<String, LocalDeviceDto> loadingCache;


  public DeviceCacheConfig(PropertiesConfig propertiesConfig) {
    this.propertiesConfig = propertiesConfig;
    this.loadingCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(propertiesConfig.getCacheExpiration(), TimeUnit.SECONDS)
            .build(
                    new CacheLoader<>() {
                      @Override
                      public LocalDeviceDto load(String uuId) throws IOException {
                        return getRegisteredDevice(uuId);
                      }
                    }
            );
  }


  public DeviceData validateData(DeviceData deviceData) throws ExecutionException {
    LocalDeviceDto registeredDeviceDto = loadingCache.get(deviceData.getUuId());
    deviceData.getData().put("locationId", registeredDeviceDto.getLocation().getId().toString());
    return deviceData;
  }


  private LocalDeviceDto getRegisteredDevice(String uuId) throws IOException {
    String localDeviceUrl = propertiesConfig.getMainApplicationHostName() + "/location-devices/";
    HttpEntity localDeviceEntity = getResponseEntity(localDeviceUrl + uuId);
    return new ObjectMapper().readValue(localDeviceEntity.getContent(), LocalDeviceDto.class);
  }


  private HttpEntity getResponseEntity(String url) throws IOException {
    CloseableHttpResponse response = httpClient().execute(new HttpGet(url));
    if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
      return response.getEntity();
    }
    throw new DeviceNotRegisteredException(ExceptionMassages.DEVICE_NOT_REGISTERED);
  }


  @Bean
  CloseableHttpClient httpClient() {
    return HttpClients.createDefault();
  }
}

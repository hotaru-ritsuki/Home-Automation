package com.softserve.lv460.device.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.softserve.lv460.device.constant.ExceptionMassages;
import com.softserve.lv460.device.document.DeviceData;
import com.softserve.lv460.device.dto.deviceDto.DeviceDto;
import com.softserve.lv460.device.exception.exceptions.DeviceNotRegisteredException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class DeviceCacheConfig {

  private final PropertiesConfig propertiesConfig;
  private LoadingCache<String, DeviceDto> loadingCache;


  public DeviceCacheConfig(PropertiesConfig propertiesConfig) {
    this.propertiesConfig = propertiesConfig;
    this.loadingCache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(propertiesConfig.getCacheExpiration(), TimeUnit.MINUTES)
            .build(
                    new CacheLoader<String, DeviceDto>() {
                      @Override
                      public DeviceDto load(String uuId) throws IOException {
                        return getRegisteredDevice(uuId);
                      }
                    }
            );
  }


  public DeviceData validateData(DeviceData deviceData) throws ExecutionException {
    DeviceDto deviceDto = loadingCache.get(deviceData.getUuId());
    deviceData.getData().put("locationId", deviceDto.getLocation().getId().toString());
    return deviceData;
  }


  private DeviceDto getRegisteredDevice(String uuId) throws IOException {
    String url = propertiesConfig.getMainApplicationHostName() + "/location-devices/" + uuId;
    CloseableHttpResponse response = httpClient().execute(new HttpGet(url));
    //!!!!!
    if (response.getStatusLine().getStatusCode() < HttpStatus.INTERNAL_SERVER_ERROR.value()) {
      HttpEntity entity = response.getEntity();
      return new ObjectMapper().readValue(entity.getContent(), DeviceDto.class);
    }
    throw new DeviceNotRegisteredException(ExceptionMassages.DEVICE_NOT_REGISTERED);
  }

  @Bean
  CloseableHttpClient httpClient() {
    return HttpClients.createDefault();
  }
}

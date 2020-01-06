package com.softserve.lv460.device.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.softserve.lv460.device.dto.DeviceDto;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Configuration
@EnableCaching
public class DeviceCacheConfig {

  @Autowired
  private PropertiesConfig propertiesConfig;


  private LoadingCache<String, Boolean> loadingCache = CacheBuilder
          .newBuilder()
          .expireAfterWrite(1, TimeUnit.MINUTES)
          .build(
                  new CacheLoader<String, Boolean>() {
                    @Override
                    public Boolean load(String key) throws Exception {
                      List<String> registeredDevicesUu = getRegisteredDevicesUu();
                      return registeredDevicesUu.contains(key);
                    }
                  }
          );


  public Boolean isKeyValid(String key) throws ExecutionException {
    return loadingCache.get(key);
  }


  private List<String> getRegisteredDevicesUu() throws Exception {
    String url = propertiesConfig.getMainApplicationHostName() + "/location-device";
    CloseableHttpResponse response = httpClient().execute(new HttpGet(url));
    HttpEntity entity = response.getEntity();
    ObjectMapper mapper = new ObjectMapper();
    List<DeviceDto> devices = mapper.readValue(entity.getContent(),
            mapper.getTypeFactory().constructCollectionType(List.class, DeviceDto.class));
    return devices.stream().map(DeviceDto::getUuid).collect(Collectors.toList());
  }

  @Bean
  CloseableHttpClient httpClient() {
    return HttpClients.createDefault();
  }
}

package com.ritsuki.device.config.cache;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ritsuki.device.config.PropertiesConfig;
import com.ritsuki.device.constant.ExceptionMessages;
import com.ritsuki.device.document.DeviceData;
import com.ritsuki.device.dto.device.LocalDeviceDTO;
import com.ritsuki.device.exception.exceptions.DeviceNotRegisteredException;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static com.google.common.cache.CacheBuilder.newBuilder;

@Configuration
public class DeviceCacheConfig {

    private final CloseableHttpClient closeableHttpClient;
    private final PropertiesConfig propertiesConfig;
    private final LoadingCache<String, LocalDeviceDTO> loadingCache;

    public DeviceCacheConfig(PropertiesConfig propertiesConfig, CloseableHttpClient closeableHttpClient) {
        this.closeableHttpClient = closeableHttpClient;
        this.propertiesConfig = propertiesConfig;
        this.loadingCache = newBuilder()
                .expireAfterWrite(propertiesConfig.getCacheExpiration(), TimeUnit.SECONDS)
                .build(
                        new CacheLoader<>() {
                            @Override
                            public LocalDeviceDTO load(String uuId) throws IOException {
                                return getRegisteredDevice(uuId);
                            }
                        }
                );
    }


    public DeviceData validateData(DeviceData deviceData) throws ExecutionException {
        LocalDeviceDTO registeredDeviceDto = loadingCache.get(deviceData.getUuId());
        deviceData.getData().put("locationId", registeredDeviceDto.getLocation().getId().toString());
        return deviceData;
    }

    public LocalDeviceDTO getFromCache(String uuid) throws ExecutionException {
        return loadingCache.get(uuid);
    }

    private LocalDeviceDTO getRegisteredDevice(String uuId) throws IOException {
        String localDeviceUrl = propertiesConfig.getMainApplicationHostName() + "/location-devices/";
        HttpEntity localDeviceEntity = getResponseEntity(localDeviceUrl + uuId);
        return new ObjectMapper().readValue(localDeviceEntity.getContent(), LocalDeviceDTO.class);
    }

    private HttpEntity getResponseEntity(String url) throws IOException {
        CloseableHttpResponse response = closeableHttpClient.execute(new HttpGet(url));
        if (response.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
            return response.getEntity();
        }
        throw new DeviceNotRegisteredException(ExceptionMessages.DEVICE_NOT_REGISTERED);
    }

}

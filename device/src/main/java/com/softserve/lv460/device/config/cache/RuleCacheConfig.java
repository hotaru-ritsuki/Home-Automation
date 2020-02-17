package com.softserve.lv460.device.config.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.softserve.lv460.device.config.PropertiesConfig;
import com.softserve.lv460.device.dto.rule.RuleDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class RuleCacheConfig {
  private final PropertiesConfig propertiesConfig;
  private final CloseableHttpClient httpClient;
  private LoadingCache<String, List<RuleDto>> cache;

  public RuleCacheConfig(PropertiesConfig propertiesConfig, CloseableHttpClient httpClient) {
    this.propertiesConfig = propertiesConfig;
    this.httpClient = httpClient;
    this.cache = CacheBuilder
            .newBuilder()
            .expireAfterWrite(this.propertiesConfig.getCacheExpiration(), TimeUnit.SECONDS)
            .build(
                    new CacheLoader<String, List<RuleDto>>() {
                      @Override
                      public List<RuleDto> load(String uuId) throws IOException {
                        return getRules(uuId);
                      }
                    }
            );
  }

  public List<RuleDto> getCache(String uuId) {
    try {
      return cache.get(uuId);
    } catch (Exception e) {
      log.error(e.getLocalizedMessage());
      return Collections.emptyList();
    }
  }

  private List<RuleDto> getRules(String uuId) throws IOException {
    String deviceRuleUrl = propertiesConfig.getMainApplicationHostName() + "/rules/device/" + uuId;
    CloseableHttpResponse response = httpClient.execute(new HttpGet(deviceRuleUrl));
    return new ObjectMapper().readValue(response.getEntity().getContent(),
            new TypeReference<>() {
            });
  }
}

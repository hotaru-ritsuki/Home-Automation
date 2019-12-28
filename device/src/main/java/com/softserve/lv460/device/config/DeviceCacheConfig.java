package com.softserve.lv460.device.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableCaching
public class DeviceCacheConfig {
  @Bean
  public CacheManager cacheManager(){
    return new EhCacheCacheManager(cacheManagerFactory().getObject());
  }

  @Bean
  public EhCacheManagerFactoryBean cacheManagerFactory(){
    EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
    factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
    factoryBean.setShared(true);
    return factoryBean
  }
}

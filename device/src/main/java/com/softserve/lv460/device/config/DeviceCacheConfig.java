package com.softserve.lv460.device.config;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
@EnableCaching
public class DeviceCacheConfig {
  private LoadingCache<String,String> loadingCache = CacheBuilder.newBuilder().build(
          new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
              return getCache();
            }
          }
  );

  public String get(String key) throws ExecutionException {
   return loadingCache.get(key);
  }


  public String getCache(){
    System.out.println("gg");
    return "hello";
  }
}

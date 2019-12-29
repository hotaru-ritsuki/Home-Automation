package com.softserve.lv460.device.config;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutionException;

@Configuration
@EnableCaching
public class DeviceCacheConfig {
  private static final String API_URL =  "localhost:8080/";
  
  private LoadingCache<String,String> loadingCache = CacheBuilder.newBuilder().build(
          new CacheLoader<String, String>() {
            @Override
            public String load(String s) throws Exception {
              CloseableHttpClient client = HttpClients.createDefault();
              CloseableHttpResponse responce = client.execute(new HttpGet());
              responce.ge
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

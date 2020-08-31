package com.ritsuki.application;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ConfigurationPropertiesScan("com.softserve.lv460.application.constant")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);

  }
  @Bean
  BCryptPasswordEncoder bCryptPasswordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public CloseableHttpClient httpClient() {
    return HttpClientBuilder.create().build();
  }
}

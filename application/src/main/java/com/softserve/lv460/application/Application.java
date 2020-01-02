package com.softserve.lv460.application;

//import org.flywaydb.core.Flyway;
import com.softserve.lv460.application.security.constants.SecurityConstants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@ConfigurationPropertiesScan("com.softserve.lv460.application.security.constants")
public class Application {

  public static void main(String[] args) {

    SpringApplication.run(Application.class, args);
  }

}

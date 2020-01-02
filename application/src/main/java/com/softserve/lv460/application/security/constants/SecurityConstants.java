package com.softserve.lv460.application.security.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Data
@Component
@PropertySource("classpath:application.properties")
public class SecurityConstants {
  @Value("${jwtconf.SECRET}")
  public String SECRET;

  @Value("${jwtconf.EXPIRATIONTIME}")
  public long EXPIRATIONTIME; // 1 day

  @Value("${jwtconf.TOKEN_PREFIX}")
  public String TOKEN_PREFIX;

  @Value("${jwtconf.HEADER_STRING}")
  public String HEADER_STRING;

  @Value("${jwtconf.SIGN_UP_URL}")
  public String SIGN_UP_URL;
}
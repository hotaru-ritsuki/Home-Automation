package com.softserve.lv460.application.security.config;

import com.softserve.lv460.application.constant.SecurityConfigProperties;
import com.softserve.lv460.application.security.filters.APIKeyAuthFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@AllArgsConstructor
@Order(1)
public class APISecurityConfig extends WebSecurityConfigurerAdapter {
private SecurityConfigProperties securityConfigProperties;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
  APIKeyAuthFilter filter = new APIKeyAuthFilter(securityConfigProperties.getHeader());
        filter.setAuthenticationManager(new AuthenticationManager() {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      String principal = (String) authentication.getPrincipal();
      if (!principalRequestValue.equals(principal)) {
        authentication.setAuthenticated(true);
      }
      return authentication;
    }
  });
        http.antMatcher("/api/**").
  csrf().disable().
  sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).
  and().addFilter(filter).authorizeRequests().anyRequest().authenticated();
}}
}

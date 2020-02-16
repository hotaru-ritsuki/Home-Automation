package com.softserve.lv460.device.security;

import com.softserve.lv460.device.security.filters.JwtAuthenticationFilter;
import com.softserve.lv460.device.security.jwt.JwtAuthenticationEntryPoint;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
  securedEnabled = true,
  jsr250Enabled = true,
  prePostEnabled = true
)
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  private SecurityConfigProperties securityConfigProperties;
  private JwtAuthenticationEntryPoint unauthorizedHandler;
  private JwtAuthenticationFilter jwtAuthenticationFilter;

  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/v2/api-docs",
      "/configuration/ui",
      "/swagger-resources/**",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**",
      securityConfigProperties.getLocationUrl(),
      securityConfigProperties.getRulesUrl(),
      securityConfigProperties.getTelegramUrl()
    );
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable().exceptionHandling()
      .authenticationEntryPoint(unauthorizedHandler)
      .and().authorizeRequests()
      .antMatchers(securityConfigProperties.getSignUpUrl()).permitAll()
      .antMatchers(securityConfigProperties.getSignInUrl()).permitAll()
      .antMatchers(securityConfigProperties.getRefreshTokensUrl()).permitAll()
      .antMatchers(securityConfigProperties.getVerifyEmail()).permitAll()
      .antMatchers(securityConfigProperties.getResendRegistrationToken()).permitAll()
      .antMatchers(securityConfigProperties.getRestorePasswordUrl()).permitAll()
      .antMatchers(securityConfigProperties.getRestoreUrl()).permitAll()
      .anyRequest().authenticated()
      .and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}
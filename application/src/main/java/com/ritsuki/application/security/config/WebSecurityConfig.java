package com.ritsuki.application.security.config;

import com.ritsuki.application.constant.SecurityConfigProperties;
import com.ritsuki.application.security.filters.JWTAuthenticationFilter;
import com.ritsuki.application.security.jwt.JWTAuthenticationEntryPoint;
import com.ritsuki.application.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
@AllArgsConstructor
@Order(1000)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userDetailsService;
  private final SecurityConfigProperties securityConfigProperties;
  private final JWTAuthenticationEntryPoint unauthorizedHandler;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JWTAuthenticationFilter jwtAuthenticationFilter;

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
            .antMatchers(securityConfigProperties.getResendRegistrationTokenUrl()).permitAll()
            .antMatchers(securityConfigProperties.getRestorePasswordUrl()).permitAll()
            .antMatchers(securityConfigProperties.getRestoreUrl()).permitAll()
            .antMatchers(securityConfigProperties.getConfirmRegistrationUrl()).permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

}


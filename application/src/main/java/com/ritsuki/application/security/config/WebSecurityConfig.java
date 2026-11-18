package com.ritsuki.application.security.config;

import com.ritsuki.application.constant.SecurityConfigProperties;
import com.ritsuki.application.security.filters.JWTAuthenticationFilter;
import com.ritsuki.application.security.jwt.JWTAuthenticationEntryPoint;
import com.ritsuki.application.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
public class WebSecurityConfig {

  private final UserDetailsServiceImpl userDetailsService;
  private final SecurityConfigProperties securityConfigProperties;
  private final JWTAuthenticationEntryPoint unauthorizedHandler;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JWTAuthenticationFilter jwtAuthenticationFilter;

  // Only one catch-all filter chain may exist; MockAuthConfiguration provides it when mock auth is on.
  @Bean
  @ConditionalOnProperty(value = "security.useMockAuth", havingValue = "false", matchIfMissing = true)
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().requestMatchers(
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            securityConfigProperties.getLocationUrl(),
            securityConfigProperties.getRulesUrl(),
            securityConfigProperties.getTelegramUrl()
    );
  }

  @Bean
  @Order(1000)
  @ConditionalOnProperty(value = "security.useMockAuth", havingValue = "false", matchIfMissing = true)
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors(cors -> {})
            .csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(securityConfigProperties.getSignUpUrl()).permitAll()
                    .requestMatchers(securityConfigProperties.getSignInUrl()).permitAll()
                    .requestMatchers(securityConfigProperties.getRefreshTokensUrl()).permitAll()
                    .requestMatchers(securityConfigProperties.getResendRegistrationTokenUrl()).permitAll()
                    .requestMatchers(securityConfigProperties.getRestorePasswordUrl()).permitAll()
                    .requestMatchers(securityConfigProperties.getRestoreUrl()).permitAll()
                    .requestMatchers(securityConfigProperties.getConfirmRegistrationUrl()).permitAll()
                    .anyRequest().authenticated())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
    provider.setPasswordEncoder(bCryptPasswordEncoder);
    return new ProviderManager(provider);
  }
}

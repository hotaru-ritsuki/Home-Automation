package com.ritsuki.application.security.filters;

import com.ritsuki.application.constant.SecurityConfigProperties;
import com.ritsuki.application.security.jwt.JWTTokenProvider;
import com.ritsuki.application.security.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
@AllArgsConstructor
@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
  private JWTTokenProvider tokenProvider;
  private UserDetailsServiceImpl customUserDetailsService;
  private SecurityConfigProperties securityConfigProperties;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    try {
      String jwt = request.getHeader(securityConfigProperties.getHeader());
      if (StringUtils.hasText(jwt) && tokenProvider.validateAccessToken(jwt)) {
        String email = tokenProvider.getEmailFromJWT(jwt);
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception ex) {
      log.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(request, response);
  }
}
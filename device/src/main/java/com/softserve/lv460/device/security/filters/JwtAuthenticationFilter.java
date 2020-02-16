package com.softserve.lv460.device.security.filters;

import com.softserve.lv460.device.security.SecurityConfigProperties;
import com.softserve.lv460.device.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private JwtTokenProvider tokenProvider;
  private SecurityConfigProperties securityConfigProperties;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
    try {
      //Get Header from request with key securityConfigProperties.getHeader()
      String jwt = request.getHeader(securityConfigProperties.getHeader());
      //encoding jwt token
      if (StringUtils.hasText(jwt) && tokenProvider.isTokenValid(jwt, securityConfigProperties.getSecret())) {
//        Long id = tokenProvider.getHomeIdFromJWT(jwt);
//        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    } catch (Exception ex) {
      log.error("Could not set user authentication in security context", ex);
    }

    filterChain.doFilter(request, response);
  }
}
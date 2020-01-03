package com.softserve.lv460.application.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.softserve.lv460.application.security.constants.SecurityConstants;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
  private SecurityConstants securityConstants;

  public JWTAuthorizationFilter(AuthenticationManager authManager, SecurityConstants securityConstants) {
    super(authManager);
    this.securityConstants = securityConstants;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest req,
                                  HttpServletResponse res,
                                  FilterChain chain) throws IOException, ServletException {
    String header = req.getHeader(securityConstants.HEADER_STRING);

    if (header == null || !header.startsWith(securityConstants.TOKEN_PREFIX)) {
      chain.doFilter(req, res);
      return;
    }

    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

    SecurityContextHolder.getContext().setAuthentication(authentication);
    chain.doFilter(req, res);
  }

  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
    String token = request.getHeader(securityConstants.HEADER_STRING);
    System.out.println(token);
    if (token != null) {
      // parse the token.
      System.out.println(JWT.require(Algorithm.HMAC512(securityConstants.SECRET.getBytes()))
              .build());
      String user = JWT.require(Algorithm.HMAC512(securityConstants.SECRET.getBytes()))
              .build()
              .verify(token.substring(7))
              .getSubject();

      if (user != null) {
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
      }
      return null;
    }
    return null;
  }
}
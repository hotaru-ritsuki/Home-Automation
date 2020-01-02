package com.softserve.lv460.application.security.filters;

        import com.auth0.jwt.JWT;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import com.softserve.lv460.application.entity.ApplicationUser;
        import com.softserve.lv460.application.security.constants.SecurityConstants;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.context.annotation.Import;
        import org.springframework.security.authentication.AuthenticationManager;
        import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
        import org.springframework.security.core.Authentication;
        import org.springframework.security.core.AuthenticationException;
        import org.springframework.security.core.userdetails.User;
        import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

        import javax.servlet.FilterChain;
        import javax.servlet.ServletException;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Date;

        import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private SecurityConstants securityConstants;
  private AuthenticationManager authenticationManager;
  public JWTAuthenticationFilter(AuthenticationManager authenticationManager,SecurityConstants securityConstants) {
    this.authenticationManager=authenticationManager;
    this.securityConstants=securityConstants;
  }


  @Override
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              HttpServletResponse res) throws AuthenticationException {
    try {
      ApplicationUser creds = new ObjectMapper()
              .readValue(req.getInputStream(), ApplicationUser.class);

      return authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      creds.getEmail(),
                      creds.getPassword(),
                      new ArrayList<>())
      );
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req,
                                          HttpServletResponse res,
                                          FilterChain chain,
                                          Authentication auth) throws IOException, ServletException {

    String token = JWT.create()
            .withSubject(((User) auth.getPrincipal()).getUsername())
            .withExpiresAt(new Date(System.currentTimeMillis() + securityConstants.EXPIRATIONTIME))
            .sign(HMAC512(securityConstants.SECRET.getBytes()));
    res.addHeader(securityConstants.HEADER_STRING, securityConstants.TOKEN_PREFIX+ token);
  }
}
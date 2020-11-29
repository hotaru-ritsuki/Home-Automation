package com.ritsuki.application.security.mock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritsuki.application.constant.SecurityConfigProperties;
import com.ritsuki.application.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(value = "security.useMockAuth", havingValue = "true")
public class MockAuthConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    SecurityConfigProperties securityConfigProperties;

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    @Autowired
    private MockAuthUser mockAuthUser;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(securityConfigProperties.getSignUpUrl()).permitAll()
                .antMatchers(securityConfigProperties.getSignInUrl()).permitAll()
                .antMatchers(securityConfigProperties.getRefreshTokensUrl()).permitAll()
                .antMatchers(securityConfigProperties.getResendRegistrationTokenUrl()).permitAll()
                .antMatchers(securityConfigProperties.getRestorePasswordUrl()).permitAll()
                .antMatchers(securityConfigProperties.getRestoreUrl()).permitAll()
                .antMatchers(securityConfigProperties.getConfirmRegistrationUrl()).permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.csrf().disable().authorizeRequests()
                .antMatchers("/**").authenticated()
                .antMatchers("/users/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler())
                .failureHandler(failureHandler())
                .and()
                .logout()
                .logoutUrl("users/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        for (Map.Entry<String, String> entry : mockAuthUser.getPasswords().entrySet()) {

            List<String> roles = mockAuthUser.getUsers().stream()
                    .filter(e -> e.getRoles().contains(Role.valueOf(entry.getKey())))
                    .findAny().get().getRoles().stream()
                    .map(r -> r.name()).collect(Collectors.toList());

            String[] itemsArray = roles.toArray(new String[roles.size()]);

            auth.inMemoryAuthentication().withUser(entry.getKey()).password("{noop}" + entry.getValue()).roles(itemsArray);
        }
    }

    private AuthenticationSuccessHandler successHandler() {
        ObjectMapper objectMapper = new ObjectMapper();
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.getWriter().append(objectMapper
                    .writeValueAsString(mockAuthUser.getUserByName(authentication.getName())));
            httpServletResponse.setStatus(200);
            httpServletResponse.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setStatus(401);
            httpServletResponse.getWriter().append("User doesn't exist");
            httpServletResponse.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        };
    }

    private LogoutSuccessHandler logoutSuccessHandler() {
        return (httpServletRequest, httpServletResponse, authentication) -> {
            httpServletResponse.setStatus(200);
            httpServletResponse.getWriter().append("Success logout");
            httpServletResponse.setHeader(CONTENT_TYPE, APPLICATION_JSON);
        };
    }
}

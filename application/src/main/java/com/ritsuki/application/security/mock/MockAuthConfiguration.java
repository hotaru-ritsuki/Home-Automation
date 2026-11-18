package com.ritsuki.application.security.mock;

import com.ritsuki.application.constant.SecurityConfigProperties;
import com.ritsuki.application.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@ConditionalOnProperty(value = "security.useMockAuth", havingValue = "true")
public class MockAuthConfiguration {

    @Autowired
    SecurityConfigProperties securityConfigProperties;

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";

    @Autowired
    private MockAuthUser mockAuthUser;

    @Bean
    public SecurityFilterChain mockSecurityFilterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(mockUsers())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(securityConfigProperties.getSignUpUrl()).permitAll()
                        .requestMatchers(securityConfigProperties.getSignInUrl()).permitAll()
                        .requestMatchers(securityConfigProperties.getRefreshTokensUrl()).permitAll()
                        .requestMatchers(securityConfigProperties.getResendRegistrationTokenUrl()).permitAll()
                        .requestMatchers(securityConfigProperties.getRestorePasswordUrl()).permitAll()
                        .requestMatchers(securityConfigProperties.getRestoreUrl()).permitAll()
                        .requestMatchers(securityConfigProperties.getConfirmRegistrationUrl()).permitAll()
                        .requestMatchers("/**").authenticated()
                        .requestMatchers("/users/**").permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(form -> form
                        .loginPage("/users/login")
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler())
                        .failureHandler(failureHandler()))
                .logout(logout -> logout
                        .logoutUrl("/users/logout")
                        .logoutSuccessHandler(logoutSuccessHandler())
                        .permitAll());
        return http.build();
    }

    private InMemoryUserDetailsManager mockUsers() {
        List<UserDetails> users = new ArrayList<>();
        for (Map.Entry<String, String> entry : mockAuthUser.getPasswords().entrySet()) {

            List<String> roles = mockAuthUser.getUsers().stream()
                    .filter(e -> e.getRoles().contains(Role.valueOf(entry.getKey())))
                    .findAny().get().getRoles().stream()
                    .map(r -> r.name()).collect(Collectors.toList());

            users.add(User.withUsername(entry.getKey())
                    .password("{noop}" + entry.getValue())
                    .roles(roles.toArray(new String[0]))
                    .build());
        }
        return new InMemoryUserDetailsManager(users);
    }

    private AuthenticationSuccessHandler successHandler() {
        ObjectMapper objectMapper = JsonMapper.builderWithJackson2Defaults().build();
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

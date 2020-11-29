package com.ritsuki.application.security.mock;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Import(MockAuthConfiguration.class)
@Configuration
public @interface EnableMockAuth {
}

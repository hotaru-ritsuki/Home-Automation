package com.ritsuki.application.controller;

import com.ritsuki.application.service.ApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.ALL_VALUE, produces = MediaType.ALL_VALUE)
public class HealthController {

    private final ApplicationUserService applicationUserService;

    private static final String HEALTHY = "healthy";
    private static final String ERROR = "error";

    @GetMapping("/health.html")
    public String healthCheck() {
        return applicationUserService.isDatabaseUp() ? HEALTHY : ERROR;
    }
}

package com.ritsuki.application.security.mock;

import com.ritsuki.application.entity.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api", consumes = APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class MockAuthController {

    @Autowired
    private MockAuthUser mockAuthUser;

    @GetMapping(value = "/me")
    public ApplicationUser some(Principal principal) {
        return mockAuthUser.getUserByName(principal.getName());
    }

    @GetMapping(value = "/principal-user")
    public Principal rest2(Principal principal) {
        return principal;
    }


}
package com.ritsuki.application.security.mock;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ritsuki.application.entity.ApplicationUser;
import com.ritsuki.application.exception.exceptions.NotFoundException;
import com.ritsuki.application.util.JacksonUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "security.mock")
@Data
public class MockAuthUser {

    private String users;
    private Map<String, String> passwords;

    public List<ApplicationUser> getUsers() {
        return JacksonUtil.deserialize(users, new TypeReference<List<ApplicationUser>>() {
        });
    }

    public Map<String, String> getPasswords() {
        return passwords;
    }

    public ApplicationUser getUserByName(String email) {
        return getUsers().stream().filter(e -> e.getEmail().equals(email)).findAny()
                .orElseThrow(() -> new NotFoundException("User not found with email " + email));
    }

}

package com.softserve.lv460.application.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception that we get when user trying to sign-in with bad email or password.
 *
 */
public class BadEmailOrPasswordException extends AuthenticationException {
    /**
     * Constructor.
     */
    public BadEmailOrPasswordException(String message) {
        super(message);
    }
}

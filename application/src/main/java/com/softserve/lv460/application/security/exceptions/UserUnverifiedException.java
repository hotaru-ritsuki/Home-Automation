package com.softserve.lv460.application.security.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Exception that we get when user trying to sign-in to account that has not verified email.
 *
 * @version 1.0
 */
public class UserUnverifiedException extends AuthenticationException {
    /**
     * Constructor.
     */
    public UserUnverifiedException(String message) {
        super(message);
    }
}

package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when user trying to verify email with token that has expired.
 *
 */
public class UserActivationEmailTokenExpiredException extends RuntimeException {
    /**
     * Generated javadoc, must be replaced with real one.
     */
    public UserActivationEmailTokenExpiredException(String message) {
        super(message);
    }
}

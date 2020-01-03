package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when user trying to sign-up with email that already registered.
 *
 */
public class UserAlreadyRegisteredException extends RuntimeException {
    /**
     * Generated javadoc, must be replaced with real one.
     */
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }
}

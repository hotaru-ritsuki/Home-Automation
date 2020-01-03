package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when user trying to refresh access token with bad refresh token.
 *
 */
public class BadRefreshTokenException extends RuntimeException {
    /**
     * Generated javadoc, must be replaced with real one.
     */
    public BadRefreshTokenException(String message) {
        super(message);
    }
}

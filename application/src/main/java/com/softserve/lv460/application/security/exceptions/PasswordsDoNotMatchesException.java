package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when user whan passwords don't matches.
 *
 * @version 1.0
 */
public class PasswordsDoNotMatchesException extends RuntimeException {
    /**
     * Constructor for PasswordsDoNotMatchesException.
     *
     * @param message - giving message.
     */
    public PasswordsDoNotMatchesException(String message) {
        super(message);
    }
}

package com.softserve.lv460.application.security.exceptions;

public class NotCurrentUserException extends RuntimeException {
    /**
     * Constructor.
     */
    public NotCurrentUserException(String message) {
        super(message);
    }
}

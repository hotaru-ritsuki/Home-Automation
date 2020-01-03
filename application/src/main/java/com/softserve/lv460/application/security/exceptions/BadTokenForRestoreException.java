package com.softserve.lv460.application.security.exceptions;

public class BadTokenForRestoreException extends RuntimeException {
    /**
     * Constructor for BadTokenForRestoreException.
     *
     * @param message - giving message.
     */
    public BadTokenForRestoreException(String message) {
        super(message);
    }
}

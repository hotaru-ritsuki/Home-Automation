package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when in some logic we have bad ID.
 *
 */
public class BadIdException extends RuntimeException {
    /**
     * Constructor.
     *
     * @param message {@link String}
     */
    public BadIdException(String message) {
        super(message);
    }
}

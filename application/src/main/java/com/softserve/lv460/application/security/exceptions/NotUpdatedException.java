package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when we try updating some object but such object not exist,
 * then we get {@link NotUpdatedException}.
 *
 * @version 1.0
 */
public class NotUpdatedException extends RuntimeException {
    /**
     * Constructor for NotUpdatedException.
     *
     * @param message - giving message.
     */
    public NotUpdatedException(String message) {
        super(message);
    }
}
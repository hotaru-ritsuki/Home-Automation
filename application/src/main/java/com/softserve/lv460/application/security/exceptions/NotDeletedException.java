package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when we try deleting some object but such object not exist,
 * then we get {@link NotDeletedException}.
 *
 * @version 1.0
 */
public class NotDeletedException extends RuntimeException {
    /**
     * Constructor for NotDeletedException.
     *
     * @param message - giving message.
     */
    public NotDeletedException(String message) {
        super(message);
    }
}
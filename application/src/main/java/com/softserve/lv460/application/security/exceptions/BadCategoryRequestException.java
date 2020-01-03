package com.softserve.lv460.application.security.exceptions;

/**
 * Exception that we get when user trying to save category with bad parameters.
 *
 */
public class BadCategoryRequestException extends RuntimeException {
    /**
     * Constructor for BadCategoryRequestException.
     *
     * @param message - giving message.
     */
    public BadCategoryRequestException(String message) {
        super(message);
    }
}

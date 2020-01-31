package com.softserve.lv460.application.exception.exceptions;

public class UserAlreadyActivated extends RuntimeException {
    public UserAlreadyActivated(String msg) {
        super(msg);
    }
}

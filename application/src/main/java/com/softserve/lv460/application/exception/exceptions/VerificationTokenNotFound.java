package com.softserve.lv460.application.exception.exceptions;

public class VerificationTokenNotFound extends RuntimeException {
    public VerificationTokenNotFound(String msg){
        super(msg);
    }
}

package com.best.company.exceptions;

public class AuthException extends ApiException{
    public AuthException(String message, String errorCode) {
        super(message, errorCode);
    }
}

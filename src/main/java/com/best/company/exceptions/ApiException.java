package com.best.company.exceptions;


import lombok.Getter;

public class ApiException extends RuntimeException{

    @Getter
    protected String errorCode;

    public ApiException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

package com.best.company.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 26.07.2023 18:05
 */

@Getter
@Setter
public abstract class AppGlobalException extends RuntimeException {

    String title;
    Object data;
    HttpStatus status;

    public AppGlobalException(String title, String message, HttpStatus status) {
        this(title, message, status, null);
    }

    public AppGlobalException(String title, HttpStatus status) {
        this(title, title, status, null);
    }

    public AppGlobalException(String title, String message, HttpStatus status, Object data) {
        super(message);
        this.title = title;
        this.status = status;
        this.data = data;
    }
}

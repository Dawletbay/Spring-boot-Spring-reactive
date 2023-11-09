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
public class BadRequestException extends AppGlobalException {

    public BadRequestException(String userMessage) {
        super(userMessage, userMessage, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String userMessage, String developerMessage) {
        super(userMessage, developerMessage, HttpStatus.BAD_REQUEST);
    }
}

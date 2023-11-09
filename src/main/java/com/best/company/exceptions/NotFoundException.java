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
public class NotFoundException extends AppGlobalException {

    public NotFoundException(String userMessage) {
        super(userMessage, userMessage, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String userMessage, String developerMessage) {
        super(userMessage, developerMessage, HttpStatus.NOT_FOUND);
    }
}

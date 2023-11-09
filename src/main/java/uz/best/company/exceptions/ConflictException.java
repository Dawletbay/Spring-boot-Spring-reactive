package uz.best.company.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 26.07.2023 18:05
 */

@Getter
@Setter
public class ConflictException extends AppGlobalException {

    public ConflictException(String userMessage) {
        super(userMessage, userMessage, HttpStatus.CONFLICT);
    }

    public ConflictException(String userMessage, String developerMessage) {
        super(userMessage, developerMessage, HttpStatus.CONFLICT);
    }
}

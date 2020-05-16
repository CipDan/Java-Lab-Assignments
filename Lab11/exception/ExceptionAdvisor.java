package com.example.lab11.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * A Rest Controller for handling errors.
 */
@RestControllerAdvice
public class ExceptionAdvisor {

    @ExceptionHandler(value = PlayerNotFoundException.class)
    public ResponseMessage playerNotFoundException(PlayerNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }

    @ExceptionHandler(value = GameNotFoundException.class)
    public ResponseMessage gameNotFoundException(GameNotFoundException ex) {
        return new ResponseMessage(ex.getMessage());
    }
}

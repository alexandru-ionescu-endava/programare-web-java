package com.example.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(BookException.class)
    public ResponseEntity<?> handle(BookException e) {

        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

    @ExceptionHandler({AuthorException.class})
    public ResponseEntity<?> handle(AuthorException e) {

        return ResponseEntity
                .badRequest()
                .body(e.getMessage());
    }

}

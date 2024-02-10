package com.notes.ExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DuplicateUserException.class)
    public ResponseEntity<Object> throwException(){

        return new ResponseEntity<Object>("User Already Exist", HttpStatus.BAD_REQUEST);
    }

}

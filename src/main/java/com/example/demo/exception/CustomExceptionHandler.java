package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Optional;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ErrorMessage catchNotFoundException(NotFoundException e,WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),request.getDescription(false), HttpStatus.NOT_FOUND, new Date());
        return errorMessage;
    }

    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage catchBadRequestException(BadRequestException e, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST, new Date());
        return errorMessage;
    }


}

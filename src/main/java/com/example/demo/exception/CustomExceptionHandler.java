package com.example.demo.exception;

import com.example.demo.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.Date;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

//    @ExceptionHandler(NotFoundException.class)
//    public ErrorMessage catchNotFoundException(NotFoundException e,WebRequest request) {
//        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(),request.getDescription(false),
//                HttpStatus.NOT_FOUND, new Date());
//        return errorMessage;
//    }
//
    @ExceptionHandler(BadRequestException.class)
    public ErrorMessage catchBadRequestException(BadRequestException e, WebRequest request) {
        log.warn("Inside Global Exception handler !!!");
        ErrorMessage errorMessage = new ErrorMessage(e.getMessage(), request.getDescription(false),
                HttpStatus.BAD_REQUEST, new Date());
        return errorMessage;
    }

    @ExceptionHandler(FilterException.class)
    public ErrorMessage catchFilterException(FilterException f, WebRequest request) {
        log.warn("Inside Global Exception handler !!!");
        ErrorMessage errorMessage = new ErrorMessage(f.getMessage(), request.getDescription(false),
                HttpStatus.FORBIDDEN, new Date());
        log.warn("Error Message : {}",f.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return new ResponseEntity<String>("Value not found ", HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//      return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
        return new ResponseEntity<>("Change the HTTP request type", HttpStatus.METHOD_NOT_ALLOWED);
    }
}

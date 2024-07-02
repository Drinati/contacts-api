package com.contact.agenda.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Autowired
    public CustomExceptionHandler(MessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {

        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                "Resource not found",
                ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyExistException.class)
    public final ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException ex) {
        CustomExceptionResponse exceptionResponse = new CustomExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.CONFLICT,
                "Resource already exists",
                ex.getMessage());

        return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
    }


}

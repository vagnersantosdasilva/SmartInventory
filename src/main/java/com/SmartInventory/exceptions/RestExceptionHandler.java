package com.SmartInventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DuplicateValueException.class)
    public ResponseEntity<?> handleDuplicateMachineException(DuplicateValueException ex){
        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("Erro de duplicação de registro");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());

        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFound(ResourceNotFoundException ex){
        ErrorDetails error = new ErrorDetails();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(new Date().getTime());
        error.setTitle("O recurso não foi encontrado");
        error.setDeveloperMessage(ex.getClass().getName());
        error.setDetail(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


}

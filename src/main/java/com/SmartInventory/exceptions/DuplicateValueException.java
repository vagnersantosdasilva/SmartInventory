package com.SmartInventory.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicateValueException extends Exception{

    public DuplicateValueException(String message){
        super(message);
    }
}

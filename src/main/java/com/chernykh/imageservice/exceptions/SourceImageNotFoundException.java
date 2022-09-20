package com.chernykh.imageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SourceImageNotFoundException extends RuntimeException {
    public SourceImageNotFoundException(){
        super("Requested image not found");
    }

    public SourceImageNotFoundException(String message){
        super("Requested image not found: \n" + message);
    }
}

package com.chernykh.imageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FailedImageWriteException extends RuntimeException {
    public FailedImageWriteException(){
        super("Failed to write an image to bucket");
    }

    public FailedImageWriteException(String message){
        super("Failed to write an image to bucket: \n" + message);
    }
}

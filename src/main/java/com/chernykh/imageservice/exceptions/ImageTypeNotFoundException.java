package com.chernykh.imageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ImageTypeNotFoundException extends RuntimeException {
    public ImageTypeNotFoundException(){
        super("Image type not found");
    }

    public ImageTypeNotFoundException(String message){
        super("Image type not found: \n" + message);
    }
}

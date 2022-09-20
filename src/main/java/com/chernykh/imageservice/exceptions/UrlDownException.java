package com.chernykh.imageservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UrlDownException extends RuntimeException {
    public UrlDownException(){
        super("Unable to connect to url");
    }

    public UrlDownException(String message){
        super("Unable to connect to url: \n" + message);
    }
}

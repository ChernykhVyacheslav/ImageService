package com.chernykh.imageservice.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {ImageTypeNotFoundException.class, SourceImageNotFoundException.class})
    protected ResponseEntity<Object> handleImageNotFound(
            RuntimeException ex, WebRequest request) {
        log.info(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value
            = {Throwable.class})
    protected ResponseEntity<Object> handleOther(
            RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage());
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }


}

package com.ufrn.imd.web2.av1.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> basicExceptionHandler(Exception ex, WebRequest request) {
        final var clazz = ex.getClass();
        final var responseStatus = clazz.getAnnotation(ResponseStatus.class);

        HttpStatus status;

        if (responseStatus != null) {
            status = responseStatus.value();
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), status, request);
    }
}

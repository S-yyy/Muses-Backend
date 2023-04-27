package com.mu.muses.handler;

import com.mu.muses.config.RestResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public Object handle(RuntimeException ex) {
        System.out.println(ex.toString());
        return RestResponse.fail(ex.getMessage());
    }
}

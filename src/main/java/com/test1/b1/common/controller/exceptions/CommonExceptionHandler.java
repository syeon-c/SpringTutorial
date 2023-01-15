package com.test1.b1.common.controller.exceptions;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Log4j2
public class CommonExceptionHandler {

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Map<String, String>> except(HttpMediaTypeNotSupportedException exception) {
        log.error("------------------------");
        log.error(exception);
        log.error("------------------------");

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("exception", exception.getMessage()));
    }

}

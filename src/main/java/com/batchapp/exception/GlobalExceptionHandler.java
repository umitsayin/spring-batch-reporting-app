package com.batchapp.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityNotFoundException e, HttpServletRequest request){

        return ResponseEntity.ok().body(getExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST),
                request.getServletPath(),
                ZonedDateTime.now().toString(),
                e.getMessage()));
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<?> entityNotFoundExceptionHandler(EntityAlreadyExistsException e, HttpServletRequest request){

        return ResponseEntity.ok().body(getExceptionResponse(String.valueOf(HttpStatus.BAD_REQUEST),
                request.getServletPath(),
                ZonedDateTime.now().toString(),
                e.getMessage()));
    }

    private Map<String, String> getExceptionResponse(String status, String path, String time, String message){
        Map<String, String> exceptionMap = new HashMap<>();

        exceptionMap.put("status", status);
        exceptionMap.put("path", path);
        exceptionMap.put("time", time);
        exceptionMap.put("message", message);

        return exceptionMap;
    }
}

package com.kevinguevara.library_management.exception;


import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    private Map<String, Object> errorDetails(HttpStatus status, String message){
        return Map.of(
            "timestamp", LocalDateTime.now(),
            "status", status.value(),
            "error", status.getReasonPhrase(),
            "message", message );  
   }
   @ExceptionHandler(EntityNotFoundException.class)
   public ResponseEntity<?> handleNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails(HttpStatus.NOT_FOUND, ex.getMessage()));
   }

   @ExceptionHandler(BookNotAvailableException.class)
   public ResponseEntity<?> handleBookNotAvailable(BookNotAvailableException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDetails(HttpStatus.CONFLICT, ex.getMessage()));
   }

    
   
}

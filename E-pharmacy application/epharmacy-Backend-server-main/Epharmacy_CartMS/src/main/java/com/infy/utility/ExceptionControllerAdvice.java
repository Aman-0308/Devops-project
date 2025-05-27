package com.infy.utility;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.exception.EPharmacyException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EPharmacyException.class)
    public ResponseEntity<ErrorMessage> handleEPharmacyException(EPharmacyException e) {
        
        // Create error message with details
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setCode(HttpStatus.BAD_REQUEST.value()); // This should reflect the actual HTTP status code
        errorMessage.setTimeStamp(LocalDateTime.now().toString());
        errorMessage.setMessage(e.getMessage());
        
        // Return ResponseEntity with matching HTTP status
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST); // Use BAD_REQUEST here instead of NOT_FOUND
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return new ResponseEntity<>("An unexpected error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

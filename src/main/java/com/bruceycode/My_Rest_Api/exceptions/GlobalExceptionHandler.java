package com.bruceycode.My_Rest_Api.exceptions;

import com.bruceycode.My_Rest_Api.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception){
        ErrorResponse studentNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Student Not Found");
        return new ResponseEntity<>(studentNotFound, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArrayIndexOutOfBoundsException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFoundException(ArrayIndexOutOfBoundsException exception){
        ErrorResponse studentNotFound = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Student Not Found");
        return new ResponseEntity<>(studentNotFound, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<ErrorResponse> EmailTakenException(EmailTakenException exception){
        ErrorResponse emailTaken = new ErrorResponse(LocalDateTime.now(), exception.getMessage(), "Email Address Already Registered ");
        return new ResponseEntity<>(emailTaken, HttpStatus.CONFLICT);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}

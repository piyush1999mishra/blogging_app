package com.example.bloggingApp.exceptionHandler;

import com.example.bloggingApp.exceptions.ResourceNotFoundException;
import com.example.bloggingApp.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> resourceNotFoundHandler(ResourceNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setSuccess(false);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> notValidExceptionHandler(MethodArgumentNotValidException ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(),false);
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> allException(Exception ex){
        ErrorResponse errorResponse=new ErrorResponse(ex.getMessage(),false);
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

}

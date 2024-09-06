package com.mc.ecommerce.GlobalExceptions;


import com.mc.ecommerce.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice //permet de gérer les exceptions à un niveau global pour tous les contrôleurs REST de l'application
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<String>CustomerNotFoundExceptionHandler(CustomerNotFoundException customerNotFoundException){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(customerNotFoundException.getMessage());
    }



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException methodArgumentNotValidException){
        var errors= new HashMap<String,String>();
        methodArgumentNotValidException.getBindingResult().getAllErrors()
                .forEach(error->{
                    var fieldName=((FieldError)error).getField();
                    var errorMessage=error.getDefaultMessage();
                    errors.put(fieldName,errorMessage);
                });
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body( new ErrorResponse(errors));
    }
}

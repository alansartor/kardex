package com.alan.kardex.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {
	
    @ExceptionHandler(AbstractExeption.class)
    public ResponseEntity<Object> handleNotFoundExeptionException(AbstractExeption ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ExeptionResponse(ex, request.getServletPath()), ex.getStatus());
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
    	List<FieldErrorDetail> errors =  new ArrayList<FieldErrorDetail>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
        	errors.add(new FieldErrorDetail(((FieldError) error).getField(), error.getDefaultMessage()));
        });
        
        return new ResponseEntity<>(new ExeptionResponse("Invalid Data", HttpStatus.BAD_REQUEST.value(), 
        		"Faltan datos y/o son incorrectos, verifique los errores informados", request.getServletPath(), errors), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(FieldErrorDetailExeption.class)
    public ResponseEntity<Object> handleFieldErrorDetailExeption(FieldErrorDetailExeption ex, HttpServletRequest request) {
        return new ResponseEntity<>(new ExeptionResponse("Invalid Data", HttpStatus.BAD_REQUEST.value(), 
        		"Faltan datos y/o son incorrectos, verifique los errores informados", request.getServletPath(), Arrays.asList(ex.getDetail())), HttpStatus.BAD_REQUEST);
    }
    
}

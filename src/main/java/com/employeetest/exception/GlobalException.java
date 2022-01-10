package com.employeetest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        List<String> errorList = allErrors.stream().map(obj -> obj.getDefaultMessage()).collect(Collectors.toList());
        return new ResponseEntity<List<String>>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmpException.class)
    public ResponseEntity<?> handleEmployeePayrollException(EmpException e){
        String errorMessage = e.getMessage();
        return new ResponseEntity<String>(errorMessage, HttpStatus.NOT_FOUND);
    }
}

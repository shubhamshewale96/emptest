package com.employeetest.exception;

public class EmpException extends RuntimeException{
    private String message;

    public EmpException(String message) {
        super(message);
    }
}

package com.example.bookstore.exception;

//Custom CustomException for EmployeePayrollApp

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        //super keyword used to give message to the runtime CustomException class
        super(message);
    }
}

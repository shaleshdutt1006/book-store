package com.example.bookstore.exception;

//Custom Exception for EmployeePayrollApp

public class UserException extends RuntimeException {

    public UserException(String message) {
        //super keyword used to give message to the runtime Exception class
        super(message);
    }
}

package com.example.bookstore.exception;

import com.example.bookstore.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.ObjectError;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandler {
    //Method to handle all the exception while giving arguments
    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        //Saving all the errors in the list named errorList which are happened during handling of arguments
        List<ObjectError> errorList = exception.getBindingResult().getAllErrors();

        //Applying stream over the errorList to get default error message to print it using responseDto
        List<String> errMsg = errorList.stream().
                map(objError -> objError.getDefaultMessage()).collect(Collectors.toList());
        ResponseDTO responseDTO = new ResponseDTO("CustomException while processing Rest Request", errMsg);
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    //This method is used to handle the custom customException while getting the Address-book and print the message
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public ResponseEntity<ResponseDTO> handleUserException(CustomException customException) {
        ResponseDTO responseDTO = new ResponseDTO("CustomException while processing the Request is "
                , customException.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);


    }
}
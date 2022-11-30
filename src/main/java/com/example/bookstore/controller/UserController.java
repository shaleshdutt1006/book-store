package com.example.bookstore.controller;


import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.model.User;

import com.example.bookstore.service.UserIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/user")

public class UserController {
    @Autowired
    UserIService userIService;

    //Method to register user details
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerDetails(@Valid @RequestBody UserDTO userDTO) {
        userIService.registerDetails(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Details registered Successfully", userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);

    }
    //Method to get user by userId
    @GetMapping("/getbyId")
    public ResponseEntity<ResponseDTO> getBytoken(@RequestParam String token) {
        Optional<User> userDTO = userIService.getByUserId(token);
        ResponseDTO responseDTO = new ResponseDTO("Get call userId successfully", userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);

    }
    //Method to get User by emailId
    @GetMapping("/getby/{emailId}")
    public ResponseEntity<ResponseDTO> getByEmailId(@PathVariable String emailId) {
        Optional<User> userDTO = userIService.getByEmailId(emailId);
        ResponseDTO responseDTO = new ResponseDTO("Get call emailId successfully", userDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);

    }
    //Method to get all the users in database
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAll() {
        List<User> userList = userIService.getAll();
        ResponseDTO responseDTO = new ResponseDTO("Get all Id successfully", userList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Method to reset the password of user using emailId
    @PutMapping("/reset-password/{emailId}")
    public ResponseEntity<ResponseDTO> resetPassword(@Valid @PathVariable String emailId,
                                         @RequestBody LoginDTO loginDTO,@RequestParam String token) {
        User searchByEmailId = userIService.resetPassword(emailId,loginDTO,token);
        ResponseDTO responseDTO = new ResponseDTO("Password reset successfully", searchByEmailId);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Method to update user details by using emailId
    @PutMapping("/updateByEmail")
    public ResponseEntity<ResponseDTO> updateUserByEmail(@Valid @RequestParam String emailId,
                                                         @RequestBody UserDTO userDTO) {
        User updatedUser = userIService.updateByEmailId(emailId, userDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated user successfully", updatedUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Method to login by user using its emailId and password
    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        String loginUser = userIService.loginByEmailId(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Login successfully", loginUser);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

}

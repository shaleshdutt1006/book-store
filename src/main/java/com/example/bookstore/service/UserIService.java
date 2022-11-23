package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.model.User;

import java.util.List;
import java.util.Optional;

public interface UserIService {


    //Method to register user details
    User registerDetails(UserDTO userDTO);

    //Method to get user by userId
    Optional<User> getByUserId(int userId);

    //Method to get User by emailId
    Optional<User> getByEmailId(String emailId);

    //Method to get all the users in database
    List<User> getAll();

    //Method to reset the password of user using emailId
    User resetPassword(String emailId, LoginDTO loginDTO);

    //Method to update user details by using emailId
    User updateByEmailId(String emailId, UserDTO userDTO);

    //Method for login
    String loginByEmailId(LoginDTO loginDTO);
}

package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.exception.UserException;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserIService {
    @Autowired
    UserRepository userRepository;

    //Method to register user details
    @Override
    public User registerDetails(UserDTO userDTO) {
        User registeredUser = new User(userDTO);
        userRepository.save(registeredUser);
        return registeredUser;
    }

    //Method to get user by userId
    @Override
    public Optional<User> getByUserId(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user;
        } else {
            throw new UserException("this userId is not Present");
        }
    }

    //Method to get User by emailId
    @Override
    public Optional<User> getByEmailId(String emailId) {
        Optional<User> user = userRepository.getByEmailId(emailId);
        if (user.isPresent()) {
            return user;
        } else {
            throw new UserException("This emailId is not present");
        }
    }

    //Method to get all the users in database
    @Override
    public List<User> getAll() {
        if (!userRepository.findAll().isEmpty()) {
            return userRepository.findAll();
        } else throw new UserException("No User Present in the database");
    }

    //Method to reset the password of user using emailId
    @Override
    public User resetPassword(String emailId, LoginDTO loginDTO) {
        Optional<User> foundUser = userRepository.getByEmailId(emailId);
        if (foundUser.isPresent()) {
            User user = userRepository.getByEmailId(emailId).get();
            user.setPassword(loginDTO.getPassword());
            userRepository.save(user);
            return user;
        } else throw new UserException("No User present of this emailId for resetting Password");
    }

    //Method to update user details by using emailId
    @Override
    public User updateByEmailId(String emailId, UserDTO userDTO) {
        User foundUser = userRepository.getByEmailId(emailId).get();
        if (userRepository.getByEmailId(emailId).isPresent()) {
            foundUser.setUserID(foundUser.getUserID());
            foundUser.setFirstName(userDTO.getFirstName());
            foundUser.setPassword(userDTO.getPassword());
            foundUser.setLastName(userDTO.getLastName());
            foundUser.setAddress(foundUser.getAddress());
            userRepository.save(foundUser);
            return userRepository.getByEmailId(emailId).get();
        } else throw new UserException("User not present of this emailId");

    }

    //Method to login using its emailId and password
    @Override
    public String loginByEmailId(LoginDTO loginDTO) {
        Optional<User> loginDetails = userRepository.getLoginDetails(loginDTO.getEmailId(), loginDTO.getPassword());
        if (loginDetails.isPresent()) {
            return "Login Successfully for user : " + loginDetails;
        } else throw new UserException("Invalid login details try again");
    }

}


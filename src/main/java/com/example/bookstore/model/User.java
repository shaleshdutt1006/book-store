package com.example.bookstore.model;


import com.example.bookstore.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private int userID;
    private String firstName;
    private String lastName;

    private String emailId;
    private String address;
    private LocalDate dob;
    private String password;

    public User(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.emailId = userDTO.getEmailId();
        this.address = userDTO.getAddress();
        this.dob = userDTO.getDob();
        this.password = userDTO.getPassword();
    }

}


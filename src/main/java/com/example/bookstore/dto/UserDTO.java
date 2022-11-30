package com.example.bookstore.dto;

import com.example.bookstore.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class UserDTO {
    @NotEmpty(message = "First Name is mandatory")
    @Pattern(regexp = "^[A-Z]{1}[a-zA-Z]{2,}$", message = "User First Name Invalid")
    private String firstName;

    private String lastName;

    @Pattern(regexp = "^[a-zA-Z\\d+_.-]+@[a-zA-Z]+.[a-zA-z]{2,}")
    private String emailId;

    @Pattern(regexp = ".{10,50}", message = "Address should have limit from 10 to 50 words")
    private String address;

    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;


    @Pattern(regexp = "(?=.*[A-Z])" + "(?=.*[0-9])" + "(?=.*[@#$%^&+=])" + "(?=\\S+$)" + ".{8,}"
            ,message = "password has at least 8 character one uppercase letter one numeric number" +
            " and one special character and no whitespace allowed between characters")
    private String password;


}

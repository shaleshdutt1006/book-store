package com.example.bookstore.dto;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class CartDTO {


    private int quantity;

    private int bookId;

    private int userID;


}

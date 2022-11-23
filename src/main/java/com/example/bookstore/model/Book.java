package com.example.bookstore.model;

import com.example.bookstore.dto.BookDTO;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private float price;
    private int quantity;

    //Constructor to insert the data
    public Book(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }

    //Constructor to update data using id
    public Book(int bookID, BookDTO bookDTO) {
        this.bookId = bookID;
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImg = bookDTO.getBookImg();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }
}

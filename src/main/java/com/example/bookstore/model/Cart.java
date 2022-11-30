package com.example.bookstore.model;


import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Data
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long cartId;

    //one to many because one cart has many bookId


    @ManyToOne()
    @JoinColumn(name = "cart_book_id")
    private Book book;
    //OneToOne because one cart has only one user
    @OneToOne()
    @JoinColumn(name = "cart_user_id")
    private User user;
    @Max(5)
    @Min(1)
    private int quantity;


    //Constructor to add data
    public Cart(Book book, User user, int quantity) {
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    //Constructor to update data
    public Cart(Long cartId, Book book, User user, int quantity) {
        this.cartId = cartId;
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

}

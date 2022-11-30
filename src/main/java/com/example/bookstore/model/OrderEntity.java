package com.example.bookstore.model;

import com.example.bookstore.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    LocalDate localDate = LocalDate.now();
    private int price;
    private int quantity;
    String address;

    @ManyToMany
    @JoinColumn(name = "books_id")
    private List<Book> book;

    @OneToOne()
    @JoinColumn(name = "user")
    private User user;

    //Constructor to insert the data
    public OrderEntity(List<Book> book, User user, OrderDTO orderDTO, int price, int quantity) {
        this.price = price;
        this.quantity = quantity;
        this.address = orderDTO.getAddress();
        this.book = book;
        this.user = user;
    }
    //Constructor to update orderId

    public OrderEntity(List<Book> book, User user, OrderDTO orderDTO, int price, int quantity, Long orderId) {
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.address = orderDTO.getAddress();
        this.book = book;
        this.user = user;
    }
}

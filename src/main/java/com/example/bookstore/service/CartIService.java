package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.model.Cart;

import java.util.List;
import java.util.Optional;

public interface CartIService {


    //Method to add item to the cart
    Cart addToCart(CartDTO cartDTO);

    //Method to get cart details by its id in the database if present otherwise throw custom exception of

    Optional<Cart> getById(Long id);

    //Method to get all the items in the cart if it is empty then throw exception

    List<Cart> getAll();


    //Method to update by id in database
    Cart updateById(Long cartId, CartDTO cartDTO);

    //Method to update quantity of Cart
    Optional<Cart> updateQuantityById(Long cartId, int quantity);

    //Method to delete cart item by its id
    List<Cart> deleteById(Long cartId);
}

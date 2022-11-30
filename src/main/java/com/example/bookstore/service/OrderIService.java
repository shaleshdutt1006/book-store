package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.model.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface OrderIService {
    //Method to place a order in the cart
    OrderEntity placeOrder(OrderDTO orderDTO);

    OrderEntity placeOrderAndRemoveItemFromCart(OrderDTO orderDTO);

    //Method to get order details by its id in the database if present otherwise throw custom exception of
    Optional<OrderEntity> getById(Long id);

    //Method to get all the ordered items if it is empty then throw exception
    List<OrderEntity> getAll();

    //Method to update data of order using Id
    OrderEntity updateById(Long orderId, OrderDTO orderDTO);

    //Method to delete order item by its id
    List<OrderEntity> deleteById(Long cartId);
}

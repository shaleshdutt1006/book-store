package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.OrderEntity;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements OrderIService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CartRepository cartRepository;

    //Method to place an order in the cart
    @Override
    public OrderEntity placeOrder(OrderDTO orderDTO) {
        List<Book> bookList = orderDTO.getBookIDs().stream().map(book -> bookRepository.findById(book).get()).collect(Collectors.toList());
        Optional<User> user = userRepository.findById(orderDTO.getUserId());
        int price = bookList.stream().mapToInt(x -> (int) (x.getPrice())).sum();
        int quantity = bookList.stream().mapToInt(x -> (int) (x.getQuantity())).sum();
        if (user.isPresent()) {
            OrderEntity addItem = new OrderEntity(bookList, user.get(), orderDTO, price, quantity);
            orderRepository.save(addItem);
            return addItem;
        } else throw new CustomException("UserId or bookId not present please provide correct details");
    }
    //Place order and remove item from cart
    @Override
    public OrderEntity placeOrderAndRemoveItemFromCart(OrderDTO orderDTO) {
        List<Book> bookList = orderDTO.getBookIDs().stream().map(book -> bookRepository.findById(book).get()).collect(Collectors.toList());
        Optional<User> user = userRepository.findById(orderDTO.getUserId());
        int price = bookList.stream().mapToInt(x -> (int) (x.getPrice())).sum();
        int quantity = bookList.stream().mapToInt(x -> (int) (x.getQuantity())).sum();
        List<Integer> list = bookList.stream().map(book -> book.getBookId()).collect(Collectors.toList());
        if (list.equals(orderDTO.getBookIDs()) && user.isPresent()) {
            Long getCartUser = cartRepository.getCartUser(orderDTO.getUserId());
            cartRepository.deleteById(getCartUser);
            OrderEntity addItem = new OrderEntity(bookList, user.get(), orderDTO, price, quantity);
            orderRepository.save(addItem);
            return addItem;
        } else throw new CustomException("UserId or bookId not present please provide correct details");
    }

    @Override
    //Method to get order details by its id in the database if present otherwise throw custom exception of

    public Optional<OrderEntity> getById(Long id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            return orderEntity;
        } else throw new CustomException("Order is not present of this id");
    }

    @Override
    //Method to get all the ordered items if it is empty then throw exception

    public List<OrderEntity> getAll() {
        if (!orderRepository.findAll().isEmpty()) {
            return orderRepository.findAll();
        } else throw new CustomException("No Item Present in the Cart");
    }

    @Override
    //Method to update data of order using Id
    public OrderEntity updateById(Long orderId, OrderDTO orderDTO) {
        Optional<OrderEntity> foundId = orderRepository.findById(orderId);
        List<Book> booksList = orderDTO.getBookIDs().stream().map(book -> bookRepository.findById(book).get()).collect(Collectors.toList());
        Optional<User> user = userRepository.findById(orderDTO.getUserId());
        int price = booksList.stream().mapToInt(x -> (int) (x.getPrice())).sum();
        int quantity = booksList.stream().mapToInt(x -> (int) (x.getQuantity())).sum();
        if (foundId.isPresent() && user.isPresent()) {
            OrderEntity updatedItem = new OrderEntity(booksList, user.get(), orderDTO, price, quantity, orderId);
            orderRepository.save(updatedItem);
            return updatedItem;
        } else throw new CustomException("Id not present to update quantity");
    }

    @Override
    //Method to delete order item by its id
    public List<OrderEntity> deleteById(Long orderId) {
        if (orderRepository.findById(orderId).isPresent()) {
            orderRepository.deleteById(orderId);
            return orderRepository.findAll();
        } else {
            throw new CustomException("Id not present in the list to delete");
        }
    }
}

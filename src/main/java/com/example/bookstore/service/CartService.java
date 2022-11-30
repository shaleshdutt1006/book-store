package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class CartService implements CartIService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    TokenUtil tokenUtil;

    //Method to add item to the cart it first Checks whether user or book present of this id if present
    //then only insert item in the cart otherwise throw exception
    //We do cartDTO.getUserId().getUser() because in first it get userid of type user which is declared in
    //cartDTO class and second one userId is to get User id to that User class
    @Override
    public Cart addToCart(CartDTO cartDTO) {
        Optional<User> user = userRepository.findById(cartDTO.getUserID());
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Cart addedItem = new Cart(book.get(), user.get(), cartDTO.getQuantity());
            cartRepository.save(addedItem);
            return addedItem;
        } else throw new CustomException("UserId or bookId not present please provide correct details");
    }

    @Override
    //Method to get cart details by its id in the database if present otherwise throw custom exception of

    public Optional<Cart> getById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return cart;
        } else throw new CustomException("Cart Id not  present");
    }

    @Override
    //Method to get all the items in the cart if it is empty then throw exception

    public List<Cart> getAll() {
        if (!cartRepository.findAll().isEmpty()) {
            return cartRepository.findAll();
        } else throw new CustomException("No Item Present in the Cart");
    }

    @Override
    //Method to update data of Cart using Id
    public Cart updateById(Long cartId, CartDTO cartDTO) {
        Optional<Cart> foundId = cartRepository.findById(cartId);
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        Optional<User> user = userRepository.findById(cartDTO.getUserID());
        if (foundId.isPresent() && book.isPresent() && user.isPresent()) {
            Cart updatedCart = new Cart(cartId, book.get(),
                    user.get(), cartDTO.getQuantity());
            cartRepository.save(updatedCart);
            return updatedCart;
        } else throw new CustomException("Id not present to update quantity");
    }

    @Override
    //Method to update quantity of Cart
    public Optional<Cart> updateQuantityById(Long cartId, int quantity) {
        Optional<Cart> foundId = cartRepository.findById(cartId);
        if (foundId.isPresent()) {
            foundId.get().setQuantity(quantity);
            Cart cart = new Cart(foundId.get().getCartId(), foundId.get().getBook(),
                    foundId.get().getUser(), foundId.get().getQuantity());
            cartRepository.save(cart);
            return foundId;
        } else throw new CustomException("Id not present to update quantity");
    }

    @Override
    //Method to delete cart item by its id
    public List<Cart> deleteById(Long cartId) {
        if (cartRepository.findById(cartId).isPresent()) {
            cartRepository.deleteById(cartId);
            return cartRepository.findAll();
        } else {
            throw new CustomException("Id not present in the list to delete");
        }
    }


}

package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookIService {


    //Method to register book details
    Book insert(BookDTO bookDTO);

    //Method to get book by bookId
    Optional<Book> getByBookId(int bookId);

    //Method to get book by its bookName
    Optional<Book> getByBookName(String bookName);

    //Method to get all the books in database
    List<Book> getAll();

    //Method to update book quantity using bookID
    Optional<Book> updateQuantity(int bookId, int quantity);

    //Method to update book details by using bookId
    Book updateByBookId(int bookId, BookDTO bookDTO);

    //Method to delete book details by using bookId
    List<Book> deleteByBookId(int bookId);

    //Method to sort book by its price in ascending order
    List<Book> sortedInAsc();

    //Method to sort book by its price in ascending order
    List<Book> sortedInDesc();



}

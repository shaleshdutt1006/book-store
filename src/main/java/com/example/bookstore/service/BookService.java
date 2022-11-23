package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class BookService implements BookIService {
    @Autowired
    BookRepository bookStoreRepository;

    //Method to register book details
    @Override
    public Book insert(BookDTO bookDTO) {
        Book insertBook = new Book(bookDTO);
        bookStoreRepository.save(insertBook);
        return insertBook;
    }

    //Method to get book by bookId
    @Override
    public Optional<Book> getByBookId(int bookId) {
        Optional<Book> book = bookStoreRepository.findById(bookId);
        if (book.isPresent()) {
            return book;
        } else {
            throw new CustomException("this bookId is not Present");
        }
    }

    //Method to get book by its bookName
    @Override
    public Optional<Book> getByBookName(String bookName) {
        Optional<Book> book = bookStoreRepository.getByBookName(bookName);
        if (book.isPresent()) {
            return book;
        } else {
            throw new CustomException("Book of this name is not present");
        }
    }

    //Method to get all the books in database
    @Override
    public List<Book> getAll() {
        if (!bookStoreRepository.findAll().isEmpty()) {
            return bookStoreRepository.findAll();
        } else throw new CustomException("No Book Present in the database");
    }

    //Method to update book quantity using bookID
    @Override
    public Optional<Book> updateQuantity(int bookId, int quantity) {
        if (bookStoreRepository.existsById(bookId)) {
            bookStoreRepository.updateQuantity(bookId, quantity);
            return getByBookId(bookId);
        } else {
            throw new CustomException("Book not present of this Id");
        }
    }

    //Method to update book details by using bookId
    @Override
    public Book updateByBookId(int bookId, BookDTO bookDTO) {
        Optional<Book> book = bookStoreRepository.findById(bookId);
        if (book.isPresent()) {
            Book updatedBook = new Book(bookId, bookDTO);
            bookStoreRepository.save(updatedBook);
            return updatedBook;
        } else throw new CustomException("Book not present of this Id");
    }

    //Method to delete book details by using bookId
    @Override
    public List<Book> deleteByBookId(int bookId) {
        Optional<Book> book = bookStoreRepository.findById(bookId);
        if (book.isPresent()) {
            bookStoreRepository.deleteById(bookId);
            return bookStoreRepository.findAll();
        } else throw new CustomException("Book not present of this Id");
    }

    //Method to sort book by its price in ascending order
    @Override
    public List<Book> sortedInAsc() {
        List<Book> sortedList = bookStoreRepository.sortedInAsc();
        if (sortedList.isEmpty()) {
            throw new CustomException("Book not present for sorting");
        } else return sortedList;
    }

    //Method to sort book by its price in ascending order
    @Override
    public List<Book> sortedInDesc() {
        List<Book> sortedList = bookStoreRepository.sortedInDesc();
        if (sortedList.isEmpty()) {
            throw new CustomException("Book not present for sorting");
        } else return sortedList;


    }
}



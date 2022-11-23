package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookIService bookIService;

    //Method to insert book details
    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insert(@Valid @RequestBody BookDTO bookDTO) {
        bookIService.insert(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Details added Successfully", bookDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Method to get book by bookId
    @GetMapping("/getbyId/{bookId}")
    public ResponseEntity<ResponseDTO> getByBookId(@PathVariable int bookId) {
        Optional<Book> foundBook = bookIService.getByBookId(bookId);
        ResponseDTO responseDTO = new ResponseDTO("Get call userId successfully", foundBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);

    }

    //Method to get book by bookName
    @GetMapping("/getbyBookName")
    public ResponseEntity<ResponseDTO> getByBookName(@RequestParam String bookName) {
        Optional<Book> foundBook = bookIService.getByBookName(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Get book successfully", foundBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);

    }

    //Method to get all the books in database
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAll() {
        List<Book> bookList = bookIService.getAll();
        ResponseDTO responseDTO = new ResponseDTO("Get all Id successfully", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }


    //Method to update the quantity of books
    @PutMapping("/updateQuantity/{bookId}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int bookId, @RequestParam int quantity) {
        Optional<Book> updatedQuantity = bookIService.updateQuantity(bookId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("update quantity successfully", updatedQuantity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Method to update book details by using bookId
    @PutMapping("/updateBybookId")
    public ResponseEntity<ResponseDTO> updateByBookId(@Valid @RequestParam int bookId, @RequestBody BookDTO bookDTO) {
        Book updatedBook = bookIService.updateByBookId(bookId, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated book successfully", updatedBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to delete book details by using bookId
    @DeleteMapping("/deleteById/{bookId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int bookId) {
        List<Book> updatedData = bookIService.deleteByBookId(bookId);
        ResponseDTO responseDTO = new ResponseDTO("Id deleted successfully", updatedData);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to sort books in ascending order by their price
    @GetMapping("/sortInAsc")
    public ResponseEntity<ResponseDTO> sortInAsc() {
        List<Book> sortedList = bookIService.sortedInAsc();
        ResponseDTO responseDTO = new ResponseDTO("Sorted in ascending order successfully", sortedList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to sort books in descending order by their price
    @GetMapping("/sortInDesc")
    public ResponseEntity<ResponseDTO> sortInDesc() {
        List<Book> sortedList = bookIService.sortedInDesc();
        ResponseDTO responseDTO = new ResponseDTO("Sorted in descending order successfully", sortedList);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

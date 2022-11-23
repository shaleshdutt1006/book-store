package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.Cart;
import com.example.bookstore.service.CartIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartIService cartIService;

    //Method to insert book details
    @PostMapping("/addToCart")
    public ResponseEntity<ResponseDTO> addToCart(@Valid @RequestBody CartDTO cartDTO) {
        Cart cart = cartIService.addToCart(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("Details added Successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //Method to get book by bookId
    @GetMapping("/getbyId/{cartId}")
    public ResponseEntity<ResponseDTO> getByCartId(@PathVariable Long cartId) {
        Optional<Cart> foundedItem = cartIService.getById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Get call userId successfully", foundedItem);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }


    //Method to get all the books in database
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAll() {
        List<Cart> cartList = cartIService.getAll();
        ResponseDTO responseDTO = new ResponseDTO("Get all Id successfully", cartList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }


    //Method to update list using body
    @PutMapping("/updateById/{cartId}")
    public ResponseEntity<ResponseDTO> updateById(@Valid @PathVariable Long cartId, @RequestBody CartDTO cartDTO) {
       Optional<Cart> updatedQuantity = cartIService.updateById(cartId, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("update quantity successfully", updatedQuantity);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //Method to update quantity of cart by using cartId
    @PutMapping("/updateQuantity{cartId}")
    public ResponseEntity<ResponseDTO> updateQuantityById(@Valid @PathVariable Long cartId, @RequestParam int quantity) {
        Optional<Cart> updatedCart = cartIService.updateQuantityById(cartId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Updated book successfully", updatedCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to delete book details by using bookId
    @DeleteMapping("/deleteById/{cartId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long cartId) {
        List<Cart> updatedData = cartIService.deleteById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Id deleted successfully", updatedData);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }


}

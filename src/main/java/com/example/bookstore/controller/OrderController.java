package com.example.bookstore.controller;


import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.OrderEntity;
import com.example.bookstore.service.OrderIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderIService orderIService;
    //Method to insert book details
    @PostMapping("/placeOrder")
    public ResponseEntity<ResponseDTO> placeOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderEntity newOrderEntity = orderIService.placeOrder(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Details added Successfully", newOrderEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    //Method to insert book details
    @PostMapping("/placeOrderDeleteFromCart")
    public ResponseEntity<ResponseDTO> placeOrderAndDeleteFromCart(@Valid @RequestBody OrderDTO orderDTO) {
        OrderEntity newOrderEntity = orderIService.placeOrderAndRemoveItemFromCart(orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Details added Successfully", newOrderEntity);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    //Method to get order by orderId
    @GetMapping("/getbyId/{orderId}")
    public ResponseEntity<ResponseDTO> getByCartId(@PathVariable Long orderId) {
        Optional<OrderEntity> foundedItem = orderIService.getById(orderId);
        ResponseDTO responseDTO = new ResponseDTO("Get call userId successfully", foundedItem);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }


    //Method to get all the orders in database
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAll() {
        List<OrderEntity> orderList = orderIService.getAll();
        ResponseDTO responseDTO = new ResponseDTO("Get all Id successfully", orderList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }

    //Method to update list using body
    @PutMapping("/updateById/{orderId}")
    public ResponseEntity<ResponseDTO> updateById(@Valid @PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        OrderEntity updatedResult= orderIService.updateById(orderId, orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("update quantity successfully", updatedResult);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    //Method to delete order by using orderId
    @DeleteMapping("/deleteById/{orderId}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable Long orderId) {
        List<OrderEntity> updatedData = orderIService.deleteById(orderId);
        ResponseDTO responseDTO = new ResponseDTO("Id deleted successfully", updatedData);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
}

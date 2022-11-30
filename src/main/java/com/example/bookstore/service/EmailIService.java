package com.example.bookstore.service;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.Email;
import org.springframework.http.ResponseEntity;

public interface EmailIService {
    ResponseEntity<ResponseDTO> sendMail(Email email);
}

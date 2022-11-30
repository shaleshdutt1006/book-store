package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Data
@NoArgsConstructor
public class OrderDTO {

    String address;
    private int userId;
    private List<Integer> bookIDs;
}

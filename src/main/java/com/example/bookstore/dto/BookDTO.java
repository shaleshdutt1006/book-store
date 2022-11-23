package com.example.bookstore.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

@Data
public class BookDTO {
    @NotEmpty(message = "Book Name is mandatory")
    @Pattern(regexp = "^[A-Z]{1}.{2,}$", message = "Book Name Invalid")
    private String bookName;

    @NotEmpty(message = "Author Name is mandatory")
    @Pattern(regexp = "^[A-Z]{1}.{2,}$", message = "Author Name Invalid")
    private String authorName;

    @Pattern(regexp = ".{10,50}", message = "Book description should be in limit 10 to 50 words")
    private String bookDescription;

    private String bookImg;

    @Max(value = 10000, message = "Maximum value for the book is 10k")
    private float price;

   @Max(5)@Min(1)
    private int quantity;


}

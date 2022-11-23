package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    //Custom query to get details by bookName
    @Query(value = "select * from Book where book_Name=:bookName", nativeQuery = true)
    Optional<Book> getByBookName(String bookName);

    //Custom query to update quantity using bookId these two annotation @Modifying and transactional are used
    //to update anything in database using custom query
    @Modifying
    @Transactional

    @Query(value = "update Book set quantity=:quantity where book_Id=:bookId", nativeQuery = true)
    void updateQuantity(int bookId, int quantity);

    //Custom query to sort book table in ascending order by its price
    @Query(value = "select * from Book order by price asc ", nativeQuery = true)
    List<Book> sortedInAsc();

    //Custom query to sort book table in ascending order by its price
    @Query(value = "select * from Book order by price desc", nativeQuery = true)
    List<Book> sortedInDesc();
}

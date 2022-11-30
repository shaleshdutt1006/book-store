package com.example.bookstore.repository;

import com.example.bookstore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    //Custom query to delete cart item using usedId

    @Query(value = "select cart_Id from book_store.cart where cart_user_id =:userId", nativeQuery = true)
    Long getCartUser(int userId);
}

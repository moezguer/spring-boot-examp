package com.moezguer.database.repository;

import com.moezguer.database.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBookByNameContainingIgnoreCase(String name);

    Book findById(BigDecimal id);

    @Query("SELECT b FROM Book b WHERE b.price < 6")
    List<Book> findAllWithPriceFilter(BigDecimal price);


}

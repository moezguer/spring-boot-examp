package com.moezguer.database.repository;

import com.moezguer.database.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBookByNameContainingIgnoreCase(String name);

}

package com.moezguer.database.repository;

import com.moezguer.database.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository <Author, Long>{

    List<Author> getAuthorByNameIgnoreCase(String name);
    List<Author> getAuthorBySurnameIgnoreCase(String name);
    List<Author> getAuthorByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);



}

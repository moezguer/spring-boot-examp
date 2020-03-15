package com.moezguer.database.repo;

import com.moezguer.database.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository <Author, UUID>{

    List<Author> getAuthorByNameIgnoreCase(String name);
    List<Author> getAuthorBySurnameIgnoreCase(String name);
    List<Author> getAuthorByNameIgnoreCaseAndSurnameIgnoreCase(String name, String surname);



}

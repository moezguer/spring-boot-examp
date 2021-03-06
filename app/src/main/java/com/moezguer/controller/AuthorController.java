package com.moezguer.controller;

import com.moezguer.database.model.Author;
import com.moezguer.database.repository.AuthorRepository;
import com.moezguer.exception.AuthorNotFoundException;
import com.moezguer.exception.AuthorUnSupportedFieldPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AuthorController {

    @Autowired private AuthorRepository repository;

    @GetMapping("/authors")
    List<Author> findAll(@RequestParam(value = "name",
                                       required = false) final String name, @RequestParam(value = "surname",
                                                                                          required = false)
                         final String surname) {
        if (StringUtils.isEmpty(name) && StringUtils.isEmpty(surname)) {
            return repository.findAll();
        } else if (StringUtils.isEmpty(name)) {
            return repository.getAuthorBySurnameIgnoreCase(surname);
        } else if (StringUtils.isEmpty(surname)) {
            return repository.getAuthorByNameIgnoreCase(name);
        } else {
            return repository.getAuthorByNameIgnoreCaseAndSurnameIgnoreCase(name, surname);
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Author newAuthor(@RequestBody final Author newAuthor) {
        return repository.save(newAuthor);
    }

    @GetMapping("/authors/{id}")
    Author findOne(@PathVariable final Long id) {
        return repository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @PutMapping("/authors/{id}")
    Author saveOrUpdate(@RequestBody final Author newAuthor, @PathVariable final Long id) {

        return repository.findById(id).map(x -> {
            x.setName(newAuthor.getName());
            x.setSurname(newAuthor.getSurname());
            return repository.save(newAuthor);
        }).orElseGet(() -> {
            throw new AuthorNotFoundException(id);
        });

    }

    @PatchMapping("/authors/{id}")
    Author patch(@RequestBody final Map<String, String> update, @PathVariable final Long id) {

        return repository.findById(id).map(x -> {

            String surname = update.get("surname");

            if (!StringUtils.isEmpty(surname)) {
                x.setSurname(surname);
                return repository.save(x);
            } else {
                throw new AuthorUnSupportedFieldPatchException(update.keySet());
            }
        }).orElseGet(() -> {
            throw new AuthorNotFoundException(id);
        });
    }

    @DeleteMapping("/authors/{id}")
    void deleteAuthor(@PathVariable final Long id) {
        repository.deleteById(id);
    }

}

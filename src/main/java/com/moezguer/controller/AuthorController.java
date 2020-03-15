package com.moezguer.controller;

import com.moezguer.database.model.Author;
import com.moezguer.database.repository.AuthorRepository;
import com.moezguer.exception.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository repository;

    //find all
    @GetMapping("/authors")
    List<Author> findAll(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname
    ) {
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
    Author newAuthor(@RequestBody Author newAuthor) {
        return repository.save(newAuthor);
    }

    @GetMapping("/authors/{id}")
    Author findOne(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }


    @PutMapping("/authors/{id}")
    Author saveOrUpdate(@RequestBody Author newAuthor, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {
                    x.setName(newAuthor.getName());
                    x.setSurname(newAuthor.getSurname());
                    return repository.save(newAuthor);
                })
                .orElseGet(() -> {
                    throw new AuthorNotFoundException(id);
                });

    }

/*    @PatchMapping("/authors/{id}")
    Author patch(@RequestBody Map<String, String> update, @PathVariable Long id) {

        return repository.findById(id)
                .map(x -> {

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
    }*/


    @DeleteMapping("/authors/{id}")
    void deleteAuthor(@PathVariable Long id) {
        repository.deleteById(id);
    }

}

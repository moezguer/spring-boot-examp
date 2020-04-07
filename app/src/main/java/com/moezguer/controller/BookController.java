package com.moezguer.controller;

import com.moezguer.database.model.Book;
import com.moezguer.database.repository.BookRepository;
import com.moezguer.exception.BookNotFoundException;
import com.moezguer.exception.BookUnSupportedFieldPatchException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/books")
@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

    // Find
    @GetMapping List<Book> findAll(@RequestParam(value = "name",
                                                 required = false) final String name) {
        if (!StringUtils.isEmpty(name)) {
            return repository.getBookByNameContainingIgnoreCase(name);
        } else {
            return repository.findAll();
        }
    }

    // Find with price filter
    @GetMapping("/filter") List<Book> findAllWithPriceFilter(@RequestParam(value = "price",
                                                                           required = true) final BigDecimal price) {

        return repository.findAllWithPriceFilter(price);

    }


    // Save
    @PostMapping
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED) Book newBook(@RequestBody final Book newBook) {
        return repository.save(newBook);
    }

    // BulkSave
    @PostMapping("/bulk")
    //return 201 instead of 200
    @ResponseStatus(HttpStatus.CREATED) List<Book> newBook(@RequestBody final List<Book> newBook) {
        return newBook.stream()
                      .map(book -> repository.save(book))
                      .collect(Collectors.toList());
    }

    // Find
    @GetMapping("/{id}") Book findOne(@PathVariable final Long id) {
        return repository.findById(id)
                         .orElseThrow(() -> new BookNotFoundException(id));
    }

    // Save or update
    @PutMapping("/{id}") Book saveOrUpdate(@RequestBody final Book newBook, @PathVariable final Long id) {

        return repository.findById(id)
                         .map(x -> {
                             x.setName(newBook.getName());
                             x.setAuthor(newBook.getAuthor());
                             x.setPrice(newBook.getPrice());
                             return repository.save(x);
                         })
                         .orElseGet(() -> {
                             newBook.setId(id);
                             return repository.save(newBook);
                         });
    }

    // update author only
    @PatchMapping("/{id}") Book patch(@RequestBody final Book update, @PathVariable final Long id) {

        return repository.findById(id)
                         .map(x -> {

                             String name = update.getName();
                             if (!StringUtils.isEmpty(name)) {
                                 x.setName(name);

                                 // better create a custom method to update a value = :newValue where id = :id
                                 return repository.save(x);
                             } else {
                                 throw new BookUnSupportedFieldPatchException(name);
                             }

                         })
                         .orElseGet(() -> {
                             throw new BookNotFoundException(id);

                         });

    }

    @DeleteMapping("/{id}") void deleteBook(@PathVariable final Long id) {
        repository.deleteById(id);
    }

}

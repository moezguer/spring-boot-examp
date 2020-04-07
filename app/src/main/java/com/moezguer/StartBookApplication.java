package com.moezguer;

import com.moezguer.database.model.Author;
import com.moezguer.database.model.Book;
import com.moezguer.database.repository.BookRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;


@SpringBootApplication
public class StartBookApplication {

    @Autowired
    private BookRepository bookRepository;

    // start everything
    public static void main(final String[] args) {
        SpringApplication.run(StartBookApplication.class, args);

    }

    @Bean InitializingBean initDatabase() {
        return () -> {
            bookRepository.save(new Book(1L, "A Guide to the Bodhisattva Way of Life", new Author("Santi", "deva"),
                    new BigDecimal("15.41")));
            bookRepository.save(new Book(2L, "The Life-Changing Magic of Tidying Up", new Author("Marie", "Kondo"),
                    new BigDecimal("9.69")));
            bookRepository.save(
                    new Book(3L, "Refactoring: Improving the Design of Existing Code", new Author("Martin", "Fowler"),
                            new BigDecimal("47.99")));
        };
    }
}

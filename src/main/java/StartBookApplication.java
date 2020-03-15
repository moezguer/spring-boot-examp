package com.moezguer;

import com.moezguer.database.repo.AuthorRepository;
import com.moezguer.database.repo.BookRepository;
import com.moezguer.database.model.Author;
import com.moezguer.database.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class StartBookApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartBookApplication.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            repository.save(new Book("A Guide to the Bodhisattva Way of Life", "Santi Deva", new BigDecimal("15.41")));
            repository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
            repository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));
        };
    }

    @Bean
    CommandLineRunner initDatabase2(AuthorRepository repository) {
        return args -> {
            repository.save(new Author("Santi", "Deva"));
            repository.save(new Author("Marie", "Kondo"));
            repository.save(new Author("Martin", "Fowler"));
        };
    }

}
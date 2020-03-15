package com.moezguer;

import com.moezguer.database.repository.AuthorRepository;
import com.moezguer.database.repository.BookRepository;
import com.moezguer.database.model.Author;
import com.moezguer.database.model.Book;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class StartBookApplication {

    // start everything
    public static void main(String[] args) {
        SpringApplication.run(StartBookApplication.class, args);
    }

    // run this only on profile 'demo', avoid run this in test
    // @Profile("demo")
    @Bean
    CommandLineRunner initDatabase(BookRepository bookRepository, AuthorRepository authorRepository) {
        return args -> {


            bookRepository.save(new Book("A Guide to the Bodhisattva Way of Life", new Author("Santi", "Deva"), new BigDecimal("15.41")));
           // bookRepository.save(new Book("The Life-Changing Magic of Tidying Up", new Author("Santi", "Deva"), new BigDecimal("9.69")));
            //bookRepository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));

            //bookRepository.save(new Book("A Guide to the Bodhisattva Way of Life", new BigDecimal("15.41")));
            //bookRepository.save(new Book("The Life-Changing Magic of Tidying Up", new BigDecimal("9.69")));

/*
            bookRepository.save(new Book("A Guide to the Bodhisattva Way of Life", new Author(1, "Santi", "Deva"), new BigDecimal("15.41")));
            bookRepository.save(new Book("The Life-Changing Magic of Tidying Up", "Marie Kondo", new BigDecimal("9.69")));
            bookRepository.save(new Book("Refactoring: Improving the Design of Existing Code", "Martin Fowler", new BigDecimal("47.99")));*/
/*            List<Book> books = new ArrayList<>();
            books.add(new Book("A Guide to the Bodhisattva Way of Life", "Santi Deva", new BigDecimal("15.41")));

            authorRepository.save(new Author("Santi", "Deva", books));
*//*            authorRepository.save(new Author("Marie", "Kondo"));
            authorRepository.save(new Author("Martin", "Fowler"));*/
        };
    }


}
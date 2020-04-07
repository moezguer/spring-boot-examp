package com.moezguer.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moezguer.database.model.Author;
import com.moezguer.database.model.Book;
import com.moezguer.database.repository.BookRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {


    private static final ObjectMapper objectmapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;

    @MockBean private BookRepository mockBookRepository;

    @Before
    public void init() {
        Book book = new Book(1L, "Book Name", new Author("Arthuro", "Vidal"), new BigDecimal("9.99"));
        when(mockBookRepository.findById(1L)).thenReturn(Optional.of(book));
    }


    @Test
    public void findByBookID_OK() throws Exception {

        mockMvc.perform(get("/books/1"))
               .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
               .andExpect(status().isOk()).andExpect(jsonPath("$.id", is(1)))
               .andExpect(jsonPath("$.name", is("Book Name"))).andExpect(jsonPath("$.price", is(9.99)));

        verify(mockBookRepository, times(1)).findById(1L);

    }

    @Test
    public void findByBookID_NOK() throws Exception {

        mockMvc.perform(get("/books/55")).andExpect(status().isNotFound());
    }

    @Test
    public void save_Book() throws Exception {

        Book newBook = new Book(5L, "Booky", new Author("De", "Jong"), new BigDecimal("91.99"));
        when(mockBookRepository.save(any(Book.class))).thenReturn(newBook);

        mockMvc.perform(post("/books").content(objectmapper.writeValueAsString(newBook))
                                      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(5)))
               .andExpect(jsonPath("$.name", is("Booky Booky"))).andExpect(jsonPath("$.price", is(91.99)));

        verify(mockBookRepository, times(1)).save(any(Book.class));
    }
}

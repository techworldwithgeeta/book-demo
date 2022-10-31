package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.demo.exceptions.BookException;
import com.example.demo.model.Book;
import com.example.demo.services.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * BookControllerTest to auomate test cases for Book Controller class
 * @author Geeta Khatwani
 * @since 2022
 */

@WebMvcTest(BookController.class)
public class BookControllerTest {
    
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BookServiceImpl bookService;

    private Book book1 ;
    private Book book2;
    private List<Book> bookList = new ArrayList<>();

    @BeforeEach
    void setUp(){
      book1 = new Book(1L," The Secret","Magical book to know more about universe",500.00,"Geeta Khatwani","fiction","1234567898",450.0);
      book2 = new Book(2L,"The Magic","Magical book to know more about universe",400.00,"Geeta Khatwani","fiction","1234567898",360.0);
    
      bookList.add(book1);
      bookList.add(book2);
    }

    @AfterEach
    void tearDown() throws Exception{ 
    }

    @Test
    public void testGetAllBooksinCT() throws  Exception {
        Mockito.when(bookService.getAllBooks()).thenReturn(bookList);
        this.mockMvc.perform(get("/books"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testGetBook() throws BookException,Exception{
        Mockito.when(bookService.getBook(1l)).thenReturn(book1);
        this.mockMvc.perform(get("/books/" + "1")).
        andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void testAddBook() throws Exception{
       // Mockito.when(bookService.addBook(ArgumentMatchers.any())).thenReturn(true);     
      Mockito.when(bookService.addBook(book1)).thenReturn("success");
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(book1);

        MockHttpServletRequestBuilder requestBuilder = 
        MockMvcRequestBuilders.post("/books")
        .content(bookJson)
        .contentType(MediaType.APPLICATION_JSON);

        ResultActions perform = mockMvc.perform(requestBuilder);
        MvcResult result = perform.andReturn();

        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        assertEquals(201,status);
    }

    @Test
    public void testUpdateBook() throws Exception{
      Mockito.when(bookService.updateBook(book1,1L)).thenReturn("success");
        ObjectMapper mapper = new ObjectMapper();
        String bookJson = mapper.writeValueAsString(book1);

        MockHttpServletRequestBuilder requestBuilder = 
        MockMvcRequestBuilders.put("/books/1")
        .content(bookJson)
        .contentType(MediaType.APPLICATION_JSON);

        ResultActions perform = mockMvc.perform(requestBuilder);
        MvcResult result = perform.andReturn();

        MockHttpServletResponse response = result.getResponse();
        int status = response.getStatus();
        assertEquals(200,status);
    }

    @Test
    public void testDeleteBook() throws Exception{
        Mockito.when(bookService.deleteBook(1L)).thenReturn("success");
        Mockito.when(bookService.getBook(1l)).thenReturn(book1);
        this.mockMvc.perform(delete("/books/" + "1")).
        andDo(print()).andExpect(status().isOk());
    }
     
}

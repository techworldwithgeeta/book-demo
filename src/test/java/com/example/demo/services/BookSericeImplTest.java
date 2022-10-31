package com.example.demo.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.example.demo.exceptions.BookException;
import com.example.demo.model.Book;
import com.example.demo.model.Discount;
import com.example.demo.repositories.BookRepository;
import com.example.demo.util.CommonUtility;


public class BookSericeImplTest {
    
    @Mock
    private BookRepository bookRepository;
    private BookService bookService;
    
    AutoCloseable autoCloseable;
    Book book;
    Discount discount;
    Optional<Book> bookOptional;

    @Mock
    private DiscountService discountService;

    @Mock
    private CommonUtility commonUtility;
   
    @BeforeEach
    void setUp(){
      autoCloseable = MockitoAnnotations.openMocks(this);
      bookService = new BookServiceImpl(bookRepository,commonUtility);
      discount = new Discount(1L, "fiction","fiction",10.0 ) ;
      book = new Book(1L,"The Secret","Magical book to know more about universe",500.00,"Geeta Khatwani","fiction","1234567898",190.0);
    
      Mockito.mock(Book.class);
      Mockito.mock(BookRepository.class);
      Mockito.mock(CommonUtility.class);
    }

    @AfterEach
    void tearDown() throws Exception{
     autoCloseable.close();
    }

    @Test
    void testGetAllBooks(){
        Mockito.when(bookRepository.findAll()).thenReturn(new ArrayList<>(Collections.singleton(book)));  
        Assertions.assertThat( bookService.getAllBooks().get(0).getName().equals(book.getName()));
    }
    
    @Test
    void  testGetBook() throws BookException{
       Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.ofNullable(book));
      Assertions.assertThat( bookService.getBook(1L).getName().equals(book.getName()));
    }

    @Test
    void testAddBook() throws Exception{
      Mockito.doNothing().when(commonUtility).calulateDiscount(book);
       Mockito.when(bookRepository.save(book)).thenReturn(book);   
       Assertions.assertThat( bookService.addBook(book)).isEqualTo("Book is created Successfully");
    }

    @Test
    void testUpdateBook() throws Exception{
       Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.ofNullable(book));
       Mockito.when(bookRepository.save(book)).thenReturn(book);   
       Assertions.assertThat( bookService.updateBook(book,1L)).isEqualTo("Book is updated succesfully for id " +1L);
    }

    @Test
    void testDeleteBook() throws Exception{
        Mockito.when(bookRepository.findById(1l)).thenReturn(Optional.ofNullable(book));
        Mockito.doAnswer(Answers.CALLS_REAL_METHODS).when(bookRepository).deleteById(any());
       Assertions.assertThat( bookService.deleteBook(1L)).isEqualTo("Book is deleted successfully for id " +1L);
    }

}

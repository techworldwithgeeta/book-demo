package com.example.demo.services;

import java.util.List;

import com.example.demo.exceptions.BookException;
import com.example.demo.model.Book;

/**
 * BookService interface to provide business logics for CRUD operations
 * @author Geeta Khatwani
 * @since 2022
 */
public interface BookService {
       
    public List<Book> getAllBooks();

    public Book getBook(Long id) throws BookException;

    public String addBook( Book book) throws Exception;

    public String updateBook(Book book, Long id) throws Exception;

    public String deleteBook(Long id) throws BookException;
}

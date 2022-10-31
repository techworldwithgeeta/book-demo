package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.BookException;
import com.example.demo.model.Book;
import com.example.demo.services.BookService;

import lombok.NoArgsConstructor;

/**
 * Book Controller class is an BOOK API with CRUD.
 * @author Geeta Khatwani
 * @since 2022
 */

@NoArgsConstructor
@RestController
public class BookController {

    @Autowired
     BookService bookService;

     /*
      * This constructor is used for Junit Automation.
      */
      /* 
     public BookController(BookService bookService){
        this.bookService = bookService;
     }
     */

     @GetMapping(value="/books")
     public ResponseEntity<?> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return new  ResponseEntity<>(books,books.size()>0 ? HttpStatus.OK:HttpStatus.NOT_FOUND);
     }
     
     @GetMapping(value="/books/{id}")
     public ResponseEntity<?> getBook(@PathVariable("id")Long id){
        try {
            return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
        } catch (BookException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);     
        }   
     }
  
    @PostMapping(value="/books",consumes = {"application/json"})
    public ResponseEntity<?> addBook( @Valid @RequestBody Book book){
        try {
            return new ResponseEntity<String>(bookService.addBook(book),HttpStatus.CREATED);
         }  catch (ConstraintViolationException e) {
              return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
         }    catch (BookException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }   
    }

    @PutMapping(value="/books/{id}",consumes = {"application/json"})
    public ResponseEntity<?> updateBook(@PathVariable("id") Long id,@Valid @RequestBody Book book){
        try {
            return new ResponseEntity<String>(bookService.updateBook(book, id),HttpStatus.OK);
        }catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
         }catch (BookException e) {
           return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value="/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id){
        try {
            return new ResponseEntity<>( bookService.deleteBook(id), HttpStatus.OK);
        } catch (BookException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
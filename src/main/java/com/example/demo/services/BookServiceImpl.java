package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptions.BookException;
import com.example.demo.model.Book;
import com.example.demo.repositories.BookRepository;
import com.example.demo.util.CommonUtility;


/**
 * BookService implementation to provide business logics for CRUD operations
 * @author Geeta Khatwani
 * @since 2022
 */
@Service
public class BookServiceImpl  implements BookService{

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CommonUtility commonUtility;

    /*
     * This constructor is created to automate junit
     */
    BookServiceImpl( BookRepository bookRepository ,CommonUtility commonUtility){
        this.bookRepository = bookRepository;
        this.commonUtility = commonUtility;
    } 

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = (List<Book>) bookRepository.findAll();
        if(bookList.size() > 0){
            return bookList;
        }
        return new ArrayList<Book>();
    }
    
    @Override
    public Book getBook(Long id) throws BookException {   
        Optional<Book> bookOptional =  bookRepository.findById(id);
    
        if(!bookOptional.isPresent()){
            throw new BookException(BookException.NotFoundException(id));
        }
        return bookOptional.get();
    }

    @Override
    public String addBook(Book book) throws Exception{ 
           commonUtility.calulateDiscount(book);
            bookRepository.save(book);
        return "Book is created Successfully";
    }
    
    @Override
    public String updateBook(Book book, Long id) throws Exception{
        Optional<Book> bookOptional =  bookRepository.findById(id);
        if(!bookOptional.isPresent()){
            throw new BookException(BookException.NotFoundException(id));
        }
         
          Book bookExist = bookOptional.get();
          bookExist.setName(book.getName());
          bookExist.setAuthor(book.getAuthor());
          bookExist.setDescription(book.getDescription());
          bookExist.setPrice(book.getPrice());
          bookExist.setType(book.getType());
          bookExist.setIsbn(book.getIsbn());
          commonUtility.calulateDiscount(bookExist);
          bookRepository.save(bookExist);

          return "Book is updated succesfully for id " + id; 
    }

    @Override
    public String deleteBook(Long id) throws BookException {
        Optional<Book> bookOptional =  bookRepository.findById(id);
        if(!bookOptional.isPresent()){
            throw new BookException(BookException.NotFoundException(id));
        }else{
            bookRepository.deleteById(id);
        }
        return "Book is deleted successfully for id " +id;
    }

}

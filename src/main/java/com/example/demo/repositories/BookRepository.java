package com.example.demo.repositories;



import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Book;

/**
 * BookRepository to perform CRUD operations with database
 * @author Geeta Khatwani
 * @since 2022
 */

@Repository
public interface BookRepository extends CrudRepository<Book,Long>{

  Optional<Book> findByIsbn(String isbn);
}
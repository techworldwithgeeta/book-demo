

package com.example.demo.exceptions;

/**
 * BookException class is used to handle book exceptions.
 * @author Geeta Khatwani
 * @since 2022
 */
public class BookException extends Exception{

    private static final long serialVersionUID =1;

    public BookException(String message){
      super(message);
    }

    public static String NotFoundException(Long id){
        return " Book with id " + id + " not found! " ; 
    }

    public static String BookAlreadyExists(){
        return "Book with same details already exists " ; 
    }
}

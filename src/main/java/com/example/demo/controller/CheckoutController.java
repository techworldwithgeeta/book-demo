package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.BookException;
import com.example.demo.model.Book;
import com.example.demo.model.Cart;
import com.example.demo.services.BookService;

/**
 * Checkout Controller class is an Checkout API .
 * @author Geeta Khatwani
 * @since 2022
 */

@RestController
public class CheckoutController {

    @Autowired
    BookService bookService;
    
    @PostMapping("/checkouts")
    public ResponseEntity<?> getTotalPrice(@RequestBody List<Cart> carts){
        Double totalPrice =0.0;
        try {
            if(carts.size() > 0){
                for(Cart cart : carts){   
                        Book book =  bookService.getBook( cart.getBookId());
                        totalPrice += book.getTotalPrice();
                }
            }
          return new ResponseEntity<Double>(totalPrice,HttpStatus.OK);
      } catch (BookException e) {
        return new ResponseEntity<Double>(totalPrice,HttpStatus.NOT_FOUND);
    }catch(Exception e){
        return new ResponseEntity<Double>(totalPrice,HttpStatus.BAD_REQUEST);
    }
}
 
}

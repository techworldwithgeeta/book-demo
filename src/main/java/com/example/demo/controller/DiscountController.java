package com.example.demo.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Discount;
import com.example.demo.services.DiscountService;

/**
 * Discount Controller class is an discount API.
 * @author Geeta Khatwani
 * @since 2022
 */

@RestController
public class DiscountController {
    
    @Autowired
    DiscountService discountService;

    @PostMapping(value="/discounts")
    public ResponseEntity<?> addDiscount(@RequestBody Discount discount){
        try {
          discountService.addDiscount(discount);
            return new ResponseEntity<Discount>(discount,HttpStatus.CREATED);
         }  catch (ConstraintViolationException e) {
              return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
         }    catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/discounts")
    public ResponseEntity<?> getAllBooks(){
       List<Discount> discounts = discountService.getDiscounts();
       return new  ResponseEntity<>(discounts,discounts.size()>0 ? HttpStatus.OK:HttpStatus.NOT_FOUND);
    }
}

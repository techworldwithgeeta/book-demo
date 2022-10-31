package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Book;
import com.example.demo.model.Discount;
import com.example.demo.services.DiscountService;

@Component
public class CommonUtility {
    
    @Autowired
    private DiscountService discountService;

    public void calulateDiscount(Book book) throws Exception{ 
        Discount discount = discountService.getDiscount(book.getType());

        Double discountPerc = discount.getDiscount();
        if(discountPerc > 0){
            Double totalPrice = book.getPrice() - ( book.getPrice() * discountPerc /100);
            book.setTotalPrice(totalPrice);
        }else{
            book.setTotalPrice(book.getPrice());
        }
    }
}

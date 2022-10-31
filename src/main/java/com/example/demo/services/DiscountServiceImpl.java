package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Discount;
import com.example.demo.repositories.DiscountRepository;

import lombok.NoArgsConstructor;

/**
 * DiscountService implementation to provide business logics for CRUD operations
 * @author Geeta Khatwani
 * @since 2022
 */

@Service
@NoArgsConstructor
public class DiscountServiceImpl  implements DiscountService{
    
    @Autowired
    private DiscountRepository discountRepository;
    
    @Override
    public void addDiscount(Discount discount) throws Exception {
        discountRepository.save(discount);
    }

    @Override
    public Discount getDiscount(String discountType) throws Exception{
       Optional<Discount> discount = discountRepository.findByDiscountType(discountType);
     if(!discount.isPresent()){
        throw new Exception("Invalid discount type " + discountType);
     }
     return discount.get();

    }

    @Override
    public List<Discount> getDiscounts() {
        List<Discount> discounts = (List<Discount>) discountRepository.findAll();
        if(discounts.size() > 0){
            return discounts;
        }
        return new ArrayList<Discount>();
    }

   
}

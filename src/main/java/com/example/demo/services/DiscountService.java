package com.example.demo.services;


import java.util.List;

import com.example.demo.model.Discount;

/**
 * DiscountService interface to provide business logics for CRUD operations
 * @author Geeta Khatwani
 * @since 2022
 */

public interface DiscountService {

    public void addDiscount( Discount discount) throws Exception;
    public Discount getDiscount(String discountType)  throws Exception;
    public List<Discount> getDiscounts();
    
}

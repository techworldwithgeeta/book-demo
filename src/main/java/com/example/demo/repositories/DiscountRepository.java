package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Discount;


/**
 * DiscountRepository to perform CRUD operations with database
 * @author Geeta Khatwani
 * @since 2022
 */

@Repository
public interface DiscountRepository  extends CrudRepository<Discount,Long>{
    
    Optional<Discount> findByDiscountType(String discountType);
}

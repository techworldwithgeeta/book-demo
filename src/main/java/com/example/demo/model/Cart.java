package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;


/**
 * Cart is a model class used as DTO.
 * @author Geeta Khatwani
 * @since 2022
 */

@Getter
@Setter
public class Cart {
   private Long id;
    private int quantity;
    private Long bookId;
}

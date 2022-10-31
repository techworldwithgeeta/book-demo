package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Discount is a model class to persist the data and used as DTO.
 * @author Geeta Khatwani
 * @since 2022
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String discountType;
   private String description;
   private Double discount;
}
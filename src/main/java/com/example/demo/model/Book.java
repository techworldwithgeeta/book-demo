package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Book is a model class to persist the data and it is used as DTO.
 * @author Geeta Khatwani
 * @since 2022
 */


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "name canot be null") 
    private String name;
    @NotNull  (message = "description can not be null") 
    private String description;
    @NotNull (message = "price can not be null") 
    private Double price;
    @NotNull(message = "author can not be null") 
    private String author;
    @NotNull (message = "booktype can not be null") 
    private String type;
    @NotNull (message = "isbn can not be null") 
    private String isbn;

    @JsonIgnore
    private Double totalPrice;
    
}

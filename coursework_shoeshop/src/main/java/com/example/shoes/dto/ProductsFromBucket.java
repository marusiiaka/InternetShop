package com.example.shoes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsFromBucket {
    private Long productId;
    private String category;
    private String description;
    private String manufacturer;
    private double price;
    private double size;
    private Integer quantity;
}

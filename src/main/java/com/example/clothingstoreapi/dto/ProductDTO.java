package com.example.clothingstoreapi.dto;

import com.example.clothingstoreapi.entity.Product;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private String image;
    private Product.Size size;
    private Product.Color color;
    private boolean isAddedToCart;
    private Product.Category category;
}

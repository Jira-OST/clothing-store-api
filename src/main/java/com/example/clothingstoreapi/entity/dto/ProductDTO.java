package com.example.clothingstoreapi.entity.dto;

import com.example.clothingstoreapi.entity.ProductEntity;
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
    private ProductEntity.Size size;
    private ProductEntity.Color color;
    private boolean isAddedToCart;
    private ProductEntity.Category category;
    private String description;
}

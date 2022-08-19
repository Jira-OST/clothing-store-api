package com.example.clothingstoreapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class ProductEntity {

    public static enum Size {
        Small, Medium, Large
    }
    public static enum Color {
        Red, Blue, Green, Yellow, White, Black
    }
    public static enum Category {
        food, clothes, coffee
    }

    public static enum ClothingCategory {
        tops, pants, dress
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_url")
    private String image;

    @Column(name = "size")
    private Size size;

    @Column(name = "color")
    private Color color;

    @Column(name = "is_added_to_cart")
    private boolean isAddedToCart;

    @Column(name = "category")
    private Category category;

    @Column(name = "clothing_category")
    private ClothingCategory clothingCategory;

    @Column(name = "description")
    private String description;


}

package com.example.clothingstoreapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

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
        tops, pants, dress
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    @NotNull(message = "Name cannot be null")
    private String name;
    @NotNull(message = "price cannot be null")
    @Column(name = "price")
    private Double price;
    @URL
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

    @Column(name = "description")
    private String description;


}

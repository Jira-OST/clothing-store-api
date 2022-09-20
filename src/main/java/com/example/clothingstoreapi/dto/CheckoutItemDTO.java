package com.example.clothingstoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CheckoutItemDTO {
    private String productName;
    private int  quantity;
    private double price;
    private Long productId;
    private Long userId;
}

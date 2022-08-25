package com.example.clothingstoreapi.dto;

import com.example.clothingstoreapi.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class UserCartDTO {
    private Set<ProductEntity> productsInCart;
}

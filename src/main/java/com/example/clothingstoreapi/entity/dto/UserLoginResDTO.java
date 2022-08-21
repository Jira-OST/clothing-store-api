package com.example.clothingstoreapi.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginResDTO {
    private String email;
    private String token;
}

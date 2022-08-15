package com.example.clothingstoreapi.dto;

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

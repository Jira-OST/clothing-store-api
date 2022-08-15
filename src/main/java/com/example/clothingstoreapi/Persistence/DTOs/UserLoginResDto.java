package com.example.clothingstoreapi.Persistence.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginResDto {
    private String email;
    private String token;
}

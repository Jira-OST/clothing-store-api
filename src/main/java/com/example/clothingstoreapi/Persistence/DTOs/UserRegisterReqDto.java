package com.example.clothingstoreapi.Persistence.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReqDto {
    private String fullName;
    private String email;
    private String password;
}

package com.example.clothingstoreapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProfileReqDTO {
    private String email;
    private String newFullName;
    private String newEmail;
    private String newPassword;
}

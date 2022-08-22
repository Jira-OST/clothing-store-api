package com.example.clothingstoreapi.service;


import com.example.clothingstoreapi.dto.UserProfileDTO;
import com.example.clothingstoreapi.entity.UserEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    ResponseEntity saveUser(UserProfileDTO user);
    UserProfileDTO getUser(String email);

    UserProfileDTO updateUser(String email, UserProfileDTO userProfile);

    List<UserEntity> getAllUsers();
}

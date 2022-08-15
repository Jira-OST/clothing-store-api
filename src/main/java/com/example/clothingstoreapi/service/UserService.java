package com.example.clothingstoreapi.service;


import com.example.clothingstoreapi.dto.UserRegisterReqDTO;
import com.example.clothingstoreapi.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserEntity saveUser(UserRegisterReqDTO user);
    UserEntity getUser(String email);

    List<UserEntity> getAllUsers();
}

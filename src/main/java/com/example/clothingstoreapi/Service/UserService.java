package com.example.clothingstoreapi.Service;


import com.example.clothingstoreapi.Persistence.DTOs.UserRegisterReqDto;
import com.example.clothingstoreapi.Persistence.Entities.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserEntity saveUser(UserRegisterReqDto user);
    UserEntity getUser(String email);

    List<UserEntity> getAllUsers();
}

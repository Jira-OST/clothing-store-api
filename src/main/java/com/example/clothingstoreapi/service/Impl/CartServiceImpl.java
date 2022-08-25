package com.example.clothingstoreapi.service.Impl;

import com.example.clothingstoreapi.dto.UserCartDTO;
import com.example.clothingstoreapi.entity.UserEntity;
import com.example.clothingstoreapi.repository.UserRepository;
import com.example.clothingstoreapi.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserCartDTO getUserCart(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            UserCartDTO userCart = modelMapper.map(userEntity.get(), UserCartDTO.class);
            return userCart;
        } else {
            throw new UsernameNotFoundException("Email : " + email + " not found.");
        }

    }
}

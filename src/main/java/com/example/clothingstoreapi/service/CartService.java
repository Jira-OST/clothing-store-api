package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.UserCartDTO;


import java.util.List;

public interface CartService {
    public UserCartDTO getUserCart(String email);
}

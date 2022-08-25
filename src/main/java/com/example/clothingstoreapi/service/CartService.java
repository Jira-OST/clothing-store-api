package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.UserCartDTO;


import java.util.List;

public interface CartService {
    public UserCartDTO getUserCart(String email);
    public boolean addToCart(String email, Long id);
    public boolean removeFromCart(String email, Long id);
    public boolean clearCart(String email);
}

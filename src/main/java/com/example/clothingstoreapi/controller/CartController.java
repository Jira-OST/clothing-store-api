package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.security.Util.JwtUtil;
import com.example.clothingstoreapi.service.Impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    private CartServiceImpl cartService;
    @Autowired
    JwtUtil jwtUtil;
    @GetMapping("/usercart")
    public ResponseEntity getCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(cartService.getUserCart(email));
    }
}

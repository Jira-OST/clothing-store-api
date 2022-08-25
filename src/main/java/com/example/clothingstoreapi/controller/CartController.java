package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.security.Util.JwtUtil;
import com.example.clothingstoreapi.service.Impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/add")
    public ResponseEntity addToCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                    @RequestParam Long id) {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(cartService.addToCart(email, id));
    }
    @GetMapping("/clear")
    public ResponseEntity clearCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(cartService.clearCart(email));
    }

    @GetMapping("/remove")
    public ResponseEntity removeFromCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                    @RequestParam Long id) {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(cartService.removeFromCart(email, id));
    }
}

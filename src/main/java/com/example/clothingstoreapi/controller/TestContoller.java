package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.security.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {

    @Autowired
    JwtUtil jwtUtil;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/api")
    public ResponseEntity<?> testApi(){
        return ResponseEntity.ok().body("Hello from api Router");
    }
}

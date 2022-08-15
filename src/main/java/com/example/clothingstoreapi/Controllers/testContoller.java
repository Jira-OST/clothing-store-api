package com.example.clothingstoreapi.Controllers;

import com.example.clothingstoreapi.Security.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testContoller {

    @Autowired
    JwtUtil jwtUtil;

    @GetMapping("/api")
    public ResponseEntity<?> testApi(){
        return ResponseEntity.ok().body("Hello from api Router");
    }
}

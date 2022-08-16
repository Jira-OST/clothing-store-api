package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.UserLoginReqDTO;
import com.example.clothingstoreapi.dto.UserLoginResDTO;
import com.example.clothingstoreapi.dto.UserProfileDTO;
import com.example.clothingstoreapi.security.Util.JwtUtil;
import com.example.clothingstoreapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody UserProfileDTO userRegisterReq){
        return userService.saveUser(userRegisterReq);
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReqDTO userLoginReq) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginReq.getEmail(), userLoginReq.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Invalid email/password");
        }
        return ResponseEntity.ok().body(new UserLoginResDTO(userLoginReq.getEmail(), jwtUtil.generateToken(userLoginReq.getEmail())));
    }

    // For testing only
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}

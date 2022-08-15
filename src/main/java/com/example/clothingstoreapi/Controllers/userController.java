package com.example.clothingstoreapi.Controllers;

import com.example.clothingstoreapi.Persistence.DTOs.UserLoginReqDto;
import com.example.clothingstoreapi.Persistence.DTOs.UserLoginResDto;
import com.example.clothingstoreapi.Persistence.DTOs.UserRegisterReqDto;
import com.example.clothingstoreapi.Persistence.Entities.UserEntity;
import com.example.clothingstoreapi.Security.Util.JwtUtil;
import com.example.clothingstoreapi.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class userController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterReqDto userRegisterReq){
        userService.saveUser(userRegisterReq);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody UserLoginReqDto userLoginReq) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userLoginReq.getEmail(), userLoginReq.getPassword())
            );
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Invalid email/password");
        }
        return ResponseEntity.ok().body(new UserLoginResDto(userLoginReq.getEmail(), jwtUtil.generateToken(userLoginReq.getEmail())));
    }

    // For testing only
    @GetMapping("/users")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }
}

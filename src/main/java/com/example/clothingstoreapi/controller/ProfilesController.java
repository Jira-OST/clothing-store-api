package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.UserProfileDTO;
import com.example.clothingstoreapi.security.Util.JwtUtil;
import com.example.clothingstoreapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class ProfilesController {

    @Autowired
    UserService userService;
    @Autowired
    JwtUtil jwtUtil;
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@Valid @RequestBody UserProfileDTO profile,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(userService.updateUser(email, profile));
    }
    @GetMapping("/profile")
    public ResponseEntity getProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token);
        return ResponseEntity.ok().body(userService.getUser(email));
    }

    @PutMapping("/profile/picture")
    public ResponseEntity updatePicture(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                        @RequestParam("image") MultipartFile image) throws IOException {
        token = token.substring(7);
        String email = jwtUtil.extractUsername(token);
        byte[] picture = image.getBytes();
        return ResponseEntity.ok().body(userService.updatePicture(email, picture));

    }

}

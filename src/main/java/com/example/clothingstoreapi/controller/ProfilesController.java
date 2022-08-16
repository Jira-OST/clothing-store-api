package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.UpdateProfileReqDTO;
import com.example.clothingstoreapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProfilesController {

    @Autowired
    UserService userService;
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileReqDTO user) {
        return userService.updateUser(user);
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/profile")
    public ResponseEntity getProfile(@RequestParam String email) {
        return userService.getUser(email);
    }

}

package com.example.clothingstoreapi.service.Impl;

import com.example.clothingstoreapi.dto.UserProfileDTO;
import com.example.clothingstoreapi.entity.UserEntity;
import com.example.clothingstoreapi.repository.UserRepository;
import com.example.clothingstoreapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(username).orElseThrow(() ->
            new UsernameNotFoundException("User not found in the database")
        );
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }

    @Override
    public List<UserEntity> getAllUsers() {
        return (List<UserEntity>) userRepo.findAll();
    }

    @Override
    public ResponseEntity saveUser(UserProfileDTO user) {
        log.info("Saving new user {} to the database", user.getFullName());
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userRepo.save(userEntity);
        return ResponseEntity.ok().body(user);
    }

    @Override
    public UserProfileDTO updateUser(String email, UserProfileDTO userProfile) {
        log.info("Updating user with email: {}", email);
        UserEntity user;
        try {
            user = userRepo.findByEmail(email).get();
        } catch (Exception e) {
            log.error("Email ({}) extracted from token is not valid ", email);
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Invalid token", e);
        }
        user.setEmail(userProfile.getEmail());
        user.setPassword(userProfile.getPassword());
        user.setFullName(userProfile.getFullName());
        userRepo.save(user);
        UserProfileDTO userProfileDTO = modelMapper.map(user, UserProfileDTO.class);
        return userProfileDTO;
    }

    @Override
    public UserProfileDTO getUser(String email) {
        log.info("Fetching user by email: {}", email);

        UserEntity user =  userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Email : " + email + " not found.")
                );
        UserProfileDTO userProfileDTO = modelMapper.map(user, UserProfileDTO.class);
        return userProfileDTO;
    }
}

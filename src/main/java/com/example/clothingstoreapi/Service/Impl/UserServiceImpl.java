package com.example.clothingstoreapi.Service.Impl;

import com.example.clothingstoreapi.Persistence.DTOs.UserRegisterReqDto;
import com.example.clothingstoreapi.Persistence.Entities.UserEntity;
import com.example.clothingstoreapi.Persistence.Repositories.UserRepository;
import com.example.clothingstoreapi.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// @Transactional ask
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
    public UserEntity saveUser(UserRegisterReqDto user) {
        log.info("Saving new user {} to the database", user.getFullName());
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userRepo.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity getUser(String email) {
        log.info("Fetching user by email: {}", email);

        UserEntity user =  userRepo.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Email : " + email + " not found.")
                );
        return user;
    }
}

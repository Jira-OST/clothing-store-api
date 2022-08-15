package com.example.clothingstoreapi.service.Impl;

import com.example.clothingstoreapi.dto.UserRegisterReqDTO;
import com.example.clothingstoreapi.entity.UserEntity;
import com.example.clothingstoreapi.repository.UserRepository;
import com.example.clothingstoreapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public UserEntity saveUser(UserRegisterReqDTO user) {
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

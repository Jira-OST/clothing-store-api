package com.example.clothingstoreapi.security.Service;

import com.example.clothingstoreapi.entity.RoleEntity;
import com.example.clothingstoreapi.entity.UserEntity;
import com.example.clothingstoreapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepo.findByEmail(email).get();
        if (user == null) {

        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapToGrantedAuthorities(user.getRoles()));
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<RoleEntity> roles) {
        if(roles != null && !roles.isEmpty()) {
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        }

        return new ArrayList<GrantedAuthority>();

    }
}

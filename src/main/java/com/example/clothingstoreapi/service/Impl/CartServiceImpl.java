package com.example.clothingstoreapi.service.Impl;

import com.example.clothingstoreapi.dto.UserCartDTO;
import com.example.clothingstoreapi.entity.ProductEntity;
import com.example.clothingstoreapi.entity.UserEntity;
import com.example.clothingstoreapi.exception.ProductNotFoundException;
import com.example.clothingstoreapi.repository.ProductRepository;
import com.example.clothingstoreapi.repository.UserRepository;
import com.example.clothingstoreapi.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public UserCartDTO getUserCart(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            UserCartDTO userCart = modelMapper.map(userEntity.get(), UserCartDTO.class);
            return userCart;
        } else {
            throw new UsernameNotFoundException("Email : " + email + " not found.");
        }

    }

    @Override
    public boolean addToCart(String email, Long id) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Email : " + email + " not found."));
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException());
        Set newCart = userEntity.getProductsInCart();
        newCart.add(productEntity);
        userEntity.setProductsInCart(newCart);
        userRepository.save(userEntity);
        return true;
    }

    @Override
    public boolean removeFromCart(String email, Long id) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Email : " + email + " not found."));
        Set cart = userEntity.getProductsInCart();
        for (Object o : cart) {
            ProductEntity productEntity = (ProductEntity) o;
            if (productEntity.getId() == id) {
                cart.remove(o);
                userEntity.setProductsInCart(cart);
                userRepository.save(userEntity);
                return true;
            }
        }
        throw new ProductNotFoundException();
    }

    @Override
    public boolean clearCart(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Email : " + email + " not found."));
        userEntity.setProductsInCart(null);
        userRepository.save(userEntity);

        return true;
    }
}

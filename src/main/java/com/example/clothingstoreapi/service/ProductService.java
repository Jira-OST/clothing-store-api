package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.ProductDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProduct();
    public ProductDTO getProductById(Long id);
    public ProductDTO createNewProduct(ProductDTO newProduct);
    public boolean deleteProductById(Long id);
    public boolean addProductToCart(Long id);
    public boolean removeProductFromCart(Long id);

}

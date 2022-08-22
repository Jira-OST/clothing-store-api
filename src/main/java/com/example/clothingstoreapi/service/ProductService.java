package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.entity.ProductEntity;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProduct();
    public List<ProductDTO> getProductsByCategory(ProductEntity.Category category);
    public ProductDTO getProductById(Long id);
    public ProductDTO createNewProduct(ProductDTO newProduct);
    public boolean deleteProductById(Long id);
    public boolean addProductToCart(Long id);
    public boolean removeProductFromCart(Long id);

}

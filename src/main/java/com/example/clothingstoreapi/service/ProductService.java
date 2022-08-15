package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProduct();
    public ProductDTO getProductById(Long id);
    public ProductDTO createNewProduct(ProductDTO newProduct);
    public boolean deleteProductById(Long id);
}

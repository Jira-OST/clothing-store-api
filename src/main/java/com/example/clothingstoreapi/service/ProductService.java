package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.ClothingProductDTO;
import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.entity.ProductEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAllProduct();
    public ResponseEntity getProductsByClothingCategory(ProductEntity.ClothingCategory clothingCategory);
    public ProductDTO getProductById(Long id);
    public ProductDTO createNewProduct(ProductDTO newProduct);
    public boolean deleteProductById(Long id);
}

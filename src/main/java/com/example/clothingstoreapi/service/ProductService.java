package com.example.clothingstoreapi.service;

import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.entity.ProductEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Page<ProductDTO> getAllProduct(int pageNumber, int pageSize,Boolean sortedByPrice, Boolean isDESC);
    public List<ProductDTO> getProductsByCategory(ProductEntity.Category category);
    public ProductDTO getProductById(Long id);
    public ProductDTO createNewProduct(ProductDTO newProduct);
    public boolean deleteProductById(Long id);
    public boolean addProductToCart(Long id);
    public boolean removeProductFromCart(Long id);

}

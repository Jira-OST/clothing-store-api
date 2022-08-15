package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.entity.Product;
import com.example.clothingstoreapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all")
    public List<ProductDTO> getAllProduct() {
        return productService.getAllProduct();
    }
    @GetMapping("/product")
    public ProductDTO getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }
    @PostMapping("/add")
    public ProductDTO createNewProduct(@RequestBody ProductDTO newProduct) {
        return productService.createNewProduct(newProduct);
    }
    @DeleteMapping("/del/{id}")
    public boolean deleteProductById(@PathVariable Long id) {
        return  productService.deleteProductById(id);
    }

}

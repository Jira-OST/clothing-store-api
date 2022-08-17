package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.exception.ProductNotFoundException;
import com.example.clothingstoreapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/product/v1")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    public List<ProductDTO> getAllProduct(HttpServletResponse response) {
        return productService.getAllProduct();
    }
    @GetMapping("/product")
    public ProductDTO getProductById(@RequestParam Long id) {
        ProductDTO productDTO = productService.getProductById(id);
        if (productDTO == null) {
            throw new ProductNotFoundException();
        }
        return productDTO;
    }
    @PostMapping("/add")
    public ProductDTO createNewProduct(@RequestBody ProductDTO newProduct) {
        return productService.createNewProduct(newProduct);
    }
    @DeleteMapping("/del/{id}")
    public boolean deleteProductById(@PathVariable Long id) {
        return  productService.deleteProductById(id);
    }
    @PutMapping("/addtocart")
    public ResponseEntity<?> addProductToCart(@RequestParam Long id) {
        if (productService.addProductToCart(id)) {
            return ResponseEntity.ok().body("Product Added Succesfully!");
        }
        return ResponseEntity.badRequest().body(null);
    }
    @PutMapping("/removefromcart")
    public ResponseEntity<?> removeProductFromCart(@RequestParam Long id) {
        if (productService.removeProductFromCart(id)) {
            return ResponseEntity.ok().body("Product removed Succesfully!");
        }
        return ResponseEntity.badRequest().body(null);
    }

}

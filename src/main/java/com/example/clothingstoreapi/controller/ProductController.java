package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.entity.ProductEntity;
import com.example.clothingstoreapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity getProductsByCategory(@RequestParam(required = false) ProductEntity.Category category,
                                                @RequestParam int pageNumber,
                                                @RequestParam int pageSize,
                                                @RequestParam Boolean sortedByPrice,
                                                @RequestParam Boolean isDESC) {
        if (category != null) {
            return ResponseEntity.ok().body(productService.getProductsByCategory(category));
        } else {
            return ResponseEntity.ok().body(productService.getAllProduct(pageNumber, pageSize, sortedByPrice, isDESC));
        }
    }

    @GetMapping("/product")
    public ResponseEntity getProductById(@RequestParam Long id) {
        return ResponseEntity.ok().body(productService.getProductById(id));
    }
    @PostMapping("/add")
    public ResponseEntity createNewProduct(@Valid @RequestBody ProductDTO newProduct) {
        return ResponseEntity.ok().body(productService.createNewProduct(newProduct));
    }
    @DeleteMapping("/del/{id}")
    public ResponseEntity deleteProductById(@PathVariable Long id) {
        return  ResponseEntity.ok().body(productService.deleteProductById(id));
    }
    @PutMapping("/addtocart")
    public ResponseEntity addProductToCart(@RequestParam Long id) {
        return ResponseEntity.ok().body(productService.addProductToCart(id));
    }
    @PutMapping("/removefromcart")
    public ResponseEntity removeProductFromCart(@RequestParam Long id) {
        return ResponseEntity.ok().body(productService.removeProductFromCart(id));
    }

}

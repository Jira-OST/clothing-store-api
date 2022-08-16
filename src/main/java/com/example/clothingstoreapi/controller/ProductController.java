package com.example.clothingstoreapi.controller;

import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/product/v2")
public class ProductController {
    @Autowired
    private ProductService productService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/all")
    public List<ProductDTO> getAllProduct(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, PATCH, DELETE");
        response.addHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        return productService.getAllProduct();
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/product")
    public ProductDTO getProductById(@RequestParam Long id) {
        return productService.getProductById(id);
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping("/add")
    public ProductDTO createNewProduct(@RequestBody ProductDTO newProduct) {
        return productService.createNewProduct(newProduct);
    }
    @CrossOrigin(origins = "*", maxAge = 3600)
    @DeleteMapping("/del/{id}")
    public boolean deleteProductById(@PathVariable Long id) {
        return  productService.deleteProductById(id);
    }

}

package com.example.clothingstoreapi.service.Impl;

import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.entity.Product;
import com.example.clothingstoreapi.repository.ProductRepository;
import com.example.clothingstoreapi.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    public List<ProductDTO> getAllProduct() {
        List<Product> products = (List<Product>) productRepository.findAll();
        List<ProductDTO> productDTOList = null;

        if(!products.isEmpty()) {
            productDTOList = new ArrayList<>();
            ProductDTO productDTO = null;
            for (Product product : products) {
                productDTO = new ProductDTO();
                BeanUtils.copyProperties(product, productDTO);
                productDTOList.add(productDTO);
            }
        }

        return productDTOList;
    }

    public ProductDTO getProductById(Long id) {
        ProductDTO productDTO = null;
        if (id != null) {
            productDTO = new ProductDTO();
            Product product = productRepository.findById(id).get();
            BeanUtils.copyProperties(product, productDTO);
        }
        return productDTO;
    }

    public ProductDTO createNewProduct(ProductDTO newProduct) {
        Product product = null;
        if (newProduct != null) {
            product = new Product();
            BeanUtils.copyProperties(newProduct, product);
            productRepository.save(product);
        }
        return newProduct;
    }

    public boolean deleteProductById(Long id) {
        if (id != null) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

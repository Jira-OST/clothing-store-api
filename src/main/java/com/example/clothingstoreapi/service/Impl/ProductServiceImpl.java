package com.example.clothingstoreapi.service.Impl;

import com.example.clothingstoreapi.dto.ClothingProductDTO;
import com.example.clothingstoreapi.dto.ProductDTO;
import com.example.clothingstoreapi.entity.ProductEntity;
import com.example.clothingstoreapi.exception.ProductNotFoundException;
import com.example.clothingstoreapi.repository.ProductRepository;
import com.example.clothingstoreapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.parameters.P;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;



    public List<ProductDTO> getAllProduct() {
        log.info("fetching all products");

        List<ProductEntity> productEntities = (List<ProductEntity>) productRepository.findAll();
        List<ProductDTO> productDTOList = null;

        if(!productEntities.isEmpty()) {
            productDTOList = new ArrayList<>();
            ProductDTO productDTO = null;
            for (ProductEntity productEntity : productEntities) {
                productDTO = new ProductDTO();
                BeanUtils.copyProperties(productEntity, productDTO);
                productDTOList.add(productDTO);
            }
        }

        return productDTOList;
    }

    public ProductDTO getProductById(Long id) {
        log.info("fetching product with id: {}", id);

        ProductDTO productDTO = null;
        if (id != null) {
            if (productRepository.findById(id).isPresent()) {
                productDTO = new ProductDTO();
                ProductEntity productEntity = productRepository.findById(id).get();
                BeanUtils.copyProperties(productEntity, productDTO);
            }
        }
        return productDTO;
    }

    @Override
    public ResponseEntity getProductsByClothingCategory(ProductEntity.ClothingCategory clothingCategory) {
        log.info("fetching products of clothing category: {}", clothingCategory);

        List<ProductEntity> productEntityList;
        try{
            productEntityList = productRepository.getProductEntityByClothingCategory(clothingCategory);
        } catch (Exception e){
            return ResponseEntity.internalServerError().body(e);
        }
        List<ClothingProductDTO> clothingProductDTOList = new ArrayList<>();
        productEntityList.forEach(product -> {
            ClothingProductDTO clothingProductDTO = modelMapper.map(product, ClothingProductDTO.class);
            clothingProductDTO.setCategory(product.getClothingCategory());
            clothingProductDTOList.add(clothingProductDTO);
        });
        return ResponseEntity.ok().body(clothingProductDTOList);

    }

    public ProductDTO createNewProduct(ProductDTO newProduct) {
        log.info("Creating a product with the following data: {}", newProduct);
        ProductEntity productEntity = null;
        if (newProduct != null) {
            productEntity = new ProductEntity();
            BeanUtils.copyProperties(newProduct, productEntity);
            productRepository.save(productEntity);
        }
        return newProduct;
    }

    public boolean deleteProductById(Long id) {
        log.info("deleting product with id: {}", id);
        if (id != null) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean addProductToCart(Long id) {
        log.info("Adding product with id: {} to the cart", id);
            if (productRepository.findById(id).isPresent()) {
                ProductEntity productEntity = productRepository.findById(id).get();
                productEntity.setAddedToCart(true);
                productRepository.save(productEntity);
                return true;
            }
        log.error("Product with id: {} not found", id);
            throw new ProductNotFoundException();

    }

    @Override
    public boolean removeProductFromCart(Long id) {
        log.info("Removing product with id: {} from the cart", id);
        if (productRepository.findById(id).isPresent()) {
            ProductEntity productEntity = productRepository.findById(id).get();
            productEntity.setAddedToCart(false);
            productRepository.save(productEntity);
            return true;
        }
        log.error("Product with id: {} not found", id);
        throw new ProductNotFoundException();
    }

}

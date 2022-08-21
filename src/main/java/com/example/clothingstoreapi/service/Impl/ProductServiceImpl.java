package com.example.clothingstoreapi.service.Impl;

import com.example.clothingstoreapi.entity.dto.ProductDTO;
import com.example.clothingstoreapi.entity.ProductEntity;
import com.example.clothingstoreapi.exception.ProductNotFoundException;
import com.example.clothingstoreapi.repository.ProductRepository;
import com.example.clothingstoreapi.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.parameters.P;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
    public List<ProductDTO> getProductsByCategory(ProductEntity.Category category) {
        log.info("fetching products of clothing category: {}", category);

        List<ProductEntity> productEntityList;
        try{
            productEntityList = productRepository.getProductEntityByCategory(category);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong while trying to get products!", e);
        }
        List<ProductDTO> ProductDTOList = new ArrayList<>();
        productEntityList.forEach(product -> {
            ProductDTO clothingProductDTO = modelMapper.map(product, ProductDTO.class);
            ProductDTOList.add(clothingProductDTO);
        });
        return ProductDTOList;

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

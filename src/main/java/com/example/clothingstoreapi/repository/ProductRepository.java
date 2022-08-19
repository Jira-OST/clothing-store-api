package com.example.clothingstoreapi.repository;

import com.example.clothingstoreapi.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    public List<ProductEntity> getProductEntityByClothingCategory(ProductEntity.ClothingCategory clothingCategory);

}

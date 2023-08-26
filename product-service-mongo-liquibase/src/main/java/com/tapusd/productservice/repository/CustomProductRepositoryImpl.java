package com.tapusd.productservice.repository;

import com.tapusd.productservice.document.Product;
import com.tapusd.productservice.dto.ProductDTO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomProductRepositoryImpl implements CustomProductRepository {

    private final MongoTemplate mongoTemplate;

    public CustomProductRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ProductDTO> customFindAll() {
        return mongoTemplate.findAll(ProductDTO.class);
    }
}

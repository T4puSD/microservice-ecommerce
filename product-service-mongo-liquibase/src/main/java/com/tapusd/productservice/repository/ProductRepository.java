package com.tapusd.productservice.repository;

import com.tapusd.productservice.document.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends
        MongoRepository<Product, ObjectId> , CustomProductRepository{
}

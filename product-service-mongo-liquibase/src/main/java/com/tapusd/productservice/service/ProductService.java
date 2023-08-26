package com.tapusd.productservice.service;

import com.tapusd.productservice.document.Product;
import com.tapusd.productservice.dto.ProductDTO;
import org.bson.types.ObjectId;

import java.util.List;

public interface ProductService {

    Product save(ProductDTO dto);

    void save(Product product);

    List<ProductDTO> findAll();

    ProductDTO findById(ObjectId id);
}

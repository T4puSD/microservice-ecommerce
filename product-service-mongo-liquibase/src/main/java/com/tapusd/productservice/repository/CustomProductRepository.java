package com.tapusd.productservice.repository;

import com.tapusd.productservice.dto.ProductDTO;

import java.util.List;

public interface CustomProductRepository {

    List<ProductDTO> customFindAll();
}

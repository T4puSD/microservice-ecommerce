package com.tapusd.productservice.adapter;

import com.tapusd.productservice.domain.Product;
import com.tapusd.productservice.dto.response.ProductDTO;

public class ProductAdapter {

    private ProductAdapter() {

    }

    public static ProductDTO convertToDTO(Product product) {
        return new ProductDTO(product.getId(),
                product.getName());
    }
}

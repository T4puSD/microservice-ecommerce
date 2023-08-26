package com.tapusd.productservice.service;

import com.tapusd.productservice.document.Product;
import com.tapusd.productservice.dto.ProductDTO;
import com.tapusd.productservice.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(ProductDTO dto) {
        var product = new Product()
                .setName(dto.name())
                .setPrice(dto.price());

        return productRepository.save(product);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        return productRepository.customFindAll();
    }

    @Override
    public ProductDTO findById(ObjectId id) {
        return null;
    }
}

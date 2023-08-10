package com.tapusd.productservice.controller;

import com.tapusd.productservice.adapter.ProductAdapter;
import com.tapusd.productservice.domain.Product;
import com.tapusd.productservice.dto.request.CreateProductDTO;
import com.tapusd.productservice.dto.response.ProductDTO;
import com.tapusd.productservice.exception.NotFoundException;
import com.tapusd.productservice.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductRestController {

    private final ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<ProductDTO> getProducts() {
        return productRepository.findAll().stream()
                .map(ProductAdapter::convertToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductDTO getProduct(@PathVariable Long id) {
        return productRepository.findById(id)
                .map(ProductAdapter::convertToDTO)
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO saveProduct(@RequestBody CreateProductDTO dto) {

        var customer = new Product();
        customer.setName(dto.name());

        Product savedProduct = productRepository.save(customer);
        return ProductAdapter.convertToDTO(savedProduct);
    }
}

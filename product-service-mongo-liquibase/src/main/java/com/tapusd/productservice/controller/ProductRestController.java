package com.tapusd.productservice.controller;

import com.tapusd.productservice.document.Product;
import com.tapusd.productservice.dto.ProductDTO;
import com.tapusd.productservice.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.findAll();
    }

    @PostMapping
    public Product save(@RequestBody ProductDTO dto) {
        return productService.save(dto);
    }
}

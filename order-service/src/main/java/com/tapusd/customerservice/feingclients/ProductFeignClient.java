package com.tapusd.customerservice.feingclients;


import com.tapusd.customerservice.dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "productservice", path = "/api/v1/products")
public interface ProductFeignClient {

    @GetMapping
    List<ProductDTO> getProducts();

    @GetMapping("/{productId}")
    ProductDTO getProduct(@PathVariable long productId);
}

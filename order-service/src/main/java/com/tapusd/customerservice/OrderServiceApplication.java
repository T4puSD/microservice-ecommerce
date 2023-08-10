package com.tapusd.customerservice;

import com.tapusd.customerservice.dto.response.ProductDTO;
import com.tapusd.customerservice.feingclients.ProductFeignClient;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @Bean
    public ApplicationRunner runner(ProductFeignClient productFeignClient) {
        return args -> {
            List<ProductDTO> products = productFeignClient.getProducts();
            for (ProductDTO product : products) {
                System.out.println(product);
            }
        };
    }

}

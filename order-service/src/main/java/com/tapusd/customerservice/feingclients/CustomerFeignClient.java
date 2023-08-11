package com.tapusd.customerservice.feingclients;

import com.tapusd.customerservice.dto.CustomerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "customerservice", path = "/api/v1/customers")
public interface CustomerFeignClient {

    @GetMapping
    List<CustomerDTO> getCustomers();

    @GetMapping("/{id}")
    CustomerDTO getCustomer(@PathVariable Long id);
}

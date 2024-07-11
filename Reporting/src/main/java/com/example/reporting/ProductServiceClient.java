package com.example.reporting;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "PRODUCT")
public interface ProductServiceClient {

    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    Optional<ProductDTO> findById(@PathVariable Long id);
}

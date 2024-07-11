package com.example.reporting;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "ORDER")
public interface OrderServiceClient {
    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    List<OrderDTO> getOrders();
}

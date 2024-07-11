package com.example.reporting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportingService {

    @Autowired
    ProductServiceClient productServiceClient;
    @Autowired
    OrderServiceClient orderServiceClient;
    public List<ProductDTO> getTopSellingProducts() {
        List<OrderDTO> orders = orderServiceClient.getOrders();

        Map<ProductDTO, Long> productCount = orders.stream()
                .flatMap(order -> order.getProductIds().stream())
                .collect(Collectors.groupingBy(productID -> {
                    Optional<ProductDTO> productDTO =  productServiceClient.findById(productID);
                    return productDTO.orElse(null);
                }, Collectors.counting()));

        return productCount.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}

package com.example.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public OrderEntity createOrder(OrderEntity order) {
        return orderRepository.save(order);
    }

    public OrderEntity getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<OrderEntity> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderEntity updateOrder(Long id, OrderEntity orderDetails) {
        OrderEntity order = getOrderById(id);
        order.setProductIds(orderDetails.getProductIds());
        order.setTotalPrice(orderDetails.getTotalPrice());
        order.setStatus(orderDetails.getStatus());
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        OrderEntity order = getOrderById(id);
        orderRepository.delete(order);
    }

    public OrderEntity updateOrderStatus(Long id, String status) {
        OrderEntity order = getOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }
}

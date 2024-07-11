package com.example.payment.Service;




import com.example.payment.Model.OrderEntity;
import com.example.payment.Model.Payment;
import com.example.payment.Model.ProductServiceClient;
import com.example.payment.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductServiceClient productServiceClient;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        double totalAmount = calculateTotalAmount(payment.getOrder());
        payment.setAmount(totalAmount);

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    private double calculateTotalAmount(OrderEntity order) {
        return order.getProductIds().stream()
                .map((orderID) ->  productServiceClient.findById(orderID))
                .mapToDouble(product -> product.get().getPrice())
                .sum();
    }
}

package com.example.payment;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

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

    private double calculateTotalAmount(Order order) {
        return order.getProducts().stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}

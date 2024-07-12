package com.example.payment.Service;




import com.example.payment.Model.*;
import com.example.payment.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    //@Autowired
    private ProductServiceClient productServiceClient;
    //@Autowired
    private NotificationServiceClient notificationServiceClient;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Payment getPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment createPayment(Payment payment) {
        try{
            double totalAmount = calculateTotalAmount(payment.getOrder());
            payment.setAmount(totalAmount);
            payment.getOrder().setStatus("Accepted");
            return paymentRepository.save(payment);
        }catch(Exception e){
            payment.getOrder().setStatus("Canceled");
            notificationServiceClient.createNotification(new NotificationDTO(payment.getOrder().getCustomerId(), e.getMessage()));
        }
        return null;
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

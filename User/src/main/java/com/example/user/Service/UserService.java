package com.example.user.Service;

import com.example.user.DTO.OrderDTO;
import com.example.user.DTO.ProductDTO;
import com.example.user.Model.Auser;
import com.example.user.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.ErrorResponseException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    ProductServiceClient productServiceClient;

    OrderServiceClient orderServiceClient;
    public List<Auser> getAllUsers() {
        return userRepository.findAll();
    }

    public Auser getUserById(Long id){
        return userRepository.findById(id).orElse(null);
    }


    public Auser createUser(Auser auser){
        return userRepository.save(auser);
    }

    public Auser updateUser(Long id, Auser userDetails){
        Auser user = userRepository.findById(id).orElseThrow(() -> new ErrorResponseException(HttpStatus.NOT_FOUND));
        user.setUserName(userDetails.getUserName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<ProductDTO> findProduct(Long id){
        return productServiceClient.findById(id);
    }
    public List<ProductDTO> getRecommendation() {
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

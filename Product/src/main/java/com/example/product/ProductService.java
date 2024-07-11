package com.example.product;
import com.example.product.ProductEntity;
import com.example.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public ProductEntity createProduct(ProductEntity product) {
        return productRepository.save(product);
    }



    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }


}

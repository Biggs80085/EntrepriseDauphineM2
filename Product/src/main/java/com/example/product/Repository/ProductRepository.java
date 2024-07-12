package com.example.product.Repository;


import com.example.product.Model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}

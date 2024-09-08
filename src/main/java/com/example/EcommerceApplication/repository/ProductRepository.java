package com.example.EcommerceApplication.repository;

import com.example.EcommerceApplication.entity.UserRoles;
import com.example.EcommerceApplication.entity.ecommerce.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}


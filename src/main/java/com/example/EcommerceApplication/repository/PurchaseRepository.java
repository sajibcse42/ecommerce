package com.example.EcommerceApplication.repository;

import com.example.EcommerceApplication.entity.ecommerce.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,Long> {
}

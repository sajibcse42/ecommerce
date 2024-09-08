package com.example.EcommerceApplication.repository;

import com.example.EcommerceApplication.entity.User;
import com.example.EcommerceApplication.entity.ecommerce.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    List<WishList> findByUserId(Long customerId);

}


package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.entity.ecommerce.WishList;

import java.util.List;

public interface WishListService {
    CommonRestResponse getWishListByCustomerId(Long customerId);
    CommonRestResponse addProductsToWishList(List<Long> productIds);
}

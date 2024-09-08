package com.example.EcommerceApplication.controller;

import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.entity.ecommerce.WishList;
import com.example.EcommerceApplication.service.WishListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishlist")
@AllArgsConstructor
public class WishListController {

    private final WishListService wishListService;


    @GetMapping("/{customerId}")
    public CommonRestResponse getWishList(@PathVariable Long customerId) {
        CommonRestResponse commonRestResponse = wishListService.getWishListByCustomerId(customerId);
        return commonRestResponse;
    }

    @PostMapping("/add-multiple")
    public CommonRestResponse addProductsToWishList(@RequestBody List<Long> productIds) {
        CommonRestResponse commonRestResponse = wishListService.addProductsToWishList( productIds);
        return commonRestResponse;
    }

}

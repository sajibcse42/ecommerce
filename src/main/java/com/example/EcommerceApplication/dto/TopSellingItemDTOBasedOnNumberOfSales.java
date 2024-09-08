package com.example.EcommerceApplication.dto;

import lombok.Data;

@Data
public class TopSellingItemDTOBasedOnNumberOfSales {
    private Long productId;
    private String productName;
    private Long quantity;

    public TopSellingItemDTOBasedOnNumberOfSales(Long productId, String productName, Long quantity) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }


}

package com.example.EcommerceApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class TopSellingItemDTO {
    private Long productId;
    private String productName;
    private BigDecimal totalAmount;

    public TopSellingItemDTO(Long productId, String productName, BigDecimal totalAmount) {
        this.productId = productId;
        this.productName = productName;
        this.totalAmount = totalAmount;
    }

}

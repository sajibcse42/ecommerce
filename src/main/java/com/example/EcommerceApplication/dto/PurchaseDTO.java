package com.example.EcommerceApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PurchaseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}

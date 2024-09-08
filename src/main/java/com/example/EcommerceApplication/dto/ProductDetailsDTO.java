package com.example.EcommerceApplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDetailsDTO {
    private String name;
    private BigDecimal price;
}

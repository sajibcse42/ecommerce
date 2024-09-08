package com.example.EcommerceApplication.dto;

import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.SqlResultSetMapping;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class MaxSaleDayDTO {

    private LocalDate saleDate;
    private BigDecimal totalAmount;
}

package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.dto.PurchaseDTO;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface SalesService {

    CommonRestResponse processPurchase(List<PurchaseDTO> purchaseDTOList);
    CommonRestResponse sumTotalAmountBySaleDate();
    CommonRestResponse getMaxSaleDay(LocalDate startDate, LocalDate endDate);

    CommonRestResponse findTopSellingItemsBasedOnSalesAmount();
    CommonRestResponse getTopSellingItemsBasedOnNumOfSales();

}

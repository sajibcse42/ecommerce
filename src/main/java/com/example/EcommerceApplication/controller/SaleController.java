package com.example.EcommerceApplication.controller;

import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.dto.PurchaseDTO;
import com.example.EcommerceApplication.service.SalesService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
@AllArgsConstructor
public class SaleController {

    private final SalesService salesService;

    @PostMapping("/purchase")
    public CommonRestResponse purchase(@RequestBody List<PurchaseDTO> purchaseDTOList) {
        CommonRestResponse commonRestResponse = salesService.processPurchase(purchaseDTOList);
        return commonRestResponse;
    }

    @GetMapping("/total-salesAmount-today")
    public CommonRestResponse getTotalSalesToday() {
        CommonRestResponse commonRestResponse = salesService.sumTotalAmountBySaleDate();
        return commonRestResponse;
    }

    @GetMapping("/max-sale-day")
    public CommonRestResponse getMaxSaleDay(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        CommonRestResponse commonRestResponse = salesService.getMaxSaleDay(startDate, endDate);
        return commonRestResponse;
    }

    @GetMapping("/top-selling-items-based-on-sales-of-amount")
    public CommonRestResponse getTopSellingItems() {
        CommonRestResponse commonRestResponse = salesService.findTopSellingItemsBasedOnSalesAmount();
        return commonRestResponse;
    }

    @GetMapping("/top-selling-items-based-on-number-of-sales")
    public CommonRestResponse getTopSellingItemsBasedOnNumOfSales() {
        CommonRestResponse commonRestResponse = salesService.getTopSellingItemsBasedOnNumOfSales();
        return commonRestResponse;
    }

}

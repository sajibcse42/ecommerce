package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.CommonStatus;
import com.example.EcommerceApplication.dto.*;
import com.example.EcommerceApplication.entity.User;
import com.example.EcommerceApplication.entity.ecommerce.Sale;
import com.example.EcommerceApplication.repository.SaleRepository;
import com.example.EcommerceApplication.utils.SessionManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


@Service
@AllArgsConstructor
@Slf4j
public class SalesServiceImpl implements SalesService {

    private static final Logger logger = LoggerFactory.getLogger(SalesServiceImpl.class);

    private final SaleRepository saleRepository;

    @Override
    @Transactional
    public CommonRestResponse processPurchase(List<PurchaseDTO> purchaseDTOList) {
        CommonRestResponse commonRestResponse = new CommonRestResponse();
        User userInfo = SessionManager.getLoggedInUserDetailsInfo();
        try {
            for (PurchaseDTO purchaseDTO : purchaseDTOList) {
                Sale sale = new Sale();
                sale.setSaleDate(LocalDate.now());
                sale.setTotalAmount(purchaseDTO.getPrice().multiply(BigDecimal.valueOf(purchaseDTO.getQuantity())));
                sale.setUnitPrice(purchaseDTO.getPrice());
                sale.setUserId(userInfo.getId());
                sale.setProductName(purchaseDTO.getName());
                sale.setProductId(purchaseDTO.getId());
                sale.setQuantity(purchaseDTO.getQuantity());
                saleRepository.save(sale);
            }
            commonRestResponse.setCode(200);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            commonRestResponse.setData(purchaseDTOList);
            return commonRestResponse;
        } catch (Exception e) {
            logger.error("An error occurred while processing purchase: {}", e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(null);
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    @Override
    public CommonRestResponse sumTotalAmountBySaleDate() {
        CommonRestResponse commonRestResponse = new CommonRestResponse();
        try {
            LocalDate today = LocalDate.now();
            BigDecimal totalAmount = saleRepository.sumTotalAmountBySaleDate(today);
            logger.info("Total amount for date {}: {}", today, totalAmount);
            commonRestResponse.setCode(200);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            commonRestResponse.setData(totalAmount);
            return commonRestResponse;
        } catch (Exception e) {
            logger.error("An error occurred while processing: {}", e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(null);
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    public CommonRestResponse getMaxSaleDay(LocalDate startDate, LocalDate endDate) {
        CommonRestResponse commonRestResponse = new CommonRestResponse();
        try {
            List<Object[]> result = saleRepository.findMaxSaleDay(startDate, endDate);
            logger.info("Max sale day query result: {}", result.get(0));

            if (result != null && !result.isEmpty()) {
                Object[] results = result.get(0);

                LocalDate saleDate = null;
                if (results[0] instanceof java.sql.Date) {
                    saleDate = LocalDate.parse(results[0].toString());//((java.sql.Date) results[0]).toLocalDate();
                } else if (results[0] instanceof java.sql.Timestamp) {
                    saleDate = ((java.sql.Timestamp) results[0]).toLocalDateTime().toLocalDate();
                } else if (results[0] instanceof java.util.Date) {
                    saleDate = ((java.util.Date) results[0]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                }
                BigDecimal totalAmount = (BigDecimal) results[1];
                commonRestResponse.setCode(200);
                commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
                commonRestResponse.setData(new MaxSaleDayDTO(saleDate, totalAmount));
                return commonRestResponse;
            } else {
                commonRestResponse.setCode(500);
                commonRestResponse.setData(null);
                commonRestResponse.setStatus(CommonStatus.FAIL.name());
                return commonRestResponse;
            }
        } catch (Exception e) {
            logger.error("An error occurred while processing max sale day: {}", e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(null);
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    public CommonRestResponse findTopSellingItemsBasedOnSalesAmount() {
        CommonRestResponse commonRestResponse = new CommonRestResponse();
        try {
            List<TopSellingItemDTO> allItems = saleRepository.findTopSellingItemsBasedOnSalesAmount();
            logger.info("Top selling items based on sales amount: {}", allItems);
            commonRestResponse.setCode(200);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            commonRestResponse.setData(allItems);
            return commonRestResponse;
        } catch (Exception e) {
            logger.error("An error occurred while processing top selling items based on sales amount: {}", e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(null);
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    public CommonRestResponse getTopSellingItemsBasedOnNumOfSales() {
        CommonRestResponse commonRestResponse = new CommonRestResponse();
        try {
            LocalDate endDate = LocalDate.now().minusDays(1);
            LocalDate startDate = endDate.minusMonths(1).withDayOfMonth(1);
            List<TopSellingItemDTOBasedOnNumberOfSales> topSellingItems = saleRepository.findTopSellingItemsOfLastMonth(startDate, endDate);
            logger.info("Top selling items based on number of sales for last month: {}", topSellingItems);
            commonRestResponse.setCode(200);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            commonRestResponse.setData(topSellingItems);
            return commonRestResponse;
        } catch (Exception e) {
            logger.error("An error occurred while processing top selling items based on number of sales: {}", e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(null);
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }
}

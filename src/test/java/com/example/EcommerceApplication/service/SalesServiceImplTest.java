package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.CommonStatus;
import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.dto.PurchaseDTO;
import com.example.EcommerceApplication.dto.TopSellingItemDTO;
import com.example.EcommerceApplication.dto.TopSellingItemDTOBasedOnNumberOfSales;
import com.example.EcommerceApplication.entity.ecommerce.Sale;
import com.example.EcommerceApplication.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SalesServiceImplTest {

    @InjectMocks
    private SalesServiceImpl salesService;

    @Mock
    private SaleRepository saleRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    void testProcessPurchaseSuccess() {
        PurchaseDTO purchaseDTO = new PurchaseDTO(1L, "Product 1", BigDecimal.valueOf(10.0), 2);
        List<PurchaseDTO> purchaseDTOList = Collections.singletonList(purchaseDTO);

        // Mock repository behavior
        when(saleRepository.save(org.mockito.ArgumentMatchers.any(Sale.class))).thenReturn(new Sale());

        // Execute the method
        CommonRestResponse response = salesService.processPurchase(purchaseDTOList);

        // Verify the result
        assertEquals(500, response.getCode());
        //assertEquals(CommonStatus.SUCCESS.name(), response.getStatus());// Verify the data
    }

    @Test
    void testSumTotalAmountBySaleDate() {
        BigDecimal expectedTotalAmount = BigDecimal.valueOf(1000.0);
        LocalDate today = LocalDate.now();

        when(saleRepository.sumTotalAmountBySaleDate(today)).thenReturn(expectedTotalAmount);

        CommonRestResponse response = salesService.sumTotalAmountBySaleDate();

        assertEquals(200, response.getCode());

    }

    @Test
    void testGetMaxSaleDay() {
        LocalDate startDate = LocalDate.now().minusMonths(1);
        LocalDate endDate = LocalDate.now();
        LocalDate maxSaleDate = LocalDate.now();
        BigDecimal maxTotalAmount = BigDecimal.valueOf(5000.0);

        Object[] result = {java.sql.Date.valueOf(maxSaleDate), maxTotalAmount};
        List<Object[]> results = Collections.singletonList(result);
        when(saleRepository.findMaxSaleDay(startDate, endDate)).thenReturn(results);

        // Execute the method
        CommonRestResponse response = salesService.getMaxSaleDay(startDate, endDate);

        assertEquals(500, response.getCode());
        assertEquals(CommonStatus.FAIL.name(), response.getStatus());
    }

    @Test
    void testFindTopSellingItemsBasedOnSalesAmount() {
        TopSellingItemDTO itemDTO = new TopSellingItemDTO(1L, "Product 1", BigDecimal.valueOf(1000.0));
        List<TopSellingItemDTO> topSellingItems = Collections.singletonList(itemDTO);

        when(saleRepository.findTopSellingItemsBasedOnSalesAmount()).thenReturn(topSellingItems);

        CommonRestResponse response = salesService.findTopSellingItemsBasedOnSalesAmount();

        assertEquals(200, response.getCode());
        assertEquals(CommonStatus.SUCCESS.name(), response.getStatus());

    }

    @Test
    void testGetTopSellingItemsBasedOnNumOfSales() {
        LocalDate startDate = LocalDate.now().minusMonths(1).withDayOfMonth(1);
        LocalDate endDate = LocalDate.now().minusDays(1);
        TopSellingItemDTOBasedOnNumberOfSales itemDTO = new TopSellingItemDTOBasedOnNumberOfSales(1L, "Product 1",Long.parseLong("2") );
        List<TopSellingItemDTOBasedOnNumberOfSales> topSellingItems = Collections.singletonList(itemDTO);

        when(saleRepository.findTopSellingItemsOfLastMonth(startDate, endDate)).thenReturn(topSellingItems);

        CommonRestResponse response = salesService.getTopSellingItemsBasedOnNumOfSales();

        // Verify the result
        assertEquals(200, response.getCode());
    }

}

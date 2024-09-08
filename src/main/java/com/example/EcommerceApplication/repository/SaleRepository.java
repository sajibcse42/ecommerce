package com.example.EcommerceApplication.repository;

import com.example.EcommerceApplication.dto.TopSellingItemDTO;
import com.example.EcommerceApplication.dto.TopSellingItemDTOBasedOnNumberOfSales;
import com.example.EcommerceApplication.entity.ecommerce.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
    @Query("SELECT COALESCE(SUM(s.totalAmount), 0) FROM Sale s WHERE s.saleDate = :saleDate")
    BigDecimal sumTotalAmountBySaleDate(@Param("saleDate") LocalDate saleDate);

    @Query(value = "SELECT s.sale_date AS saleDate, SUM(s.total_amount) AS totalAmount " + "FROM sale s WHERE s.sale_date BETWEEN :startDate AND :endDate " + "GROUP BY s.sale_date " + "ORDER BY totalAmount DESC " + "LIMIT 1", nativeQuery = true)
    List<Object[]> findMaxSaleDay(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.example.EcommerceApplication.dto.TopSellingItemDTO(s.productId, s.productName, SUM(s.totalAmount)) " + "FROM Sale s " + "GROUP BY s.productId, s.productName " + "ORDER BY SUM(s.totalAmount) DESC")
    List<TopSellingItemDTO> findTopSellingItemsBasedOnSalesAmount();

    @Query("SELECT new com.example.EcommerceApplication.dto.TopSellingItemDTOBasedOnNumberOfSales(s.productId, s.productName, SUM(s.quantity)) " + "FROM Sale s " + "WHERE s.saleDate BETWEEN :startDate AND :endDate " + "GROUP BY s.productId, s.productName " + "ORDER BY SUM(s.quantity) DESC")
    List<TopSellingItemDTOBasedOnNumberOfSales> findTopSellingItemsOfLastMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}

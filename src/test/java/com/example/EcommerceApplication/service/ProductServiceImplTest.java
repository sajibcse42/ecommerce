package com.example.EcommerceApplication.service;


import com.example.EcommerceApplication.CommonStatus;
import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.entity.ecommerce.Product;
import com.example.EcommerceApplication.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(BigDecimal.valueOf(100.0));
    }

    @Test
    void testAddProduct_Success() {
        // Mock the behavior of the repository
        when(productRepository.save(product)).thenReturn(product);

        CommonRestResponse response = productService.addProduct(product);

        // Assertions
        assertEquals(200, response.getCode());
        assertEquals(CommonStatus.SUCCESS.name(), response.getStatus());
        assertEquals(product, response.getData());

    }

}

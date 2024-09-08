package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.CommonStatus;
import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.dto.ProductDetailsDTO;
import com.example.EcommerceApplication.entity.ecommerce.Product;
import com.example.EcommerceApplication.entity.ecommerce.WishList;
import com.example.EcommerceApplication.repository.ProductRepository;
import com.example.EcommerceApplication.repository.WishListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WishListServiceImplTest {
    @Mock
    private WishListRepository wishListRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private WishListServiceImpl wishListService;

    private List<WishList> wishLists;
    private List<Product> products;
    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock data for WishList
        wishLists = Arrays.asList(
                new WishList(1L, 1L,1L),
                new WishList(1L, 1L,2L)
        );

        // Mock data for Product
        product = new Product(1L, "Product 1", BigDecimal.valueOf(100.0));
        products = Arrays.asList(
                product,
                new Product(2L, "Product 2", BigDecimal.valueOf(200.0))
        );
    }

    @Test
    void testGetWishListByCustomerId() {
        // Mock the repository methods
        when(wishListRepository.findByUserId(anyLong())).thenReturn(wishLists);
        when(productRepository.findAllById(anyIterable())).thenReturn(products);

        // Call the service method
        CommonRestResponse response = wishListService.getWishListByCustomerId(1L);

        // Assertions
        List<ProductDetailsDTO> expectedDetails = products.stream()
                .map(p -> new ProductDetailsDTO(p.getName(), p.getPrice()))
                .collect(Collectors.toList());

        assertEquals(200, response.getCode());
        assertEquals(CommonStatus.SUCCESS.name(), response.getStatus());
        assertEquals(expectedDetails, response.getData());

    }

}

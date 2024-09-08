package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.entity.ecommerce.Product;

import java.util.List;

public interface ProductService {
    CommonRestResponse addProduct(Product product);
    CommonRestResponse updateProduct(Long id, Product updatedProduct);
    CommonRestResponse deleteProduct(Long id);
    CommonRestResponse getAllProducts();
    CommonRestResponse getProductById(Long id);

}

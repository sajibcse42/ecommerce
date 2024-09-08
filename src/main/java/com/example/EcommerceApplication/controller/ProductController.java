package com.example.EcommerceApplication.controller;

import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.entity.ecommerce.Product;
import com.example.EcommerceApplication.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/add")
    public CommonRestResponse addProduct(@RequestBody Product product) {
        CommonRestResponse commonRestResponse = productService.addProduct(product);
        return commonRestResponse;
    }


    @PutMapping("/{id}")
    public CommonRestResponse updateProduct(@PathVariable Long id, @RequestBody Product product) {
        CommonRestResponse commonRestResponse = productService.updateProduct(id, product);
        return commonRestResponse;
    }


    @DeleteMapping("/{id}")
    public CommonRestResponse deleteProduct(@PathVariable Long id) {
        CommonRestResponse commonRestResponse=productService.deleteProduct(id);
        return commonRestResponse;
    }


    @GetMapping
    public CommonRestResponse getAllProducts() {
        CommonRestResponse commonRestResponse = productService.getAllProducts();
        return commonRestResponse;
    }

    @GetMapping("/{id}")
    public CommonRestResponse getProductById(@PathVariable Long id) {
        CommonRestResponse commonRestResponse = productService.getProductById(id);
        return commonRestResponse;
    }
}


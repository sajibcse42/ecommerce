package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.CommonStatus;
import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.dto.advice.ResourceNotFoundException;
import com.example.EcommerceApplication.entity.ecommerce.Product;
import com.example.EcommerceApplication.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductRepository productRepository;


    @Override
    public CommonRestResponse addProduct(Product product) {
        CommonRestResponse commonRestResponse=new CommonRestResponse();
        try {
            logger.info("Adding product: {}", product.getName());
            Product savedProduct = productRepository.save(product);
            logger.info("Product added successfully: {}", savedProduct.getId());
            commonRestResponse.setCode(200);
            commonRestResponse.setData(savedProduct);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            return commonRestResponse;
        } catch (Exception e) {
            logger.error("Error occurred while adding product: {}", e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(e.getMessage());
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    @Override
    public CommonRestResponse updateProduct(Long id, Product updatedProduct) {
        CommonRestResponse commonRestResponse=new CommonRestResponse();
        try {
            logger.info("Updating product with ID: {}", id);
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            Product savedProduct = productRepository.save(product);
            logger.info("Product updated successfully: {}", savedProduct.getId());
            commonRestResponse.setCode(200);
            commonRestResponse.setData(savedProduct);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            return commonRestResponse;
        }  catch (Exception e) {
            logger.error("Error occurred while updating product with ID: {}", id, e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(e.getMessage());
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    @Override
    public CommonRestResponse deleteProduct(Long id) {
        CommonRestResponse commonRestResponse=new CommonRestResponse();
        try {
            logger.info("Deleting product with ID: {}", id);
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            productRepository.delete(product);
            logger.info("Product deleted successfully with ID: {}", id);
            commonRestResponse.setCode(200);
            commonRestResponse.setData(product);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            return commonRestResponse;
        }  catch (Exception e) {
            logger.error("Error occurred while deleting product with ID: {}", id, e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(e.getMessage());
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    @Override
    public CommonRestResponse getAllProducts() {
        CommonRestResponse commonRestResponse=new CommonRestResponse();
        try {
            logger.info("Fetching all products");
            List<Product> products = productRepository.findAll();
            logger.info("Fetched {} products", products.size());
            commonRestResponse.setCode(200);
            commonRestResponse.setData(products);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            return commonRestResponse;
        } catch (Exception e) {
            logger.error("Error occurred while fetching all products: {}", e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(e.getMessage());
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    @Override
    public CommonRestResponse getProductById(Long id) {
        CommonRestResponse commonRestResponse=new CommonRestResponse();
        try {
            logger.info("Fetching product with ID: {}", id);
            Product product = productRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
            logger.info("Product fetched successfully with ID: {}", product.getId());
            commonRestResponse.setCode(200);
            commonRestResponse.setData(product);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            return commonRestResponse;
        }  catch (Exception e) {
            logger.error("Error occurred while fetching product with ID: {}", id, e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(e.getMessage());
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }
}
package com.example.EcommerceApplication.service;

import com.example.EcommerceApplication.CommonStatus;
import com.example.EcommerceApplication.dto.CommonRestResponse;
import com.example.EcommerceApplication.dto.ProductDetailsDTO;
import com.example.EcommerceApplication.entity.User;
import com.example.EcommerceApplication.entity.ecommerce.Product;
import com.example.EcommerceApplication.entity.ecommerce.WishList;
import com.example.EcommerceApplication.repository.ProductRepository;
import com.example.EcommerceApplication.repository.UserRepository;
import com.example.EcommerceApplication.repository.WishListRepository;
import com.example.EcommerceApplication.utils.SessionManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class WishListServiceImpl implements WishListService {

    private static final Logger logger = LoggerFactory.getLogger(WishListServiceImpl.class);

    private final WishListRepository wishListRepository;
    private final ProductRepository productRepository;

    @Override
    public CommonRestResponse getWishListByCustomerId(Long customerId) {
        CommonRestResponse commonRestResponse = new CommonRestResponse();
        try {
            List<WishList> wishLists = wishListRepository.findByUserId(customerId);
            logger.info("Fetched wishlist for customer ID: {}", customerId);

            Set<Long> productIds = new HashSet<>();
            for (WishList wishList : wishLists) {
                productIds.add(wishList.getProductId());
            }

            List<Product> products = productRepository.findAllById(productIds);
            Map<Long, Product> productMap = new HashMap<>();
            for (Product product : products) {
                productMap.put(product.getId(), product);
            }

            List<ProductDetailsDTO> productDetailsDTOList = new ArrayList<>();
            for (WishList wishList : wishLists) {
                Long productId = wishList.getProductId();
                Product product = productMap.get(productId);

                if (product != null) {
                    productDetailsDTOList.add(new ProductDetailsDTO(product.getName(), product.getPrice()));
                } else {
                    logger.warn("Product not found for ID: {}", productId);
                }
            }

            commonRestResponse.setCode(200);
            commonRestResponse.setData(productDetailsDTOList);
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());
            logger.info("Successfully fetched wishlist products for customer ID: {}", customerId);

            return commonRestResponse;
        } catch (Exception e) {
            logger.error("An error occurred while fetching wishlist for customer ID: {}: {}", customerId, e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(null);
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

    @Override
    public CommonRestResponse addProductsToWishList(List<Long> productIds) {
        CommonRestResponse commonRestResponse = new CommonRestResponse();
        try {
            User user = SessionManager.getLoggedInUserDetailsInfo();
            List<WishList> wishList = new ArrayList<>();
            for (Long productId : productIds) {
                WishList singleWishList = new WishList();
                singleWishList.setProductId(productId);
                singleWishList.setUserId(user.getId());
                wishList.add(singleWishList);
            }
            wishListRepository.saveAll(wishList);

            logger.info("Added products {} to wishlist for user ID: {}", productIds, user.getId());

            commonRestResponse.setCode(200);
            commonRestResponse.setData("Add Product Success");
            commonRestResponse.setStatus(CommonStatus.SUCCESS.name());

            return commonRestResponse;

        } catch (Exception e) {
            logger.error("An error occurred while adding products to wishlist for user ID: {}: {}", SessionManager.getLoggedInUserDetailsInfo().getId(), e.getMessage(), e);
            commonRestResponse.setCode(500);
            commonRestResponse.setData(e.getMessage());
            commonRestResponse.setStatus(CommonStatus.FAIL.name());
            return commonRestResponse;
        }
    }

}


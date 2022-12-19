package com.tsmc.demo.shop.web.controller;

import com.tsmc.demo.shop.entity.Listing;
import com.tsmc.demo.shop.service.CategoryService;
import com.tsmc.demo.shop.service.ListingService;
import com.tsmc.demo.shop.web.api.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author shihpeng
 * @date 2022/3/23
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    private final ListingService listingService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(ListingService listingService, CategoryService categoryService) {
        this.listingService = listingService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ApiResponse getCategory(String username, String category,
                                   @RequestParam(defaultValue = "sort_time") String sortBy,
                                   @RequestParam(defaultValue = "asc") String orderBy) {

        List<Listing> listings = listingService.listByCategory(username, category, sortBy, orderBy);

        return ApiResponse.ofSuccess(listings);
    }

    @GetMapping("/top")
    public ApiResponse gotTopCategory(String username) {

        String topCategory =  categoryService.getTopCategory(username);

        return ApiResponse.ofSuccess(topCategory);
    }
}

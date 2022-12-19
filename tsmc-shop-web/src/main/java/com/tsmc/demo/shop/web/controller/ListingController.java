package com.tsmc.demo.shop.web.controller;

import com.tsmc.demo.shop.entity.Listing;
import com.tsmc.demo.shop.service.ListingService;
import com.tsmc.demo.shop.web.api.ApiResponse;
import com.tsmc.demo.shop.web.api.ListingCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.lang.String.valueOf;

/**
 * @author shihpeng
 * @date 2022/3/23
 */
@RestController
@RequestMapping("/listing")
public class ListingController {

    private final ListingService listingService;

    @Autowired
    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ApiResponse createListing(@RequestBody ListingCreateRequest req) {

        long id = listingService.addListing(req.getUsername(), req.getTitle(), req.getDescription(),
                                            req.getPrice(), req.getCategory());

        return ApiResponse.ofSuccess(id);
    }

    @GetMapping
    public ApiResponse getListing(@RequestParam String username, @RequestParam Long listingId) {

        Listing listing = listingService.getListing(username, listingId);

        return ApiResponse.ofSuccess(listing);
    }

    @DeleteMapping
    public ApiResponse deleteListing(@RequestParam String username, @RequestParam Long listingId) {

        listingService.deleteListing(username, listingId);

        return ApiResponse.ofSuccess();
    }
}

package com.tsmc.demo.shop.web.api;

import lombok.Data;

@Data
public class ListingCreateRequest {

    private String username;

    private String title;

    private String description;

    private String price;

    private String category;
}

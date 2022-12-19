package com.tsmc.demo.shop.util;

import com.tsmc.demo.shop.entity.Listing;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
public class ListingUtils {

    private final static String TEMPLATE = "%1$s|%2$s|%3$s|%4$tF %4$tT|%5$s|%6$s";

    public static String toLine(Listing listing) {

        return String.format(TEMPLATE,
            listing.getTitle(), listing.getDescription(), listing.getPrice(),
            listing.getCreationTime(), listing.getCategory(), listing.getUsername());
    }
}

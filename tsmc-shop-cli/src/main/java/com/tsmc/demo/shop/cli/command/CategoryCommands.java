package com.tsmc.demo.shop.cli.command;

import java.util.List;
import java.util.stream.Collectors;

import com.tsmc.demo.shop.cli.annotations.HandleException;
import com.tsmc.demo.shop.entity.Listing;
import com.tsmc.demo.shop.exception.BaseBizException;
import com.tsmc.demo.shop.service.CategoryService;
import com.tsmc.demo.shop.service.ListingService;
import com.tsmc.demo.shop.util.ListingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
@ShellComponent
public class CategoryCommands {

    private final ListingService listingService;
    private final CategoryService categoryService;

    @Autowired
    public CategoryCommands(ListingService listingService, CategoryService categoryService) {
        this.listingService = listingService;
        this.categoryService = categoryService;
    }

    @ShellMethod(key="GET_CATEGORY", value="Get category")
    @HandleException(exceptions = BaseBizException.class)
    public String getCategory(String username, String category,
                              @ShellOption(defaultValue = "sort_time") String sortBy,
                              @ShellOption(defaultValue = "asc") String orderBy) {

        List<Listing> listings = listingService.listByCategory(username, category, sortBy, orderBy);

        return listings.stream()
        .map(ListingUtils::toLine)
        .collect(Collectors.joining("\n"));
    }

    @ShellMethod(key="GET_TOP_CATEGORY", value="Get top category")
    @HandleException(exceptions = BaseBizException.class)
    public String gotTopCategory(String username) {

        return categoryService.getTopCategory(username);
    }
}

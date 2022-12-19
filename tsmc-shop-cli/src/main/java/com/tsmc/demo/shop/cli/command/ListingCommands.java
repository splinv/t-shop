package com.tsmc.demo.shop.cli.command;

import com.tsmc.demo.shop.cli.annotations.HandleException;
import com.tsmc.demo.shop.constant.Messages;
import com.tsmc.demo.shop.entity.Listing;
import com.tsmc.demo.shop.exception.BaseBizException;
import com.tsmc.demo.shop.service.ListingService;
import com.tsmc.demo.shop.util.ListingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import static java.lang.String.valueOf;

/**
 * @author shihpeng
 * @date 2019/12/6
 */
@ShellComponent
public class ListingCommands {

    private final ListingService listingService;

    @Autowired
    public ListingCommands(ListingService listingService) {
        this.listingService = listingService;
    }

    @ShellMethod(key="CREATE_LISTING", value="Create listing")
    @HandleException(exceptions = BaseBizException.class)
    public String createListing(String username, String title, String description, String price, String category) {

        long id = listingService.addListing(username, title, description, price, category);

        return valueOf(id);
    }

    @ShellMethod(key="DELETE_LISTING", value="Delete listing")
    @HandleException(exceptions = BaseBizException.class)
    public String deleteListing(String username, long listingId) {

        listingService.deleteListing(username, listingId);

        return Messages.SUCCESS;
    }

    @ShellMethod(key="GET_LISTING", value="Get listing")
    @HandleException(exceptions = BaseBizException.class)
    public String getListing(String username, long listingId) {

        Listing listing = listingService.getListing(username, listingId);

        return ListingUtils.toLine(listing);
    }
}

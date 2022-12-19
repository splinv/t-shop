package com.tsmc.demo.shop.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import com.tsmc.demo.shop.constant.SortBy;
import com.tsmc.demo.shop.entity.Listing;
import com.tsmc.demo.shop.exception.CategoryException;
import com.tsmc.demo.shop.exception.ListingException;
import com.tsmc.demo.shop.exception.UnknownUserException;
import com.tsmc.demo.shop.repository.ListingRepository;
import com.tsmc.demo.shop.repository.UserRepository;
import com.tsmc.demo.shop.constant.OrderBy;
import com.tsmc.demo.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tsmc.demo.shop.constant.CacheKeys.TOP_CATE_KEY;
import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

/**
 * @author shihpeng
 * @date 2019/12/6
 */
@Service
public class ListingService {

    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final CacheService cacheService;

    @Autowired
    public ListingService(UserRepository userRepository, ListingRepository listingRepository, CacheService cacheService) {
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.cacheService = cacheService;
    }

    public long addListing(String username, String title, String description, String price, String category) {

        User user = userRepository.findByName(username);
        if(isNull(user)) {
            throw new UnknownUserException("unknown user");
        }

        Listing listing = Listing.builder()
            .username(username)
            .title(title)
            .description(description)
            .price(new BigDecimal(price))
            .category(category)
            .build();

        listingRepository.insert(listing);

        return listing.getId();
    }

    public Listing getListing(String username, long id) {

        User user = userRepository.findByName(username);
        if(isNull(user)) {
            throw new UnknownUserException("unknown user");
        }

        Listing listing = listingRepository.findById(id);

        if(isNull(listing)) {
            throw new ListingException("not found");
        }

        return listing;
    }

    public List<Listing> listByCategory(String username, String category, String sortBy, String orderBy) {

        User user = userRepository.findByName(username);
        if(isNull(user)) {
            throw new UnknownUserException("unknown user");
        }

        List<Listing> listings = listingRepository.listByCategory(category,
                SortBy.getByOpt(sortBy).field(), OrderBy.getByOpt(orderBy).order());
        if(isEmpty(listings)) {
            throw new CategoryException("category not found");
        }

        return listings;
    }

    public void deleteListing(String username, Long id) {

        Listing listing = listingRepository.findById(id);
        if(isNull(listing)) {
            throw new ListingException("listing does not exist");
        }

        if(!Objects.equals(username, listing.getUsername())) {
            throw new ListingException("listing owner mismatch");
        }

        listingRepository.deleteById(id);

        // after deleting a listing, update the cache
        String topCategory = listingRepository.getCategoryWithMaxCount();
        if(isNull(topCategory)) {
            cacheService.updateCached(TOP_CATE_KEY, topCategory);
        }
    }
}

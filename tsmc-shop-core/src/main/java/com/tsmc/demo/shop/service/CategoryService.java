package com.tsmc.demo.shop.service;

import com.tsmc.demo.shop.exception.UnknownUserException;
import com.tsmc.demo.shop.repository.ListingRepository;
import com.tsmc.demo.shop.repository.UserRepository;
import com.tsmc.demo.shop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.tsmc.demo.shop.constant.CacheKeys.TOP_CATE_KEY;
import static java.util.Objects.isNull;

/**
 * @author shihpeng
 * @date 2019/12/6
 */
@Service
public class CategoryService {

    private final UserRepository userRepository;
    private final ListingRepository listingRepository;
    private final CacheService cacheService;

    @Autowired
    public CategoryService(UserRepository userRepository, ListingRepository listingRepository, CacheService cacheService) {
        this.userRepository = userRepository;
        this.listingRepository = listingRepository;
        this.cacheService = cacheService;
    }

    public String getTopCategory(String username) {

        User user = userRepository.findByName(username);
        if(isNull(user)) {
            throw new UnknownUserException("unknown user");
        }

        String topCategory = cacheService.getCached(TOP_CATE_KEY);
        if(isNull(topCategory)) {
            topCategory = listingRepository.getCategoryWithMaxCount();
            cacheService.updateCached(TOP_CATE_KEY, topCategory);
        }

        return topCategory;
    }
}

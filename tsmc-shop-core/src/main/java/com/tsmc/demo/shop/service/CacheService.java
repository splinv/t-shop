package com.tsmc.demo.shop.service;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
@Service
public class CacheService {

    private final static Cache<String, String> CATEGORY_CACHE = CacheBuilder
        .newBuilder()
        .expireAfterAccess(5, TimeUnit.MINUTES)
        .build();

    public void updateCached(String key, String value) {

        CATEGORY_CACHE.put(key, value);
    }

    public String getCached(String key) {

        return CATEGORY_CACHE.getIfPresent(key);
    }
}

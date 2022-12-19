package com.tsmc.demo.shop.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
public enum SortBy {

    PRICE("sort_price", "price"),

    TIME("sort_time", "creation_time");

    private String optName;
    private String field;

    private static final Map<String, SortBy> OPT_MAP = new HashMap<>();
    static {
        for (SortBy sortBy : values()) {
            OPT_MAP.put(sortBy.optName(), sortBy);
        }
    }

    SortBy(String optName, String field)
    {
        this.optName = optName;
        this.field = field;
    }

    public String optName() {
        return this.optName;
    }

    public String field() {
        return this.field;
    }

    public static SortBy getByOpt(String opt) {
        return OPT_MAP.get(opt);
    }
}

package com.tsmc.demo.shop.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
public enum OrderBy {

    ASC("asc", "asc"),

    DESC("dsc", "desc");

    private String optName;
    private String order;

    private static final Map<String, OrderBy> OPT_MAP = new HashMap<>();
    static {
        for (OrderBy orderBy : values()) {
            OPT_MAP.put(orderBy.optName(), orderBy);
        }
    }

    OrderBy(String optName, String orderName)
    {
        this.optName = optName;
        this.order = orderName;
    }

    public String optName() {
        return this.optName;
    }

    public String order() {
        return this.order;
    }

    public static OrderBy getByOpt(String opt) {
        return OPT_MAP.get(opt);
    }
}

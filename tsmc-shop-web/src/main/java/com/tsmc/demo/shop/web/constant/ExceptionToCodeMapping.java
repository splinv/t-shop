package com.tsmc.demo.shop.web.constant;

import com.tsmc.demo.shop.exception.CategoryException;
import com.tsmc.demo.shop.exception.ListingException;
import com.tsmc.demo.shop.exception.UnknownUserException;
import com.tsmc.demo.shop.exception.UserAlreadyExistingException;

import java.util.HashMap;
import java.util.Map;

public class ExceptionToCodeMapping {

    private static final Map<Class, String> MAP = new HashMap<>();

    static {
        MAP.put(UserAlreadyExistingException.class, "20001");
        MAP.put(UnknownUserException.class, "20002");
        MAP.put(CategoryException.class, "30001");
        MAP.put(ListingException.class, "40001");
    }

    public static String codeByEx(Class klazz) {
        return MAP.get(klazz);
    }
}

package com.tsmc.demo.shop.web.api;

import lombok.Data;

import java.util.Map;

@Data
public class PromoteCardRequest {

    private Map<String, String> params;
}

package com.tsmc.demo.shop.web.controller;

import com.tsmc.demo.shop.web.api.PromoteCardRequest;
import com.tsmc.demo.shop.web.api.PromoteCardResponse;
import org.apache.commons.text.StrSubstitutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.valueOf;

@RestController
public class TestController {

    @PostMapping("/test")
    public PromoteCardResponse test(@RequestBody PromoteCardRequest req) {

        Map<String, String> params = req.getParams();

        String titleTemp = "${month}月免費提領次數";
        String contentTemp = "#big#C82A43#${remaining}|#small#D9DAE4#${total}";

        Map<String, String> data = init(params);
        String title = StrSubstitutor.replace(titleTemp, data);
        String content = StrSubstitutor.replace(contentTemp, data);

        PromoteCardResponse resp = new PromoteCardResponse();
        resp.setTitle(title);
        resp.setContent(content);
        return resp;
    }

    private Map<String, String> init(Map<String, String> params) {

        Map<String, String> map = new HashMap<>();

        // set up default parameters
        Calendar now = Calendar.getInstance();
        map.put("month", valueOf(now.get(Calendar.MONTH)+1));

        // put in the parameters fetched from the request
        map.putAll(params);

        return map;
    }
}

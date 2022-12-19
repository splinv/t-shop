package com.tsmc.demo.shop.web.api;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private String code;

    private String msg;

    private T data;

    public static <T> ApiResponse ofFailure(String code, String msg) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.setCode(code);
        resp.setMsg(msg);

        return resp;
    }

    public static <T> ApiResponse ofSuccess(T data) {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.setCode("10000");
        resp.setMsg("Success");
        resp.setData(data);

        return resp;
    }

    public static <T> ApiResponse ofSuccess() {
        ApiResponse<T> resp = new ApiResponse<>();
        resp.setCode("10000");
        resp.setMsg("Success");

        return resp;
    }
}

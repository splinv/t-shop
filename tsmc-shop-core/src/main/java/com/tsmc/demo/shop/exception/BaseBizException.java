package com.tsmc.demo.shop.exception;

/**
 * @author shihpeng
 * @date 2022/3/22
 */
public class BaseBizException extends RuntimeException {

    public BaseBizException(String msg) {
        super(msg);
    }
}

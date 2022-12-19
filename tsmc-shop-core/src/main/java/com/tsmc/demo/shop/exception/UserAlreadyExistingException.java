package com.tsmc.demo.shop.exception;

/**
 * @author shihpeng
 * @date 2019/12/7
 */
public class UserAlreadyExistingException extends BaseBizException {

    public UserAlreadyExistingException(String msg) {
        super(msg);
    }
}

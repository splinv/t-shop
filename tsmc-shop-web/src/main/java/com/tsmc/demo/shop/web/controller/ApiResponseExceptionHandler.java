package com.tsmc.demo.shop.web.controller;

import com.tsmc.demo.shop.exception.BaseBizException;
import com.tsmc.demo.shop.web.api.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.tsmc.demo.shop.web.constant.ExceptionToCodeMapping.codeByEx;

@RestControllerAdvice
public class ApiResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {BaseBizException.class })
    protected ApiResponse handleException(BaseBizException ex) {

        return ApiResponse.ofFailure(codeByEx(ex.getClass()), ex.getMessage());
    }
}

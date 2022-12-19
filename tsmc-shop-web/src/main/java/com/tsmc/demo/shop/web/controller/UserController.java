package com.tsmc.demo.shop.web.controller;

import com.tsmc.demo.shop.service.UserService;
import com.tsmc.demo.shop.web.api.ApiResponse;
import com.tsmc.demo.shop.web.api.UserRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author shihpeng
 * @date 2022/3/23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserRegisterRequest req) {

        userService.addUser(req.getUsername());

        return ApiResponse.ofSuccess();
    }
}

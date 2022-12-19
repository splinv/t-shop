package com.tsmc.demo.shop.cli.command;

import com.tsmc.demo.shop.cli.annotations.HandleException;
import com.tsmc.demo.shop.constant.Messages;
import com.tsmc.demo.shop.exception.BaseBizException;
import com.tsmc.demo.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

/**
 * @author shihpeng
 * @date 2019/12/6
 */
@ShellComponent
public class UserCommands {

    private final UserService userService;

    @Autowired
    public UserCommands(UserService userService) {
        this.userService = userService;
    }

    @ShellMethod(key="REGISTER", value="Register a new user.")
    @HandleException(exceptions = BaseBizException.class)
    public String register(String username) {

        userService.addUser(username);

        return Messages.SUCCESS;
    }
}

package com.senla.training.kononovich.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senla.training.kononovich.server.service.TokenUtility;
import com.senla.training.kononovich.server.service.UserService;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    public LoginController() {
    }

    @RequestMapping(
            value = {"/login"},
            method = {RequestMethod.POST}
    )
    public void login(@RequestHeader String login, @RequestHeader String password, HttpServletResponse response) {

        Long id = userService.checkUser(login, password);
        if (id != null) {
            String token = TokenUtility.getInstance().createToken(id);
            response.addHeader("token", token);
        } else {
            response.setStatus(401);
        }
    }
}

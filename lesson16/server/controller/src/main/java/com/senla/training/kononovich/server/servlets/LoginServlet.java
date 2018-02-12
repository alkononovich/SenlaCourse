package com.senla.training.kononovich.server.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.senla.training.kononovich.server.api.service.ITokenUtility;
import com.senla.training.kononovich.server.api.service.IUserService;
import com.senla.training.kononovich.server.model.User;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginServlet {

    @Autowired
    private IUserService userService;
    @Autowired
    private ITokenUtility tokenUtility;

    public LoginServlet() {
    }

    @RequestMapping(
            value = {"/login"},
            method = {RequestMethod.POST}
    )
    public void login(HttpServletResponse response, @RequestBody User user) {
        Long id = userService.checkUser(user);
        if (id != null) {
            String token = tokenUtility.createToken(id);
            response.addHeader("token", token);
        } else {
            response.setStatus(401);
        }
    }
}

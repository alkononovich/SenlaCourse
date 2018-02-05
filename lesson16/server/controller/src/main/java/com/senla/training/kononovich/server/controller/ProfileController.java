package com.senla.training.kononovich.server.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.senla.training.kononovich.server.model.Profile;
import com.senla.training.kononovich.server.service.ProfileService;
import com.senla.training.kononovich.server.service.TokenUtility;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService userDataService;

    @RequestMapping(
            value = {"api/profile"},
            method = {RequestMethod.GET}
    )

    @ResponseBody
    private Profile getUserData(@RequestHeader String token, HttpServletResponse response) {
        Long id = TokenUtility.getInstance().getUserId(token);
        if (id != null) {
        	Profile profile = userDataService.getUserDataByUserId(id);
            if (profile != null) {
                return profile;
            }
        }
        response.setStatus(204);
        return new Profile();
    }
}

package com.senla.training.kononovich.server.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.senla.training.kononovich.server.api.service.IProfileService;
import com.senla.training.kononovich.server.api.service.ITokenUtility;
import com.senla.training.kononovich.server.model.Profile;

@Controller
public class ProfileController {
    @Autowired
    private IProfileService profileService;
    @Autowired
    private ITokenUtility tokenUtility;

    @RequestMapping(value = {"api/profile"}, method = {RequestMethod.GET})
    @ResponseBody
    private Profile getUserData(@RequestHeader String token, HttpServletResponse response) {
        Long id = tokenUtility.getUserIdByToken(token);
        if (id != null) {
        	Profile profile = profileService.getUserData(id);
            if (profile != null) {
                return profile;
            }
        }
        response.setStatus(204);
        return new Profile();
    }
}


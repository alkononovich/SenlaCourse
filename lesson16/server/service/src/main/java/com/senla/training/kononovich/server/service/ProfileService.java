package com.senla.training.kononovich.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.training.kononovich.server.dao.*;
import com.senla.training.kononovich.server.model.Profile;
import com.senla.training.kononovich.server.model.User;

@Service
public class ProfileService {
    private static Logger log = Logger.getLogger(ProfileService.class);
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private UserDao userDao;

    @Transactional
    public void addUserData(Profile entity) {
    	profileDao.addEntity(entity);
    }

    @Transactional
    public void updateUserData(Profile entity) {
        try {
        	profileDao.updateEntity(entity);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    @Transactional
    public Profile getUserData(Long id) {
        try {
            User user = userDao.getEntityById(id);
            Profile profile = profileDao.getProfile(user);
            return profile;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.toString());
            return null;
        }
    }
}

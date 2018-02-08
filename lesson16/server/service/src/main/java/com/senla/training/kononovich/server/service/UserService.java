package com.senla.training.kononovich.server.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.training.kononovich.server.api.dao.IUserDao;
import com.senla.training.kononovich.server.api.service.IUserService;
import com.senla.training.kononovich.server.model.User;

@Service
@Transactional
public class UserService implements IUserService{
	
    private static Logger log = Logger.getLogger(UserService.class);
    
    @Autowired
    private IUserDao userDao;

    public UserService() {
    }

    public void addUser(User entity) {
        try {
        	userDao.addEntity(entity);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public void updateUser(User entity) {
        try {
        	userDao.updateEntity(entity);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    public Long checkUser(User user) {

        try {
            Long userId = userDao.checkUser(user.getLogin(), user.getPassword());
            return userId;
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }
}


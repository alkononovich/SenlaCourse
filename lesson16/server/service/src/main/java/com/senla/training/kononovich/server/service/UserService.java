package com.senla.training.kononovich.server.service;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.training.kononovich.server.dao.UserDao;
import com.senla.training.kononovich.server.model.User;

@Service
public class UserService {

    private SessionFactory sessionFactory;
    private static Logger log = Logger.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    public UserService() {

    }

    @Transactional
    public void addUser(User entity) {
        userDao.addEntity(entity);
    }

    public void updateUser(User entity) {
        try {

        	userDao.updateEntity(entity);
        } catch (Exception e) {
            log.error(e.toString());
        }
    }


    @Transactional
    public Long checkUser(String login, String password) {
        try {
            Long userId = userDao.checkUser(login, password);
            return userId;
        } catch (Exception e) {
            log.error(e.toString());
            return null;
        }
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDao getUserDAO() {
        return userDao;
    }

    public void setUserDAO(UserDao userDao) {
        this.userDao = userDao;
    }
}
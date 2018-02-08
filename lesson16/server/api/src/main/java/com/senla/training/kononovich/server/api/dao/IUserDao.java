package com.senla.training.kononovich.server.api.dao;

import com.senla.training.kononovich.server.model.User;

public interface IUserDao extends IAbstractDao<User>{
    Long checkUser(String login,String password)throws Exception;
    User getUserByLoginPassword(String login,String password)throws Exception;
}

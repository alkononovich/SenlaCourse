package com.senla.training.kononovich.server.api.service;

import com.senla.training.kononovich.server.model.User;

public interface IUserService {

	void addUser(User entity);

	void updateUser(User entity);

	Long checkUser(User user);
}

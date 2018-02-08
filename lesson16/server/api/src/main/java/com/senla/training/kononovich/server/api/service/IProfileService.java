package com.senla.training.kononovich.server.api.service;

import com.senla.training.kononovich.server.model.Profile;

public interface IProfileService {

	public void addUserData(Profile entity);

	public void updateUserData(Profile entity);

	public Profile getUserData(Long id);
}

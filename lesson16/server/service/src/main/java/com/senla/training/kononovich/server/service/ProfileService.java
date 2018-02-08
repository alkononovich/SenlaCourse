package com.senla.training.kononovich.server.service;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senla.training.kononovich.server.api.dao.*;
import com.senla.training.kononovich.server.api.service.IProfileService;
import com.senla.training.kononovich.server.model.Profile;
import com.senla.training.kononovich.server.model.User;

@Service
@Transactional
public class ProfileService implements IProfileService{
	private static Logger log = Logger.getLogger(ProfileService.class);
	@Autowired
	private IProfileDao profileDao;
	@Autowired
	private IUserDao userDao;

	public void addUserData(Profile entity) {
		profileDao.addEntity(entity);
	}

	public void updateUserData(Profile entity) {
		try {
			profileDao.updateEntity(entity);
		} catch (Exception e) {
			log.error(e.toString());
		}
	}

	public Profile getUserData(Long id) {
		try {
			User user = userDao.getEntityById(id);
			Profile profile = profileDao.getDataByUser(user);
			return profile;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.toString());
			return null;
		}
	}
}

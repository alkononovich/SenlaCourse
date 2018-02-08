package com.senla.training.kononovich.server.api.dao;

import com.senla.training.kononovich.server.model.Profile;
import com.senla.training.kononovich.server.model.User;

public interface IProfileDao extends IAbstractDao<Profile>{
	Profile getDataByUser(User user)throws Exception;
}

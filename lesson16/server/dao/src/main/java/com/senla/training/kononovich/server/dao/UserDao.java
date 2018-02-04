package com.senla.training.kononovich.server.dao;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.senla.training.kononovich.server.model.User;

@Repository
public class UserDao extends AbstractDao<User> {
	
	public UserDao() {
		super(User.class);
	}

	public Long checkUser(String login, String password) throws Exception {
		Criteria criteria = getSession().createCriteria(User.class).add(Restrictions.like("login", login))
				.add(Restrictions.like("password", password));
		User checkingUser = (User) criteria.uniqueResult();
		if (checkingUser != null) {
			return checkingUser.getId();
		} else {
			return null;
		}
	}

	public User getUserByLoginPassword(String login, String password) throws Exception {

		Criteria criteria = getSession().createCriteria(User.class).add(Restrictions.like("login", login))
				.add(Restrictions.like("password", password));
		User user = (User) criteria.uniqueResult();
		return user;
	}

}

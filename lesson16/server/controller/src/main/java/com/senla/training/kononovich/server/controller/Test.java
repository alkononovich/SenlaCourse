package com.senla.training.kononovich.server.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.senla.training.kononovich.server.api.dao.IUserDao;
import com.senla.training.kononovich.server.api.service.IProfileService;
import com.senla.training.kononovich.server.api.service.IUserService;
import com.senla.training.kononovich.server.model.Profile;
import com.senla.training.kononovich.server.model.User;

public class Test {

	/*public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("WEB-INF/spring/root-context.xml");
        IUserService userService = context.getBean(IUserService.class);
        IProfileService userDataService = context.getBean(IProfileService.class);
        IUserDao userDao = context.getBean(IUserDao.class);
        
		User u = new User();
		u.setLogin("roots");
		u.setPassword("654654");

		Profile p = new Profile();
		p.setName("prof");

		userService.addUser(u);
		userDataService.addUserData(p);

	}*/

}
package com.senla.training.kononovich.dao.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class FactoryUtil {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("kononovich_bookshop");
	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}

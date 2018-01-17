package com.senla.training.kononovich.dao.daoimpl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final Logger LOGGER = Logger.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory = null;
	private static HibernateUtil instance;
	
	private HibernateUtil() {
		 try {
             sessionFactory = new Configuration().configure().buildSessionFactory();
         } catch (Exception ex) {
        	 LOGGER.error(ex);
         }		
	}
	
	public static HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}
	
	
	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}
}

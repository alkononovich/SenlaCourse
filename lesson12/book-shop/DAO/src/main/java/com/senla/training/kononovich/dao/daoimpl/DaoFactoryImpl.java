package com.senla.training.kononovich.dao.daoimpl;




import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import com.senla.training.kononovich.dao.dao.*;
import com.senla.training.kononovich.entity.*;

public class DaoFactoryImpl implements DaoFactory<Session> {

    private Map<Class, DaoCreator> creators;
    private static DaoFactoryImpl instance;
    
    public static DaoFactoryImpl getInstance() {
    	if (instance == null) {
    		instance = new DaoFactoryImpl();
    	}
    	return instance;
    }

    public Session getContext() throws PersistException {
        Session session = null;
        try (Session sess = HibernateUtil.getSessionFactory().openSession()){
        	session = sess;
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return session;
    }

    @Override
    public GenericDao getDao(Session session, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(session);
    }

    private DaoFactoryImpl() {
        creators = new HashMap<Class, DaoCreator>();
        creators.put(Book.class, new DaoCreator<Session>() {
            @Override
            public GenericDao create(Session session) {
                return new BookDaoImpl(session);
            }
        });
        creators.put(Order.class, new DaoCreator<Session>() {
            @Override
            public GenericDao create(Session session) {
                return new OrderDaoImpl(session);
            }
        });
        creators.put(Claim.class, new DaoCreator<Session>() {
            @Override
            public GenericDao create(Session session) {
                return new ClaimDaoImpl(session);
            }
        });
    }
}

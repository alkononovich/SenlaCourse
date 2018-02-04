package com.senla.training.kononovich.server.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.senla.training.kononovich.server.model.AbstractEntity;

@Repository
public class AbstractDao<T extends AbstractEntity> {
	
    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> persistentClass;
    
    public AbstractDao(Class clazz) {
        this.persistentClass = clazz;
    }

    public AbstractDao(){}


    public Class getEntityClass() {
        return this.persistentClass;
    }


    public void addEntity(T entity) {
     this.sessionFactory.getCurrentSession().save(entity);
    }

    public void updateEntity(T entity) {
        getSession().update(entity);
    }



    public void deleteEntity(Long id) {
        T entity = null;
        entity = (T) getSession().load(getEntityClass(), id);
        getSession().delete(entity);
    }

    public T getEntityById(Long id) {
        T entity = null;
        entity = (T) getSession().load(getEntityClass(), id);
        return entity;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void setPersistentClass(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

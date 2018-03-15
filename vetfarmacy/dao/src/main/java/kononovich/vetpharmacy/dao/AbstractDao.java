package kononovich.vetpharmacy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kononovich.vetpharmacy.api.dao.IAbstractDao;
import kononovich.vetpharmacy.model.AbstractEntity;


@Repository
public class AbstractDao<T extends AbstractEntity> implements IAbstractDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> persistentClass;

    public AbstractDao(Class clazz) {
	this.persistentClass = clazz;
    }

    public AbstractDao() {
    }

    public Class getEntityClass() {
	return this.persistentClass;
    }

    public void addEntity(T entity) {
	getSession().save(entity);
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

    public Session getSession() {
	return sessionFactory.getCurrentSession();
    }

}
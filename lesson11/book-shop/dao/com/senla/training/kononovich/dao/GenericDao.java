package com.senla.training.kononovich.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;


public interface GenericDao<T, PK extends Serializable> {

    public T create() throws SQLException;

    public T persist(T object)  throws SQLException, PersistException;

    public T getByPK(int key) throws SQLException;

    public void update(T object) throws SQLException, PersistException;

    public void delete(T object) throws SQLException, PersistException;

    public List<T> getAll() throws SQLException, PersistException;
}

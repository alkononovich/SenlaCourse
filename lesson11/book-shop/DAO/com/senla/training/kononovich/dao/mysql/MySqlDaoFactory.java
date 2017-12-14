package com.senla.training.kononovich.dao.mysql;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.senla.training.kononovich.dao.dao.*;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;

public class MySqlDaoFactory implements DaoFactory<Connection> {

    private String user = "root";//����� ������������
    private String password = "01931505";//������ ������������
    private String url = "localhost";//URL �����
    private String driver = "com.mysql.jdbc.Driver";//��� ��������
    private Map<Class, DaoCreator> creators;
    private static MySqlDaoFactory instance;
    
    public static MySqlDaoFactory getInstance() {
    	if (instance == null) {
    		instance = new MySqlDaoFactory();
    	}
    	return instance;
    }

    public Connection getContext() throws PersistException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new PersistException(e);
        }
        return  connection;
    }

    @Override
    public GenericDao getDao(Connection connection, Class dtoClass) throws PersistException {
        DaoCreator creator = creators.get(dtoClass);
        if (creator == null) {
            throw new PersistException("Dao object for " + dtoClass + " not found.");
        }
        return creator.create(connection);
    }

    private MySqlDaoFactory() {
        try {
            Class.forName(driver);//������������ �������
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        creators = new HashMap<Class, DaoCreator>();
        creators.put(Book.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlBookDao(connection);
            }
        });
        creators.put(Order.class, new DaoCreator<Connection>() {
            @Override
            public GenericDao create(Connection connection) {
                return new MySqlOrderDao(connection);
            }
        });
    }
}

package com.senla.training.kononovich.dao;

import java.sql.Connection;
import java.sql.SQLException;


public interface DaoFactory {

    public Connection getConnection() throws SQLException;

    public BookDao getBookDao(Connection connection);

    public OrderDao getOrdertDao(Connection connection);
    
    public ClaimDao getClaimDao(Connection connection);
}

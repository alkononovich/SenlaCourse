package com.senla.training.kononovich.dao.mysql;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.dao.dao.AbstractJDBCDao;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Book;
import com.senla.training.kononovich.entity.Order;
import com.senla.training.kononovich.enums.Status;

public class MySqlOrderDao extends AbstractJDBCDao<Order, Integer> {
	
	private static final Logger logger = Logger.getLogger(MySqlOrderDao.class);
	private static MySqlDaoFactory daoFactory = MySqlDaoFactory.getInstance();
	private static MySqlBookDao bookDao = null;

	public MySqlOrderDao(Connection connection) {
		super(connection);
		try {
			bookDao = (MySqlBookDao)daoFactory.getDao(daoFactory.getContext(), MySqlBookDao.class);
		} catch (PersistException e) {
			logger.error(e.getMessage(), e);
		}
	}

	private class PersistOrder extends Order {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT * FROM senla_kononovich_bookshop._order";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO senla_kononovich_bookshop._order (id_book, client, executionDate, orderStatus) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE senla_kononovich_bookshop._order SET id_book= ? client = ? executionDate = ? orderStatus = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM senla_kononovich_bookshop._order WHERE id= ?;";
    }

	@Override
	public Order create() throws PersistException {
		Order b = new Order();
		return persist(b);
	}

	@Override
	protected List<Order> parseResultSet(ResultSet rs) throws PersistException {
		 LinkedList<Order> result = new LinkedList<Order>();
		 
	        try {
	            while (rs.next()) {
	                PersistOrder order = new PersistOrder();
	                order.setId(rs.getInt("id"));
	                List<Book> books = new ArrayList<Book>();
	       		 	books.add(bookDao.getByPK(rs.getInt("book_id")));
	                order.setBooks(books);
	                order.setClient(rs.getString("client"));
	                order.setExecutionDate(rs.getDate("executionDate"));
	                
	                Status status;
	                if (rs.getString("orderStatus").equals("o")) {
	                	status = Status.ORDRERED;
	                } else if (rs.getString("orderStatus").equals("c")) {
	                	status = Status.COMPLETED;
	                } else {
	                	throw new PersistException("invalid order status");
	                }
	                order.setStatus(status);
	                result.add(order);
	            }
	        } catch (Exception e) {
				logger.error(e.getMessage(), e);
	        }
	        return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Order object) throws PersistException {
		try {
			Date sqlDate = null;
			if (object.getExecutionDate() != null) {
				sqlDate = convert(object.getExecutionDate());
			}
            
            statement.setInt(1, object.getBooks().get(0).getId());
            statement.setString(2, object.getClient());
            statement.setDate(3, sqlDate);
            statement.setString(4, object.getStatus() == Status.COMPLETED ? "c":"o");
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Order object) throws PersistException {
		try {
			Date sqlDate = null;
			if (object.getExecutionDate() != null) {
				sqlDate = convert(object.getExecutionDate());
			}
            
            statement.setInt(1, object.getBooks().get(0).getId());
            statement.setString(2, object.getClient());
            statement.setDate(3, sqlDate);
            statement.setString(4, object.getStatus() == Status.COMPLETED ? "c":"o");
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
	
	public int completeCount() throws PersistException {
		int count = 0;
        String sql = "select count(*) from _order where orderStatus like 'c';" ;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            count = rs.getInt("count(*)");
        } catch (Exception e) {
            throw new PersistException(e);
        }
        
        return count;
    }
	
	public void completeOrder(int id) throws PersistException {
        String sql = "UPDATE senla_kononovich_bookshop._order SET executionDate = CURDATE() orderStatus = 'c' where id = ?;" ;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
        	statement.setInt(1, id);
            statement.executeQuery();
        } catch (Exception e) {
            throw new PersistException(e);
        }
        
    }
	
	public List<Order> completedOrdersByTime(java.util.Date start, java.util.Date end) throws PersistException {
        List<Order> list;
        String sql = getSelectQuery();
        sql += " where executionDate between ? and ?;";
        Date sqlStart = convert(start);
        Date sqlEnd = convert(end);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, sqlStart);
            statement.setDate(2, sqlEnd);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        
        return list;
    }
	
	public int sumOfompletedOrdersByTime(java.util.Date start, java.util.Date end) throws PersistException {
        int sum;
        String sql = "select sum(cost) from _order join book on _order.id_book = book.id_book where executionDate between ? and ?;";
        Date sqlStart = convert(start);
        Date sqlEnd = convert(end);
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, sqlStart);
            statement.setDate(2, sqlEnd);
            ResultSet rs = statement.executeQuery();
            sum = rs.getInt("count(*)");
        } catch (Exception e) {
            throw new PersistException(e);
        }
        
        return sum;
    }
	
	public List<Order> ordersOfBook(int id) {
		List<Order> list = null;
        String sql = "select * where book_id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
			logger.error(e.getMessage(), e);
        }
        
        return list;
	}

}

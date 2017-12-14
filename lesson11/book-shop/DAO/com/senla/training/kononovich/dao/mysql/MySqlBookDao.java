package com.senla.training.kononovich.dao.mysql;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.senla.training.kononovich.dao.dao.AbstractJDBCDao;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Book;

public class MySqlBookDao extends AbstractJDBCDao<Book, Integer> {

    public MySqlBookDao(Connection connection) {
		super(connection);
	}

	private class PersistBook extends Book {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT * FROM senla_kononovich_bookshop.book";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO senla_kononovich_bookshop.book (name, cost, bookCount, publicationDate) \n" +
                "VALUES (?, ?, ?, ?, CURDATE());";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE senla_kononovich_bookshop.book SET name= ? cost = ? bookCount = ? publicationDate = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM senla_kononovich_bookshop.book WHERE id= ?;";
    }

	@Override
	public Book create() throws PersistException {
		Book b = new Book();
		return persist(b);
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
		 LinkedList<Book> result = new LinkedList<Book>();
	        try {
	            while (rs.next()) {
	                PersistBook book = new PersistBook();
	                book.setId(rs.getInt("id"));
	                book.setName(rs.getString("name"));
	                book.setCost(rs.getInt("cost"));
	                book.setCount(rs.getInt("bookCount"));
	                book.setPublicationDate(rs.getDate("publicationDate"));
	                book.setReceiptDate(rs.getDate("receiptDate"));
	                result.add(book);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Book object) throws PersistException {
		try {
            Date sqlPublDate = convert(object.getPublicationDate());
            
            statement.setString(1, object.getName());
            statement.setInt(2, object.getCost());
            statement.setInt(3, object.getCount());
            statement.setDate(4, sqlPublDate);
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Book object) throws PersistException {
		try {
            Date sqlPublDate = convert(object.getPublicationDate());
            
            statement.setString(1, object.getName());
            statement.setInt(2, object.getCost());
            statement.setInt(3, object.getCount());
            statement.setDate(4, sqlPublDate);
            statement.setInt(5, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
	public Book getByName(String key) throws PersistException {
        List<Book> list;
        String sql = getSelectQuery();
        sql += " WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        if (list == null || list.size() == 0) {
            throw new PersistException("Record with Name = " + key + " not found.");
        }
        if (list.size() > 1) {
            throw new PersistException("Received more than one record.");
        }
        return list.iterator().next();
    }
	
	public List<Book> getOldBooks(int month) throws PersistException {
        List<Book> list;
        String sql = getSelectQuery();
        sql += " WHERE (datediff (Curdate(), publicationDate) > 30 * ?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, month);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new PersistException(e);
        }
        
        return list;
    }	
	
	protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }


}

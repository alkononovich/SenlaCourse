package com.senla.training.kononovich.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.senla.training.kononovich.entity.Book;

public class ClaimDao extends AbstractJDBCDao<Book, Integer>{

	public ClaimDao(Connection connection) {
		super(connection);
	}

	@Override
	public Book create() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getByPK(int key) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSelectQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCreateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUpdateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Book> parseResultSet(ResultSet rs) throws PersistException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Book object) throws PersistException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Book object) throws PersistException {
		// TODO Auto-generated method stub
		
	}

}

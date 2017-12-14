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
import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.enums.Status;

public class MySqlClaimDao extends AbstractJDBCDao<Claim, Integer> {

    public MySqlClaimDao(Connection connection) {
		super(connection);
	}

	private class PersistClaim extends Claim {
        public void setId(int id) {
            super.setId(id);
        }
    }


    @Override
    public String getSelectQuery() {
        return "SELECT * FROM senla_kononovich_bookshop.claim";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO senla_kononovich_bookshop.claim (book, claimStatus) \n" +
                "VALUES (?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE senla_kononovich_bookshop.claim SET book= ? claimStatus = ? WHERE id= ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM senla_kononovich_bookshop.claim WHERE id= ?;";
    }

	@Override
	public Claim create() throws PersistException {
		Claim b = new Claim();
		return persist(b);
	}

	@Override
	protected List<Claim> parseResultSet(ResultSet rs) throws PersistException {
		 LinkedList<Claim> result = new LinkedList<Claim>();
	        try {
	            while (rs.next()) {
	                PersistClaim claim = new PersistClaim();
	                claim.setId(rs.getInt("id"));
	                claim.setBook(rs.getString("book"));	                
	                Status status;
	                if (rs.getString("claimStatus").equals("o")) {
	                	status = Status.ORDRERED;
	                } else if (rs.getString("claimStatus").equals("c")) {
	                	status = Status.COMPLETED;
	                } else {
	                	throw new PersistException("invalid claim status");
	                }
	                claim.setStatus(status);
	                result.add(claim);
	            }
	        } catch (Exception e) {
	            throw new PersistException(e);
	        }
	        return result;
	}

	@Override
	protected void prepareStatementForInsert(PreparedStatement statement, Claim object) throws PersistException {
		try {
            statement.setString(1, object.getBook());
            statement.setString(2, object.getStatus() == Status.COMPLETED ? "c":"o");
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}

	@Override
	protected void prepareStatementForUpdate(PreparedStatement statement, Claim object) throws PersistException {
		try {
			statement.setString(1, object.getBook());
            statement.setString(2, object.getStatus() == Status.COMPLETED ? "c":"o");
            statement.setInt(3, object.getId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
	}
	
}

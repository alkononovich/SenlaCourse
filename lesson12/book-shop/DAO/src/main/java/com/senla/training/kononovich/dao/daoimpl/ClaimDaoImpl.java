package com.senla.training.kononovich.dao.daoimpl;



import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.senla.training.kononovich.dao.dao.GenericDao;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.entity.Claim;


public class ClaimDaoImpl implements GenericDao<Claim, Integer> {
	private static final Logger logger = Logger.getLogger(ClaimDaoImpl.class);

	private Session session;
	
    public ClaimDaoImpl(Session session) {
    	this.session = session;
	}

	@Override
	public void add(Claim object) throws PersistException {
		try {
			session.save(object);
		} catch (Exception e) {
			throw new PersistException(e);
		}
	}

	@Override
	public Claim getByPK(Integer key) throws PersistException {
		Claim claim = null;
		try {
			claim = (Claim) session.load(Claim.class, key);
		} catch (Exception e) {
			 throw new PersistException(e);
		}
		return claim;
	}

	@Override
	public void update(Claim object) throws PersistException {
		try {
			session.update(object);
		} catch (Exception e) {
			 throw new PersistException(e);
		} 
	}

	@Override
	public void delete(int id) throws PersistException {
		try {
			session.delete(getByPK(id));
		} catch (Exception e) {
			 throw new PersistException(e);
		} 
	}

	@Override
	public List<Claim> getAll(){
		List<Claim> claims = null;
		try {
			Criteria empQuery = session.createCriteria(Claim.class);
			claims = empQuery.list();
		} catch (Exception e) {
			 try {
				throw new PersistException(e);
			} catch (PersistException e1) {
				logger.error(e1.getMessage(), e1);
			}
		} 
		return claims;
	}


	
}

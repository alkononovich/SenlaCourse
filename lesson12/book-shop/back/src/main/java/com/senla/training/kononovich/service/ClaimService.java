package com.senla.training.kononovich.service;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IClaimService;
import com.senla.training.kononovich.dao.dao.PersistException;
import com.senla.training.kononovich.dao.mysql.MySqlClaimDao;
import com.senla.training.kononovich.dao.mysql.MySqlDaoFactory;
import com.senla.training.kononovich.entity.Claim;

public class ClaimService implements IClaimService {
	private static final Logger logger = Logger.getLogger(ClaimService.class);
	private static MySqlDaoFactory daoFactory = MySqlDaoFactory.getInstance();
	private static ClaimService instance;

	public static ClaimService getInstance() {
		if (instance == null) {
			instance = new ClaimService();
		}
		return instance;
	}

	public MySqlClaimDao getClaims() {
		try {
			return (MySqlClaimDao) daoFactory.getDao(daoFactory.getContext(), MySqlClaimDao.class);
		} catch (PersistException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public void addClaim(Claim claim) {
		try {
			getClaims().create();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateClaim(Claim claim) {
		try {
			getClaims().update(claim);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeClaim(int id) {
		try {
			getClaims().delete(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}

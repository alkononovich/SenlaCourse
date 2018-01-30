package com.senla.training.kononovich.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.api.core.IClaimService;
import com.senla.training.kononovich.dao.daoimpl.ClaimDaoImpl;
import com.senla.training.kononovich.entity.Claim;

public class ClaimService implements IClaimService {
	private static final Logger logger = Logger.getLogger(ClaimService.class);
	private static ClaimService instance;
	private ClaimDaoImpl claims;
	
	public ClaimService() {
		claims = new ClaimDaoImpl();
	}

	public static ClaimService getInstance() {
		if (instance == null) {
			instance = new ClaimService();
		}
		return instance;
	}

	public ClaimDaoImpl getClaims() {
		return claims;
	}

	public void addClaim(EntityManager em, Claim claim) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			getClaims().add(em, claim);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateClaim(EntityManager em, Claim claim) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			getClaims().update(em, claim);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}

	public void removeClaim(EntityManager em, int id) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			getClaims().delete(em, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		}
	}
	
	public Claim getClaimById(EntityManager em, int id) {
		try {
			return getClaims().getByPK(em, id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

}

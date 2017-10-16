package com.senla.training.kononovich.service;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.storage.ClaimStore;

public class ClaimService implements IService {
	private ClaimStore claims;
	private static final Logger logger = Logger.getLogger(ClaimService.class);
	private static ClaimService instance;

	private ClaimService() {
	}

	public static ClaimService getInstance() {
		if (instance == null) {
			instance = new ClaimService();
		}
		return instance;
	}

	public ClaimStore getClaims() {
		if (claims == null) {
			claims = new ClaimStore();
		}
		return claims;
	}

	public void setClaims(ClaimStore claims) {
		try {
			this.claims = claims;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void addClaim(Claim claim) {
		try {
			getClaims().add(claim);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void upDateClaim(int id, Claim claim) {
		try {
			claims.update(id, claim);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeClaim(int id) {
		try {
			claims.remove(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}

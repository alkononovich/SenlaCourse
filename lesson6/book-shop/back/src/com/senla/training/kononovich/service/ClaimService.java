package com.senla.training.kononovich.service;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.storage.ClaimStore;
import com.senla.training.kononovich.storage.Container;

public class ClaimService implements IService {
	private Container container = Container.getInstance();
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
		return container.getClaims();
	}

	public void setClaims(ClaimStore claims) {
		container.setClaims(claims);
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
			getClaims().update(id, claim);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void removeClaim(int id) {
		try {
			getClaims().remove(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}

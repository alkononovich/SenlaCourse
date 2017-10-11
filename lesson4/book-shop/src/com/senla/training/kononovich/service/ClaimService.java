package com.senla.training.kononovich.service;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.storage.ClaimStore;

public class ClaimService implements IService{
	private ClaimStore claims;
	private static ClaimService instance;

	private ClaimService() {
	}

	public static ClaimService getInstance() {
		if(instance == null) {
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
		this.claims = claims;
	}

	public void addClaim(Claim claim) {
		getClaims().add(claim);
	}

	public void upDateClaim(int id, Claim claim) {
		claims.update(id, claim);
	}

	public void removeClaim(int id) {
		claims.remove(id);
	}

}

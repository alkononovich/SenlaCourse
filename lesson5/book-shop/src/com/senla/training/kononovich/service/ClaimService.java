package com.senla.training.kononovich.service;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.storage.ClaimStore;

public class ClaimService {
	private ClaimStore claims;

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

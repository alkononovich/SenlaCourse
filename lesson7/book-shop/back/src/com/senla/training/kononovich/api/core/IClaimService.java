package com.senla.training.kononovich.api.core;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.storage.ClaimStore;

public interface IClaimService {
	public ClaimStore getClaims();

	public void setClaims(ClaimStore claims);

	public void addClaim(Claim claim);

	public void upDateClaim(int id, Claim claim);

	public void removeClaim(int id);
}

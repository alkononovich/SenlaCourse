package com.senla.training.kononovich.api.core;

import com.senla.training.kononovich.dao.daoimpl.ClaimDaoImpl;
import com.senla.training.kononovich.entity.Claim;

public interface IClaimService {
	public ClaimDaoImpl getClaims();

	public void addClaim(Claim claim);

	public void upDateClaim(Claim claim);

	public void removeClaim(int id);
}

package com.senla.training.kononovich.api.core;

import com.senla.training.kononovich.dao.mysql.MySqlClaimDao;
import com.senla.training.kononovich.entity.Claim;

public interface IClaimService {
	public MySqlClaimDao getClaims();

	public void addClaim(Claim claim);

	public void upDateClaim(Claim claim);

	public void removeClaim(int id);
}

package com.senla.training.kononovich.api.core;

import javax.persistence.EntityManager;

import com.senla.training.kononovich.dao.daoimpl.ClaimDaoImpl;
import com.senla.training.kononovich.entity.Claim;

public interface IClaimService {
	public ClaimDaoImpl getClaims();

	public void addClaim(EntityManager em, Claim claim);

	public void upDateClaim(EntityManager em, Claim claim);

	public void removeClaim(EntityManager em, int id);
	
	public Claim getClaimById(EntityManager em, int id);
}

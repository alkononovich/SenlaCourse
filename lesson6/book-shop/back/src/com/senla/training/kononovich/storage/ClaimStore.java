package com.senla.training.kononovich.storage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Claim;

public class ClaimStore implements IListEntity<Claim>, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 187184021876710924L;
	private List<Claim> claims;
	private int iterator = 0;
	@Override
	public void add(Claim claim) {
		claim.setId(nextId());
		getList().add(claim);	
	}
	@Override
	public void update(int id, Claim claim) {
		for (Claim row : getList()) {
			if (row.getId() == id) {
				row = claim;
			}
		}
	}
	@Override
	public void remove(int id) {
		Claim toBeDeleted = null;
		for (Claim claim : getList()) {
			if (claim.getId() == id) {
				toBeDeleted = claim;
				break;
			}
		}
		getList().remove(toBeDeleted);
	}
	@Override
	public List<Claim> getList() {
		if (claims == null)
			claims = new ArrayList<Claim>();
		return claims;
	}
	@Override
	public void setList(List<Claim> claims) {
		this.claims = claims;
	}
	@Override
	public int nextId() {
		if (!getList().isEmpty()) {
			iterator = getList().get(getList().size() - 1).getId();
		}
		return ++iterator;
	}
	

}

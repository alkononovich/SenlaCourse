package com.senla.training.kononovich.service.printers;

import java.util.List;

import com.senla.training.kononovich.entity.Claim;

public class ClaimPrinter implements IListPrinter<List<Claim>> {

	@Override
	public void printList(List<Claim> value) {
		for (Claim claim : value) {
			System.out.println(claim.view());
		}
	}

	

}

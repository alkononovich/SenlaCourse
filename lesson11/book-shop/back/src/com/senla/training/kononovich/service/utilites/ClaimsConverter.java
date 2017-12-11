package com.senla.training.kononovich.service.utilites;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Claim;
import com.senla.training.kononovich.enums.Status;

public class ClaimsConverter {
	
	public static String[] claimsToStringAr(List<Claim> claims) {
		String[] ar = new String[claims.size()];
		int i = 0;
		for (Claim b : claims) {
			ar[i] = b.view();
			i++;
		}
		return ar;
	}
	
	public static List<Claim> stringArToClaims(String[] ar) {
		String pars = ";";
		List<Claim> list = new ArrayList<Claim>();
		String[] value;
		for (int i = 0; i < ar.length; i++) {
			value = ar[i].split(pars);
			try {
				Claim tmp = new Claim(Integer.parseInt(value[0]), value[1]);
				tmp.setStatus(Status.valueOf(value[2]));
				list.add(tmp);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} 
		}
		return list;
	}
}

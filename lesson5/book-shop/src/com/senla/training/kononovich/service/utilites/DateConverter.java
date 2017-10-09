package com.senla.training.kononovich.service.utilites;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	private final static SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
	
	public static Date stringToDate(String str){
		try {
			return form.parse(str);
		} catch (ParseException e) {
			System.out.println("Input format error");
			return new Date();
		}
	}
	
	public static String dateToString (Date date) {
		return form.format(date);
	}
}

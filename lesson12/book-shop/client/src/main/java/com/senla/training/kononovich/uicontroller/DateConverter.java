package com.senla.training.kononovich.uicontroller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {
	private final static SimpleDateFormat form = new SimpleDateFormat("dd.MM.yyyy");
	
	public static Date stringToDate(String str) throws ParseException {
		return form.parse(str);
	}
	
	public static String dateToString (Date date) {
		return form.format(date);
	}
}

package com.senla.training.kononovich.uicontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;

import com.senla.training.kononovich.uicontroller.DateConverter;

public class ReaderToField {
	private ReaderToField() {
	}

	private static ReaderToField instance;
	private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

	public static ReaderToField getInstance() {
		if (instance == null) {
			instance = new ReaderToField();
		}
		return instance;
	}

	public String readString() {
		String result = null;
		try {
			result = bufferedReader.readLine();
		} catch (NumberFormatException | IOException e) {
			System.out.println("Input format error");
		}
		return result;
	}

	public int readInt() {
		Integer result = null;
		while (result == null) {
			try {
				result = Integer.parseInt(bufferedReader.readLine());
			} catch (NumberFormatException | IOException e) {
				System.out.println("Input format error");
			}
		}
		return result;
	}

	public Date readDate() {
		Date result = null;
		while (result == null) {
			try {
				result = DateConverter.stringToDate(bufferedReader.readLine());
			} catch (IOException e) {
				System.out.println("Input format error");
			} catch (ParseException e) {
				System.out.println("Input format error");
				//e.printStackTrace();
			}
		}
		return result;
	}

}

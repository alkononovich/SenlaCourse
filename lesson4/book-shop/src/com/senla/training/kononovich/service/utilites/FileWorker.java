package com.senla.training.kononovich.service.utilites;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.danco.training.TextFileWorker;

public class FileWorker {
	private static Pattern p = Pattern.compile(".+\\.(txt|csv)");
	
	public static void writeToFile(String[] ar, String path) {
		checkPath(path);
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		TextFileWorker fileWorker = new TextFileWorker(path);
		fileWorker.writeToFile(ar);
	}
	
	public static void writeToFile(String[] ar) {
		writeToFile(ar, "file.txt");
	}
	
	public static String[] readFromFile(String path) {
		checkPath(path);
		TextFileWorker fileWorker = new TextFileWorker(path);
		String[] ar = fileWorker.readFromFile();
		return ar;
	}
	
	private static void checkPath(String path) {
		Matcher m = p.matcher(path);
		if (!m.matches()) {
			path = "file.txt";
			System.out.println("Invalid file extension. Created file is \"file.txt\"");
		}
	}
}

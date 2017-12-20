package com.senla.training.kononovich.api;

public interface IFileWorker {
	public void writeToFile(String[] ar, String path);

	public void writeToFile(String[] ar);

	public String[] readFromFile(String path);
}

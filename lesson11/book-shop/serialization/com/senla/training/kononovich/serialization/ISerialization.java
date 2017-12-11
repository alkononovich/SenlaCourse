package com.senla.training.kononovich.serialization;

public interface ISerialization {
	public void serialToFile(Object o, String path);

	public Object serialFromFile(String path);
}

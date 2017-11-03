package com.senla.training.kononovich.dependencyinjection.propertyworker;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyLoader {
	private static PropertyLoader propertyLoader;

	private Properties properties = new Properties();

	public static PropertyLoader getInstance(String confName) {
		if (propertyLoader == null) {
			propertyLoader = new PropertyLoader(confName);
		}
		return propertyLoader;
	}

	private PropertyLoader(String confName) {

		try (FileInputStream fis = new FileInputStream(confName)) {
			properties.load(fis);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public String getProperty(String name) {

		return properties.getProperty(name);

	}
}

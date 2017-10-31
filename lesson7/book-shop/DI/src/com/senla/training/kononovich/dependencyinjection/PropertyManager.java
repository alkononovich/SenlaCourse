package com.senla.training.kononovich.dependencyinjection;

import java.io.FileInputStream;
import java.util.Properties;


public class PropertyManager {
	private static final String DEPENDENCY_INJECTION = "DependencyInjection.txt";

	private static PropertyManager propertyManager;
	
	private Properties properties = new Properties();
	
	public static PropertyManager getInstance() {
		if (propertyManager == null) {
			propertyManager = new PropertyManager();
		}
		return propertyManager;
	}

	private PropertyManager() {
		//properties = new Properties();

		try (FileInputStream fis = new FileInputStream(DEPENDENCY_INJECTION)) {
			properties.load(fis);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	
	public String getClassName(String name) {

		return properties.getProperty(name);

	}
}

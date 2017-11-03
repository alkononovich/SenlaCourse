package com.senla.training.kononovich.dependencyinjection.propertyworker;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.service.BookClaimService;

public class PropertyLoader {
	private static final Logger logger = Logger.getLogger(BookClaimService.class);
	private static Map<String, Properties> props = new HashMap<String, Properties>();
	private Properties properties;

	public PropertyLoader(String confName) {
		if (props.containsKey(confName)) {
			properties = props.get(confName);
		} else {
			try (FileInputStream fis = new FileInputStream(confName)) {
				properties = new Properties();
				properties.load(fis);
				props.put(confName, properties);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}

	}

	public String getProperty(String propName) {
		return properties.getProperty(propName);

	}
}

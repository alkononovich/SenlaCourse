package com.senla.training.kononovich.dependencyinjection.propertyworker;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyWorker implements IPropertyWorker {
	private static final Logger logger = Logger.getLogger(PropertyWorker.class);
	private static PropertyWorker instance;

	private Map<String, Properties> confs = new HashMap<>();
	
	public static PropertyWorker getInstance() {
		if (instance == null) {
			instance = new PropertyWorker();
		}
		return instance;
	}

	public String getProps(String confName, String propName) {
		String prop = null;
		if (confs.containsKey(confName)) {
			prop = confs.get(confName).getProperty(propName);
		} else {
			Properties conf = new Properties();
			try (FileInputStream fis = new FileInputStream(confName)) {
				conf.load(fis);
				confs.put(confName, conf);
				prop = conf.getProperty(propName);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return prop;
	}
}

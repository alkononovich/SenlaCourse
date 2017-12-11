package com.senla.training.kononovich.config;

import java.io.*;
import java.util.Properties;

public class Configuration implements IConfiguration{
	private static final String TOGGLE_ON_COMPLETE_CLAIM = "claim.toggleOnComplete";
	private static final String OLD_MONTH = "book.oldMonth";
	private static final String SERIAL_PATH = "serialization.filePath";
	private static final String FILE_NOT_FOUND = "File not found";
	private static final String CONFIG_PROPERTIES = "config.properties";
	private Properties properties;
	private Props props;
	private Boolean toInit = false;
	private static Configuration instance;

	public Configuration() {
		properties = new Properties();
	}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}

	public Props getProps() {
		if (!toInit) {
			initProps();
		}
		return props;
	}

	private void initProps() {
		try (FileInputStream fis = new FileInputStream(CONFIG_PROPERTIES)) {
			properties.load(fis);
			props = new Props();
			props.setToggleOnCompleteClaim(Boolean.parseBoolean(properties.getProperty(TOGGLE_ON_COMPLETE_CLAIM)));
			props.setOldMonth(Integer.parseInt(properties.getProperty(OLD_MONTH)));
			props.setSerialPath(properties.getProperty(SERIAL_PATH));
			toInit = true;
		} catch (Exception e) {
			System.err.println(FILE_NOT_FOUND);
		}
	}
}

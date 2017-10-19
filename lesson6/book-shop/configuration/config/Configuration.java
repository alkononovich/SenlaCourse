package config;

import java.io.*;
import java.util.Properties;


public class Configuration {
	private static final String TOGGLE_ON_COMPLETE_CLAIM = "toggleOnCompleteClaim";
	private static final String OLD_MONTH = "oldMonth";
	private static final String SERIAL_PATH = "serialPath";
	private static final String FILE_NOT_FOUND = "File not found";
	private static final String CONFIG_PROPERTIES = "config.properties";
	private Properties properties = new Properties();
	private Props props;
	private Boolean toInit = false;
	private static Configuration instance;

	private Configuration() {
	}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}
		return instance;
	}
	public Props getProps() {
		if (toInit == false) {
			initProps();
		}
		return props;
	}

	private void initProps() {
		try (FileInputStream fis = new FileInputStream(CONFIG_PROPERTIES)) {
			properties.load(fis);
			props.setToggleOnCompleteClaim(Boolean.parseBoolean(TOGGLE_ON_COMPLETE_CLAIM));
			props.setOldMonth(Integer.parseInt(OLD_MONTH));
			props.setSerialPath(SERIAL_PATH);

		} catch (Exception e) {
			System.err.println(FILE_NOT_FOUND);
		} finally {
			toInit = true;
		}
	}
}

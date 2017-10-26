package com.senla.training.kononovich.annotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class AutoConfigurer {
	private static final String NULL = "";

	public void configureObj(Object obj) {
		Properties properties = new Properties();

		Class<? extends Object> mClassObject = obj.getClass();
		Field[] fields = mClassObject.getFields();
		for (Field field : fields) {
			ConfigPropery annotation = field.getAnnotation(ConfigPropery.class);
			String confName;
			String propName;
			Class<?> type;
			if (annotation.configName().equals(NULL)) {
				confName = "config.properties";
			} else {
				confName = annotation.configName();
			}
			
			if (annotation.propertyName().equals(NULL)) {
				StringBuffer str = new StringBuffer();
				str.append(mClassObject.getName()).append(".").append(field.getName());
				propName = str.toString();
			} else {
				propName = annotation.propertyName();
			}
			
			if (annotation.type() == Object.class) {
				type = field.getType();
			} else {
				type = annotation.type();
			}
			try (FileInputStream fis = new FileInputStream(confName)) {
				properties.load(fis);
				field.set(type, properties.getProperty(propName));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

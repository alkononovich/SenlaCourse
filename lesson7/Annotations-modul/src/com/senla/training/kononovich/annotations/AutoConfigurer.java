package com.senla.training.kononovich.annotations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class AutoConfigurer {
	
	public void configureObj(Object obj) {
		Properties properties = new Properties();

		Class<? extends Object> mClassObject = obj.getClass();
		Field[] fields = mClassObject.getFields();
		for (Field field : fields) {
			ConfigPropery annotation = (ConfigPropery) field.getAnnotation(ConfigPropery.class);
			try (FileInputStream fis = new FileInputStream(annotation.configName())) {
				properties.load(fis);
				field.set(annotation.type(), properties.getProperty(annotation.propertyName()));
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

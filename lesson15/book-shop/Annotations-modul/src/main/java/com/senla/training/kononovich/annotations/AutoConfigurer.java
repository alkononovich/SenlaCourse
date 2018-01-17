package com.senla.training.kononovich.annotations;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.dependencyinjection.propertyworker.IPropertyWorker;

public class AutoConfigurer implements IAutoConfigurer {
	private static final Logger logger = Logger.getLogger(AutoConfigurer.class);
	private static final String NULL = "";
	private SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
	private IPropertyWorker propertyWorker = (IPropertyWorker)DependencyInjection.getClassInstance(IPropertyWorker.class);

	public Object configureObj(Object obj) {

		Class<? extends Object> mClassObject = obj.getClass();

		Field[] fields = mClassObject.getDeclaredFields();
		for (Field field : fields) {
			boolean isFieldAccessible = field.isAccessible();
			field.setAccessible(true);
			if (field.getAnnotation(ConfigProperty.class) != null) {
				ConfigProperty annotation = field.getAnnotation(ConfigProperty.class);
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
				try {
					if (type.equals(Integer.class)) {
						field.setInt(obj, Integer.parseInt(propertyWorker.getProps(confName, propName)));
					} else if (type.equals(Date.class)) {
						field.set(obj, format.parse(propertyWorker.getProps(confName, propName)));
					} else if (type.equals(String.class)) {
						field.set(obj, propertyWorker.getProps(confName, propName));
					} else if (type.equals(Boolean.class)) {
						field.set(obj, Boolean.parseBoolean(propertyWorker.getProps(confName, propName)));
					}
				} catch (NumberFormatException e) {
					logger.error(e.getMessage(), e);
				} catch (IllegalArgumentException e) {
					logger.error(e.getMessage(), e);
				} catch (IllegalAccessException e) {
					logger.error(e.getMessage(), e);
				} catch (ParseException e) {
					logger.error(e.getMessage(), e);
				}

			}
			field.setAccessible(isFieldAccessible);
		}
		return obj;
	}

}

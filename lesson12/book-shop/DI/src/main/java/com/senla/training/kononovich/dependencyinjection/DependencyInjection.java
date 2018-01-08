package com.senla.training.kononovich.dependencyinjection;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.senla.training.kononovich.dependencyinjection.propertyworker.PropertyWorker;

public class DependencyInjection {
	private static final Logger logger = Logger.getLogger(DependencyInjection.class);
	private static final String DEPENDENCY_INJECTION = "DependencyInjection.txt";

	private static Map<String, Object> objects = new HashMap<String, Object>();

	public static Object getClassInstance(Class<?> work) {
		Object obj = null;
		if (objects.containsKey(work.getName())) {
			obj = objects.get(work.getName());
		} else {
			PropertyWorker propertyWorker = PropertyWorker.getInstance();
			String implClassName = propertyWorker.getProps(DEPENDENCY_INJECTION, work.getName());
			try {
				Class<?> implClass = Class.forName(implClassName);
				obj = implClass.newInstance();
				objects.put(work.getName(), obj);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);	
			}
		}
		return obj;
	}

}

package com.senla.training.kononovich.dependencyinjection;

import java.util.HashMap;
import java.util.Map;


public class DependencyInjection {
	private static Map<String, Object> objects = new HashMap<String, Object>();

	public static Object getClassInstance(Class<?> work) {
		Object obj = null;
		if (objects.containsKey(work.getName())) {

			obj = objects.get(work.getName());
		} else {
			String implClassName = PropertyManager.getInstance().getClassName(work.getName());
			try {
				Class<?> implClass = Class.forName(implClassName);
				obj = implClass.newInstance();
				objects.put(implClassName, obj);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return obj;
	}

}

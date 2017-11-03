package com.senla.training.kononovich.dependencyinjection;

import com.senla.training.kononovich.dependencyinjection.propertyworker.PropertyWorker;

public class DependencyInjection {
	private static final String DEPENDENCY_INJECTION = "DependencyInjection.txt";
	private static PropertyWorker propertyWorker = new PropertyWorker();

	public static Object getClassInstance(Class<?> work) {
		Object obj = null;
		String implClassName = propertyWorker.getProps(DEPENDENCY_INJECTION, work.getName());
		try {
			Class<?> implClass = Class.forName(implClassName);
			obj = implClass.newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}
		return obj;
	}

}

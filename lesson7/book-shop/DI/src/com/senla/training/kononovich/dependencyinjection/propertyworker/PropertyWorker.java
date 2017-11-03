package com.senla.training.kononovich.dependencyinjection.propertyworker;

import java.util.HashMap;
import java.util.Map;

public class PropertyWorker implements IPropertyWorker{
	private Map<String, String> objects = new HashMap<String, String>();
	
	public String getProps(String confName, String propName) {
		String obj = null;
		if (objects.containsKey(propName)) {
			obj = objects.get(propName);
		} else {
			obj = PropertyLoader.getInstance(confName).getProperty(propName);
			objects.put(propName, obj);
		} 			
		return obj;
	}
}

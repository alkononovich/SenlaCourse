package com.senla.training.kononovich.annotations;

import java.util.Date;

public class MyClass {
	@ConfigProperty(configName = "conf.properties", propertyName = "class.name", type = String.class)
	private String name;
	
	@ConfigProperty(configName = "conf.properties", propertyName = "class.count", type = Integer.class)
	public int count;
	
	@ConfigProperty(configName = "conf.properties", propertyName = "class.date", type = Date.class)
	public Date date;
	
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append(name).append(" ").append(count).append(" ").append(date);
		return str.toString();
	}
}

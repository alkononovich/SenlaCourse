package com.senla.training.kononovich.client;

public class Request {
	
	private String methodName;
	private Object[] params;
	
	public Request(String methodName) {
		this.methodName = methodName;
		this.params = null;
	}
	
	public Request(String methodName, Object...objects) {
		this.methodName = methodName;
		this.params = objects;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}
	
	
}

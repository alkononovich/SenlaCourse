package com.senla.training.kononovich.request;

import java.io.Serializable;

public class Request implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5616050237393526811L;
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

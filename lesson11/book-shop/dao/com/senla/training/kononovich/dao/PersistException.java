package com.senla.training.kononovich.dao;

public class PersistException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2462905693260023386L;

	public PersistException(Exception e) {
		super(e);
	}
	
	public PersistException(String e) {
		super(e);
	}
}

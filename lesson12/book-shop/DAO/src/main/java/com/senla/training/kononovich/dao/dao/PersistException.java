package com.senla.training.kononovich.dao.dao;


public class PersistException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8619000431262361024L;

	public PersistException() {
    }

    public PersistException(String message) {
        super(message);
    }

    public PersistException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersistException(Throwable cause) {
        super(cause);
    }

    public PersistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}


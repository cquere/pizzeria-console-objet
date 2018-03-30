package fr.pizzeria.exception;

public class ArgumentNullException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArgumentNullException() {
	}
	
	public ArgumentNullException(String msg) {
		super(msg);
	}
}

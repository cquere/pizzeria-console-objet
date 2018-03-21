package fr.pizzeria.exception;

public class UpdatePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UpdatePizzaException() {
	}

	public UpdatePizzaException(String msg) {
		super("La pizza ne peu pas être modifié : " + msg);
	}
}

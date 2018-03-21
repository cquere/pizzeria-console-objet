package fr.pizzeria.exception;

public class DeletePizzaException extends StockageException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DeletePizzaException() {
	}

	public DeletePizzaException(String msg) {
		super("La pizza ne peu pas être supprimée : " + msg);
	}
}

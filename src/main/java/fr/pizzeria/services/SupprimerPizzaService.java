package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.StockageException;

public class SupprimerPizzaService extends MenuService {

	@Override
	public void executeUC(IPizzaDao pizzaMemDao, Scanner scan) throws StockageException {
		System.out.println("Suppression d’une pizza :");
		System.out.println("Veuillez choisir le code de la pizza à supprimer :");
		String code = scan.next();
		code = code.toUpperCase();
		if (pizzaMemDao.pizzaExists(code)) {
			pizzaMemDao.deletePizza(code);
		} else {
			throw new DeletePizzaException(code + " : code pizza inexistant");
		}
	}
}

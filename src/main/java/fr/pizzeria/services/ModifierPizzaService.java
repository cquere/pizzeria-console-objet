package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) throws StockageException {
		System.out.println("Mise à jour d’une pizza :");
		System.out.println("Veuillez saisir le code :");
		String oldCode = scan.next();
		oldCode = oldCode.toUpperCase();
		System.out.println("Veuillez saisir le nouveau code :");
		String code = scan.next();
		code = code.toUpperCase();
		System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
		String libelle = scan.next();
		System.out.println("Veuillez saisir le nouveau prix :");

		double prix = Double.parseDouble(scan.next());
		if (pizzaMemDao.pizzaExists(oldCode)) {
			if (code.length() != 3) {
				throw new UpdatePizzaException("Le code Pizza doit contenir 3 caractères");
			} else if (prix <= 0) {
				throw new UpdatePizzaException("Le prix de la Pizza doit être strictement positif");
			} else {
				pizzaMemDao.updatePizza(oldCode, new Pizza(code, libelle, prix));
			}
		} else {
			throw new UpdatePizzaException(oldCode + " : code pizza inexistant");
		}
	}

}

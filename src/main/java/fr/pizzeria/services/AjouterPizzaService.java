package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) throws StockageException {
		System.out.println("Ajout d’une nouvelle pizza :");
		System.out.println("Veuillez saisir le code :");
		String code = scan.next();
		code = code.toUpperCase();
		System.out.println("Veuillez saisir le nom (sans espace) :");
		String libelle = scan.next();
		System.out.println("Veuillez saisir le prix :");
		double prix = Double.parseDouble(scan.next());

		if (code.length() != 3) {
			throw new UpdatePizzaException("Le code Pizza doit contenir 3 caractères");
		} else if (prix <= 0) {
			throw new UpdatePizzaException("Le prix de la Pizza doit être strictement positif");
		} else {
			pizzaMemDao.saveNewPizza(new Pizza(code, libelle, prix));
		}
	}
}

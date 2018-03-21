package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) {
		System.out.println("Mise à jour d’une pizza :");

		System.out.println("Veuillez saisir le code :");
		String oldCode = scan.next();

		System.out.println("Veuillez saisir le nouveau code :");
		String code = scan.next();
		System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
		String libelle = scan.next();
		System.out.println("Veuillez saisir le nouveau prix :");

		double prix = Double.parseDouble(scan.next());
		if (pizzaMemDao.pizzaExists(oldCode)) {
			pizzaMemDao.updatePizza(oldCode, new Pizza(code, libelle, prix));
		}
	}

}

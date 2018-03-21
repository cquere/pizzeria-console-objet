package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;

public class SupprimerPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) {
		System.out.println("Suppression d’une pizza :");

		System.out.println("Veuillez choisir le code de la pizza à supprimer :");
		String code = scan.next();
		if (pizzaMemDao.pizzaExists(code)) {
			pizzaMemDao.deletePizza(code);
		}
	}
}

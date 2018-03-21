package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) {
		System.out.println("Ajout d’une nouvelle pizza :");

		System.out.println("Veuillez saisir le code :");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace) :");
		String libelle = scan.next();
		System.out.println("Veuillez saisir le prix :");
		double prix = Double.parseDouble(scan.next());
		pizzaMemDao.saveNewPizza(new Pizza(code, libelle, prix));

	}

}

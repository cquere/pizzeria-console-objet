package fr.pizzeria.console;

import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	private static Scanner scan;
	private static PizzaMemDao pizzaMemDao;

	public static void main(String[] args) {
		pizzaMemDao = new PizzaMemDao();

		scan = new Scanner(System.in);
		initPizzaList();
		showMenu();
		while (analyseScan(Integer.parseInt(scan.next())) != 99) {
			showMenu();
		}
	}

	private static void showMenu() {
		System.out.println("***** Pizzeria Administration *****");
		System.out.println("1. Lister les pizzas");
		System.out.println("2. Ajouter une nouvelle pizza");
		System.out.println("3. Mettre à jour une pizza");
		System.out.println("4. Supprimer une pizza");
		System.out.println("99. Sortir");
	}

	private static int analyseScan(int scanResult) {

		switch (scanResult) {
		case 1:
			System.out.println("Liste des pizzas : ");
			showPizzaList();
			break;
		case 2:
			System.out.println("Ajout d’une nouvelle pizza :");
			addPizza();
			break;
		case 3:
			System.out.println("Mise à jour d’une pizza :");
			updatePizza();
			break;
		case 4:
			System.out.println("Suppression d’une pizza");
			deletePizza();
			break;
		case 99:
			System.out.println("Aurevoir ☹");
			break;
		}
		return scanResult;
	}

	private static void initPizzaList() {

	}

	private static void showPizzaList() {

		List<Pizza> pizzaList = pizzaMemDao.findAllPizzas();

		for (Object object : pizzaList) {
			Pizza p = (Pizza) object;
			System.out.println(p.toString());
		}

	}

	private static void addPizza() {
		System.out.println("Veuillez saisir le code :");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace) :");
		String libelle = scan.next();
		System.out.println("Veuillez saisir le prix :");
		double prix = Double.parseDouble(scan.next());
		pizzaMemDao.saveNewPizza(new Pizza(code, libelle, prix));

	}

	private static void updatePizza() {

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

	private static void deletePizza() {
		System.out.println("Veuillez choisir le code de la pizza à supprimer :");
		String code = scan.next();
		if (pizzaMemDao.pizzaExists(code)) {
			pizzaMemDao.deletePizza(code);
		}

	}
}

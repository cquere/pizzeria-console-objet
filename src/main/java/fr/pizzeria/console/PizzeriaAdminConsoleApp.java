package fr.pizzeria.console;


import java.util.Scanner;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.services.MenuService;
import fr.pizzeria.services.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	private static Scanner scan;
	private static PizzaMemDao pizzaMemDao;

	public static void main(String[] args) {
		pizzaMemDao = new PizzaMemDao();

		scan = new Scanner(System.in);
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

		MenuService service;

		switch (scanResult) {
		case 99:
			System.out.println("Aurevoir ☹");
			break;
		default:
			try {
				MenuServiceFactory.getMenuService(scanResult).executeUC(pizzaMemDao, scan);				
			} catch (Exception e) {
				System.out.println("Mauvaise saisi de Menu!");
			}
			break;
		}
		return scanResult;
	}
}

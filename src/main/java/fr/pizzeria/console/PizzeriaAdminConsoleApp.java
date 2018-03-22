package fr.pizzeria.console;

import java.util.Scanner;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.dao.PizzaTxtDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.services.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	private static Scanner scan;
	private static PizzaMemDao pizzaMemDao;

	public static void main(String[] args) {
		pizzaMemDao = new PizzaMemDao();
		PizzaTxtDao p = new PizzaTxtDao();
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
		System.out.println("5. Exportez la liste des pizza en PDF");
		System.out.println("99. Sortir");
	}

	private static int analyseScan(int scanResult) {

		switch (scanResult) {
		case 99:
			System.out.println("Aurevoir ☹");
			break;
		default:
			try {
				MenuServiceFactory.getMenuService(scanResult).executeUC(pizzaMemDao, scan);
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			} catch (Exception e) {
				System.out.println("Ce numéro de Menu n'existe pas : " + scanResult);
			}
			break;
		}
		return scanResult;
	}
}

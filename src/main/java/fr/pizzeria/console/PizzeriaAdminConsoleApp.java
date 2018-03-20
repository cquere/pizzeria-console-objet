package fr.pizzeria.console;

import java.util.Scanner;

public class PizzeriaAdminConsoleApp {

	private static Scanner scan;

	public static void main(String[] args) {
		scan = new Scanner(System.in);
		showMenu();
		while (analyseScan(scan.nextInt()) != 99) {
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
			System.out.println("Liste des pizzas");
			break;
		case 2:
			System.out.println("Ajout d’une nouvelle pizza");
			break;
		case 3:
			System.out.println("Mise à jour d’une pizza");
			break;
		case 4:
			System.out.println("Suppression d’une pizza");
			break;
		case 99:
			System.out.println("Aurevoir ☹");
			break;
		}
		return scanResult;
	}
}

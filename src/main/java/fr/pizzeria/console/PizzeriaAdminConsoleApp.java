package fr.pizzeria.console;

import java.awt.image.PixelInterleavedSampleModel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang.ArrayUtils;

import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {

	private static Scanner scan;
	// private static Pizza[] pizzaList;
	private static List<Pizza> pizzaList;

	public static void main(String[] args) {
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
		pizzaList = new ArrayList<Pizza>();
		pizzaList.add(new Pizza(0, "PEP", "Pépéroni", 12.50));
		pizzaList.add(new Pizza(1, "MAR", "Margherita", 14.00));
		pizzaList.add(new Pizza(2, "REIN", "La Reine", 11.50));
		pizzaList.add(new Pizza(3, "FRO", "La 4 fromages", 12.00));
		pizzaList.add(new Pizza(4, "CAN", "La cannibale", 12.50));
		pizzaList.add(new Pizza(5, "SAV", "La savoyarde", 13.00));
		pizzaList.add(new Pizza(6, "ORI", "L'orientale", 13.50));
		pizzaList.add(new Pizza(7, "IND", "L'indienne", 14.00));
	}

	private static void showPizzaList() {
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

		pizzaList.add(new Pizza(code, libelle, prix));
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
		int i;

		for (Object object : pizzaList) {
			Pizza p = (Pizza) object;
			if (p.getCode().equals(oldCode)) {
				p.setPizza(code, libelle, prix);
			}
		}

	}

	private static void deletePizza() {
		System.out.println("Veuillez choisir le code de la pizza à supprimer :");
		String code = scan.next();

		for (Iterator<Pizza> iter = pizzaList.iterator(); iter.hasNext();) {
			Pizza p = (Pizza) iter.next();
			if (p.getCode().equals(code)) {
				iter.remove();
			}
		}
	}
}

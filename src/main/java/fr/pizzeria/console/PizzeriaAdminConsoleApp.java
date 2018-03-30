package fr.pizzeria.console;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.services.MenuServiceFactory;

public class PizzeriaAdminConsoleApp {

	private static final Logger LOG = LoggerFactory.getLogger("INFO");
	private static final Logger LOGERROR = LoggerFactory.getLogger("ERROR");

	private static Scanner scan;
	private static PizzaMemDao pizzaMemDao;

	public static void main(String[] args) {
		pizzaMemDao = new PizzaMemDao();
		// PizzaTxtDao p = new PizzaTxtDao();
		scan = new Scanner(System.in);
		showMenu();
		while (analyseScan(Integer.parseInt(scan.next())) != 99) {
			showMenu();
		}
	}

	private static void showMenu() {
		LOG.info("***** Pizzeria Administration *****");
		LOG.info("1. Lister les pizzas");
		LOG.info("2. Ajouter une nouvelle pizza");
		LOG.info("3. Mettre à jour une pizza");
		LOG.info("4. Supprimer une pizza");
		LOG.info("5. Exportez la liste des pizza en PDF");
		LOG.info("99. Sortir");
	}

	private static int analyseScan(int scanResult) {

		switch (scanResult) {
		case 99:
			LOG.info("Aurevoir ☹");
			break;
		default:
			try {
				MenuServiceFactory.getMenuService(scanResult).executeUC(pizzaMemDao, scan);
			} catch (StockageException e) {
				LOGERROR.error(e.getMessage());
			} catch (Exception e) {
				LOGERROR.error("Ce numéro de Menu n'existe pas : " + scanResult);
			}
			break;
		}
		return scanResult;
	}
}

package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.Validator;

public class AjouterPizzaService extends MenuService {

	@Override
	public void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) throws StockageException {
		System.out.println("Ajout d’une nouvelle pizza :");

		System.out.println("Veuillez saisir le code :");
		String code = scan.next();
		code = code.toUpperCase();

		System.out.println("Veuillez saisir le nom (sans espace) :");
		String libelle = scan.next();

		System.out.println("Veuillez saisir la catégorie de la pizza : \n1. Viande\n2. Poisson\n3. Sans Viande");
		int categ = Integer.parseInt(scan.next());

		System.out.println("Veuillez saisir le prix :");
		double prix = Double.parseDouble(scan.next());

		
		if (categ != 1 && categ != 2 && categ != 3) {
			throw new UpdatePizzaException("Ce choix de catégorie n'est pas disponible");
		} else {
			CategoriePizza categorie = CategoriePizza.getByIndex(categ);
			Pizza p = new Pizza(code, libelle, prix, categorie);
			Validator.checkRule(p);
			pizzaMemDao.saveNewPizza(p);
		}
	}
}

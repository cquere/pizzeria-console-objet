package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaService extends MenuService {

	@Override
	public void executeUC(IPizzaDao pizzaMemDao, Scanner scan) throws StockageException {
		System.out.println("Mise à jour d’une pizza :");
		
		System.out.println("Veuillez saisir le code :");
		String oldCode = scan.next();
		oldCode = oldCode.toUpperCase();
		
		System.out.println("Veuillez saisir le nouveau code :");
		String code = scan.next();
		code = code.toUpperCase();
		
		System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
		String libelle = scan.next();
		
		System.out.println("Veuillez saisir la catégorie de la pizza : \n1. Viande\n2. Poisson\n3. Sans Viande");
		int categ = Integer.parseInt(scan.next());
		
		System.out.println("Veuillez saisir le nouveau prix :");
		double prix = Double.parseDouble(scan.next());
		
		if (pizzaMemDao.pizzaExists(oldCode)) {
			if (code.length() != 3) {
				throw new UpdatePizzaException("Le code Pizza doit contenir 3 caractères");
			} else if (prix <= 0) {
				throw new UpdatePizzaException("Le prix de la Pizza doit être strictement positif");
			}else if (categ != 1 && categ != 2 && categ != 3) {
				throw new UpdatePizzaException("Ce choix de catégorie n'est pas disponible");
			} else {
				CategoriePizza categorie = CategoriePizza.getByIndex(categ);
				pizzaMemDao.updatePizza(oldCode, new Pizza(code, libelle, prix, categorie));
			}
		} else {
			throw new UpdatePizzaException(oldCode + " : code pizza inexistant");
		}
	}

}

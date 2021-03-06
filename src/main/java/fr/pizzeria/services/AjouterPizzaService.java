package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.Validator;

public class AjouterPizzaService extends MenuService {
	
	@Override
	public void executeUC(IPizzaDao pizzaMemDao, Scanner scan) throws StockageException, ArgumentNullException {
		if (pizzaMemDao == null || scan == null)
			throw new ArgumentNullException();
		
		LOG.info("Ajout d’une nouvelle pizza :");

		LOG.info("Veuillez saisir le code :");
		String code = scan.next();
		code = code.toUpperCase();

		LOG.info("Veuillez saisir le nom (sans espace) :");
		String libelle = scan.next();

		LOG.info("Veuillez saisir la catégorie de la pizza : \n1. Viande\n2. Poisson\n3. Sans Viande");
		int categ = Integer.parseInt(scan.next());

		LOG.info("Veuillez saisir le prix :");
		double prix = Double.parseDouble(scan.next());

		
		if (categ != 1 && categ != 2 && categ != 3) {
			throw new UpdatePizzaException("Ce choix de catégorie n'est pas disponible");
		} else if (pizzaMemDao.pizzaExists(code) == false){
			CategoriePizza categorie = CategoriePizza.getByIndex(categ);
			Pizza p = new Pizza(code, libelle, prix, categorie);
			Validator.checkRule(p);
			pizzaMemDao.saveNewPizza(p);
		} else {
			throw new UpdatePizzaException("La pizza est deja presente dans la base de donnée");
		}
	}
}

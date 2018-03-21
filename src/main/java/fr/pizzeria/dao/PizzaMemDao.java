package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDao implements IPizzaDao {

	private  List<Pizza> pizzaList;

	public PizzaMemDao() {
		pizzaList = new ArrayList<Pizza>();
		pizzaList.add(new Pizza(0, "PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(1, "MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza(2, "REIN", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(3, "FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza(4, "CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(5, "SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(6, "ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(7, "IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
	}

	public List<Pizza> findAllPizzas() {
		return pizzaList;
	}

	public void saveNewPizza(Pizza pizza) {
		pizzaList.add(pizza);
	}

	public void updatePizza(String codePizza, Pizza pizza) {
		for (Object object : pizzaList) {
			Pizza p = (Pizza) object;
			if (p.getCode().equals(codePizza)) {
				p.setPizza(pizza.getCode(), pizza.getLibelle(), pizza.getPrix(), pizza.getCategorie());
			}
		}

	}

	public void deletePizza(String codePizza) {
		for (Iterator<Pizza> iter = pizzaList.iterator(); iter.hasNext();) {
			Pizza p = (Pizza) iter.next();
			if (p.getCode().equals(codePizza)) {
				iter.remove();
			}
		}
	}

	public Pizza findPizzaByCode(String codePizza) {
		Pizza p = null;
		for (Object object : pizzaList) {
			p = (Pizza) object;
			if (p.getCode().equals(codePizza)) {
				break;
			}
		}
		return p;
	}

	public boolean pizzaExists(String codePizza) {
		for (Object object : pizzaList) {
			Pizza p = (Pizza) object;
			if (p.getCode().equals(codePizza)) {
				return true;
			}
		}
		return false;
	}
}

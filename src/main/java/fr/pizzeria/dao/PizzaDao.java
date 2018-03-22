package fr.pizzeria.dao;

import java.util.Iterator;
import java.util.List;

import fr.pizzeria.model.Pizza;

public abstract class PizzaDao implements IPizzaDao {

	List<Pizza> pizzaList;

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

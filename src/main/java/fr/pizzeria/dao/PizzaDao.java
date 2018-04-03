package fr.pizzeria.dao;

import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public abstract class PizzaDao implements IPizzaDao {
	protected static final Logger LOGERROR = LoggerFactory.getLogger("ERROR");
	protected static final Logger LOG = LoggerFactory.getLogger("INFO");

	List<Pizza> pizzaList;

	public List<Pizza> findAllPizzas() {
		return pizzaList;
	}

	public void saveNewPizza(Pizza pizza) throws ArgumentNullException {
		if (pizza == null)
			throw new ArgumentNullException("pizza is NULL");
		pizzaList.add(pizza);
	}

	public void updatePizza(String codePizza, Pizza pizza) throws ArgumentNullException, UpdatePizzaException {
		if (codePizza == null || pizza == null)
			throw new ArgumentNullException("codePizza is NULL");
		Pizza p;
		if ( (p = this.findPizzaByCode(codePizza)) != null)
		{
			p.setPizza(pizza.getCode(), pizza.getLibelle(), pizza.getPrix(), pizza.getCategorie());
		}else {
			throw new UpdatePizzaException();
		}
		
	}

	public void deletePizza(String codePizza) throws ArgumentNullException {
		if (codePizza == null)
			throw new ArgumentNullException("codePizza is NULL");
		for (Iterator<Pizza> iter = pizzaList.iterator(); iter.hasNext();) {
			Pizza p = (Pizza) iter.next();
			if (p.getCode().equals(codePizza)) {
				iter.remove();
			}
		}
	}

	public Pizza findPizzaByCode(String codePizza) throws ArgumentNullException {
		if (codePizza == null)
			throw new ArgumentNullException("codePizza is NULL");
		Pizza p = null;
		for (Object object : pizzaList) {
			p = (Pizza) object;
			if (p.getCode().equals(codePizza)) {
				return p;
			}
		}
		return null;
	}

	public boolean pizzaExists(String codePizza) throws ArgumentNullException {
		if (codePizza == null)
			throw new ArgumentNullException("codePizza is NULL");
		return pizzaList.stream().anyMatch(p -> p.getCode().equals(codePizza));
	}

}

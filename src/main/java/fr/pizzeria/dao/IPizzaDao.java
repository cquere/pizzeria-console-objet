package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	List<Pizza> findAllPizzas();

	void saveNewPizza(Pizza pizza) throws ArgumentNullException;

	void updatePizza(String codePizza, Pizza pizza) throws ArgumentNullException, UpdatePizzaException;

	void deletePizza(String codePizza) throws ArgumentNullException;

	Pizza findPizzaByCode(String codePizza) throws ArgumentNullException;

	boolean pizzaExists(String codePizza) throws ArgumentNullException;
}

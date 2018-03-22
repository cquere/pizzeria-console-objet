package fr.pizzeria.dao;

import java.util.ArrayList;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDao extends PizzaDao implements IPizzaDao {


	public PizzaMemDao() {
		pizzaList = new ArrayList<Pizza>();
		pizzaList.add(new Pizza(0, "PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(1, "MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza(2, "REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(3, "FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzaList.add(new Pizza(4, "CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(5, "SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(6, "ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzaList.add(new Pizza(7, "IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
	}

}

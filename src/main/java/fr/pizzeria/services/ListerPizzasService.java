package fr.pizzeria.services;

import java.util.List;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzasService extends MenuService {

	@Override
	public void executeUC(IPizzaDao pizzaMemDao, Scanner scan) {
		System.out.println("Liste des pizzas : ");

		List<Pizza> pizzaList = pizzaMemDao.findAllPizzas();

		for (Object object : pizzaList) {
			Pizza p = (Pizza) object;
			System.out.println(p.toString());
		}
	}

}

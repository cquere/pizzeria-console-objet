package fr.pizzeria.services;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.StockageException;

public abstract class MenuService {

	public abstract void executeUC(PizzaMemDao pizzaMemDao, Scanner scan) throws StockageException;
}

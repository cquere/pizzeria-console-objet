package fr.pizzeria.services;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.StockageException;

public abstract class MenuService {
	protected static final Logger LOG = LoggerFactory.getLogger("INFO");

	protected static final Logger LOGERROR = LoggerFactory.getLogger("ERROR");

	public abstract void executeUC(IPizzaDao pizzaMemDao, Scanner scan) throws StockageException;
}

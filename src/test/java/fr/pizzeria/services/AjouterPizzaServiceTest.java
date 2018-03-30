package fr.pizzeria.services;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

import javax.annotation.Generated;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.*;
import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaMemDao;
import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaServiceTest {

	/**
	 * Création d'une "Rule" qui va permettre de substituer le System.in utilisé
	 * par le Scanner par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();

	@Test
	public void testExecuteUC() throws Exception {
		AjouterPizzaService testSubject;
		IPizzaDao pizzaMemDao = new PizzaMemDao();

		List<Pizza> result = pizzaMemDao.findAllPizzas();
		int size = result.size();

		// J'alimente le mock
		systemInMock.provideLines("RER", "test", "2", "15");

		Scanner scan = new Scanner(System.in);

		testSubject = new AjouterPizzaService();
		testSubject.executeUC(pizzaMemDao, scan);

		result = pizzaMemDao.findAllPizzas();
		boolean isPresent = result.stream().anyMatch(p -> {
			return p.getCode().equals("RER") && p.getLibelle().equals("test") && p.getPrix() == 15
					&& p.getCategorie() == CategoriePizza.POISSON;
		});
		assertTrue(isPresent);
		assertTrue((size + 1) == result.size());
	}

	@Test(expected = UpdatePizzaException.class)
	public void testExecuteUC1() throws Exception {
		AjouterPizzaService testSubject;
		IPizzaDao pizzaMemDao = new PizzaMemDao();

		// J'alimente le mock
		systemInMock.provideLines("RER", "test", "5", "15");

		Scanner scan = new Scanner(System.in);

		testSubject = new AjouterPizzaService();
		testSubject.executeUC(pizzaMemDao, scan);
	}

	@Test(expected = ArgumentNullException.class)
	public void testExecuteUC2() throws Exception {
		AjouterPizzaService testSubject;
		Scanner scan = new Scanner(System.in);

		testSubject = new AjouterPizzaService();
		testSubject.executeUC(null, scan);
	}

	@Test(expected = ArgumentNullException.class)
	public void testExecuteUC3() throws Exception {
		AjouterPizzaService testSubject;
		testSubject = new AjouterPizzaService();
		testSubject.executeUC(null, null);
	}

	@Test(expected = ArgumentNullException.class)
	public void testExecuteUC4() throws Exception {
		AjouterPizzaService testSubject;

		testSubject = new AjouterPizzaService();
		testSubject.executeUC(new PizzaMemDao(), null);
	}
}
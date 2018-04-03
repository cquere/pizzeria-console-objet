package fr.pizzeria.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaMemDaoTest {

	@Test
	public void testFindAllPizzas() throws Exception {
		PizzaDao testSubject;
		List<Pizza> result;

		testSubject = new PizzaMemDao();
		result = testSubject.findAllPizzas();
		assertNotNull(result);
	}

	@Test
	public void testDeletePizza() throws ArgumentNullException {
		PizzaMemDao testSubject;
		String codePizza = "PEP";

		testSubject = new PizzaMemDao();
		testSubject.deletePizza(codePizza);

		assertFalse(testSubject.pizzaExists("PEP"));
	}

	@Test(expected = ArgumentNullException.class)
	public void testDeletePizza1() throws ArgumentNullException {
		PizzaMemDao testSubject = new PizzaMemDao();
		testSubject.deletePizza(null);
	}

	@Test
	public void testFindPizzaByCode() throws ArgumentNullException {
		PizzaMemDao testSubject;
		Pizza result;
		Pizza result1;

		testSubject = new PizzaMemDao();
		result = testSubject.findPizzaByCode("PEP");
		result1 = testSubject.findPizzaByCode("tyu");

		assertNotNull(result);
		assertEquals("FindPizzaByCode :", "PEP", result.getCode());
		assertNull(result1);
	}

	@Test(expected = ArgumentNullException.class)
	public void testFindPizzaByCode1() throws ArgumentNullException {
		PizzaMemDao testSubject = new PizzaMemDao();
		testSubject.findPizzaByCode(null);

	}

	@Test
	public void testPizzaExists() throws ArgumentNullException {
		PizzaMemDao testSubject;
		testSubject = new PizzaMemDao();
		assertTrue(testSubject.pizzaExists("PEP"));
		assertFalse(testSubject.pizzaExists("iui"));
	}

	@Test(expected = ArgumentNullException.class)
	public void testPizzaExists1() throws ArgumentNullException {
		PizzaMemDao testSubject;
		testSubject = new PizzaMemDao();
		assertTrue(testSubject.pizzaExists(null));
	}

	@Test
	public void testSaveNewPizza() throws ArgumentNullException {
		PizzaMemDao testSubject= new PizzaMemDao();
		Pizza pizza = new Pizza("RER", "test", 15, CategoriePizza.POISSON);
		List<Pizza> result = testSubject.findAllPizzas();
		int size = result.size();
		testSubject.saveNewPizza(pizza);

		result = testSubject.findAllPizzas();
		boolean isPresent = result.stream().anyMatch(p -> {
			return p.getCode().equals("RER") && p.getLibelle().equals("test") && p.getPrix() == 15
					&& p.getCategorie() == CategoriePizza.POISSON;
		});
		assertTrue(isPresent);
		assertTrue((size + 1) == result.size());
	}

	@Test(expected = ArgumentNullException.class)
	public void testSaveNewPizza1() throws ArgumentNullException {

		PizzaMemDao testSubject = new PizzaMemDao();
		testSubject.saveNewPizza(null);
	}

	@Test
	public void testUpdatePizza() throws ArgumentNullException, UpdatePizzaException {
		PizzaMemDao testSubject = new PizzaMemDao();
		Pizza pizza = new Pizza("RER", "test", 15, CategoriePizza.POISSON);
		testSubject.updatePizza("PEP", pizza);
		List<Pizza> result = testSubject.findAllPizzas();
		int size = result.size();
		testSubject.updatePizza("PEP", pizza);

		result = testSubject.findAllPizzas();
		boolean isPresent = result.stream().anyMatch(p -> {
			return p.getCode().equals("RER") && p.getLibelle().equals("test") && p.getPrix() == 15
					&& p.getCategorie() == CategoriePizza.POISSON;
		});

		assertFalse(testSubject.pizzaExists("PEP"));
		assertTrue(isPresent);
		assertTrue(size == result.size());
	}

	@Test (expected = ArgumentNullException.class)
	public void testUpdatePizza1() throws ArgumentNullException, UpdatePizzaException {
		PizzaMemDao testSubject = new PizzaMemDao();
		testSubject.updatePizza("PEP", null);
	}
	@Test (expected = ArgumentNullException.class)
	public void testUpdatePizza2() throws ArgumentNullException, UpdatePizzaException {
		PizzaMemDao testSubject = new PizzaMemDao();
		testSubject.updatePizza(null, new Pizza("RER", "test", 15, CategoriePizza.POISSON));
	}
	@Test (expected = ArgumentNullException.class)
	public void testUpdatePizza3() throws ArgumentNullException, UpdatePizzaException {
		PizzaMemDao testSubject = new PizzaMemDao();
		testSubject.updatePizza(null, null);
	}
}
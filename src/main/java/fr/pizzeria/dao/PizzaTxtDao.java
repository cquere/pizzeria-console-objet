package fr.pizzeria.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaTxtDao extends PizzaDao implements IPizzaDao {


	
	public PizzaTxtDao() throws NumberFormatException, ArgumentNullException {
		ResourceBundle rb = ResourceBundle.getBundle("conf");
		String path = rb.getString("path.absolute");
		path += "liste_pizza.txt";
		pizzaList = new ArrayList<Pizza>();

		File file = new File(path);
		List<String> list = null;
		try {
			list = FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			LOGERROR.error(e.getMessage());
		}

		for (String s : list) {
			LOG.info(s);
			String[] p = s.split("[;]+");
			saveNewPizza(new Pizza(p[0], p[1], Double.parseDouble(p[2]), CategoriePizza.valueOf(p[3].toUpperCase())));
		}

		// = new ArrayList <String>();
		//
		// list.add("PEP;Pépéroni;12.50;Viande");
		// list.add("MAR;Margherita;14.00;Sans Viande");
		// list.add("REI;La Reine;11.50;Viande");
		// list.add("FRO;La 4 fromages;12.00;Sans Viande");
		// list.add("CAN;La cannibale;12.50;Viande");
		// list.add("SAV;La savoyarde;13.00;Viande");
		// list.add("ORI;L'orientale;13.50;Viande");
		// list.add("IND;L'indienne;14.00;Viande");
		//
		// try {
		// FileUtils.writeLines(file, list);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// 			LOGERROR.error(e.getMessage());

		// }

	}


	public void updatePizza(String codePizza, Pizza pizza) throws ArgumentNullException, UpdatePizzaException {
		super.updatePizza(codePizza, pizza);
	}

	public void deletePizza(String codePizza) throws ArgumentNullException {
		super.deletePizza(codePizza);
	}

	public Pizza findPizzaByCode(String codePizza) throws ArgumentNullException {
		return super.findPizzaByCode(codePizza);
	}

	public boolean pizzaExists(String codePizza) throws ArgumentNullException {
		return super.pizzaExists(codePizza);
	}

}

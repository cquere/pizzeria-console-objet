package fr.pizzeria.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.utils.DataBase;

public class PizzaDbDao extends PizzaDao {

	private DataBase database;
	private Connection condb;
	private ResultSet insertPizza;

	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 */
	public PizzaDbDao() throws ClassNotFoundException, SQLException {
		database = new DataBase();
		try {
			saveNewPizza(new Pizza("RER", "This is a test", 12.60, CategoriePizza.VIANDE));
		} catch (ArgumentNullException e) {
			LOGERROR.error(e.getMessage());
		}
	}

	@Override
	public List<Pizza> findAllPizzas() {
		ResultSet resultats = null;
		condb = database.getConnection();
		List<Pizza> listPizza = new ArrayList<>();
		java.sql.PreparedStatement selectPizzaSt = null;

		try {
			selectPizzaSt = condb.prepareStatement("SELECT * FROM pizza_table");
			resultats = selectPizzaSt.executeQuery();
			while (resultats.next()) {
				listPizza.add(new Pizza(resultats.getString("CODE_PIZZA"), resultats.getString("LIBELLE_PIZZA"),
						resultats.getDouble("PRIX"), CategoriePizza.valueOf(resultats.getString("CATEGORIE"))));
			}
		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Action impossible");
		} finally {
			try {
				if (resultats != null)
					resultats.close();
				if (selectPizzaSt != null)
					selectPizzaSt.close();

			} catch (SQLException e) {
				LOGERROR.error(e.getMessage());
				throw new RuntimeException("Problème de connection a la base : Action impossible");
			}
			database.close(condb);
		}
		return listPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws ArgumentNullException {
		java.sql.PreparedStatement updatePizzaSt = null;

		condb = database.getConnection();
		try {

			updatePizzaSt = condb.prepareStatement(
					"INSERT IGNORE INTO pizza_table (CODE_PIZZA, LIBELLE_PIZZA, PRIX, CATEGORIE) VALUES (?, ?, ?, ?);");
			updatePizzaSt.setString(1, pizza.getCode());
			updatePizzaSt.setString(2, pizza.getLibelle());
			updatePizzaSt.setDouble(3, pizza.getPrix());
			updatePizzaSt.setString(4, pizza.getCategorie().toString());
			updatePizzaSt.executeUpdate();
		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Action impossible");
		} finally {
			
			try {
				if (updatePizzaSt != null)
				updatePizzaSt.close();
			} catch (SQLException e) {
				LOGERROR.error(e.getMessage());
				throw new RuntimeException("Problème de connection a la base : Action impossible");
			}
			database.close(condb);
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws ArgumentNullException, UpdatePizzaException {
		java.sql.PreparedStatement updatePizzaSt = null;

		condb = database.getConnection();
		try {
			updatePizzaSt = condb.prepareStatement(
					"UPDATE pizza_table SET CODE_PIZZA=?, LIBELLE_PIZZA=?, PRIX=?, CATEGORIE=? WHERE CODE_PIZZA=?;");
			updatePizzaSt.setString(1, pizza.getCode());
			updatePizzaSt.setString(2, pizza.getLibelle());
			updatePizzaSt.setDouble(3, pizza.getPrix());
			updatePizzaSt.setString(4, pizza.getCategorie().toString());
			updatePizzaSt.setString(5, codePizza);
			updatePizzaSt.executeUpdate();
		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Action impossible");
		} finally {
			try {
				if (updatePizzaSt != null)
				updatePizzaSt.close();
			} catch (SQLException e) {
				LOGERROR.error(e.getMessage());
				throw new RuntimeException("Problème de connection a la base : Action impossible");
			}
			database.close(condb);
		}
	}

	@Override
	public void deletePizza(String codePizza) throws ArgumentNullException {
		java.sql.PreparedStatement selectPizzaSt = null;

		condb = database.getConnection();
		try {
			selectPizzaSt = condb
					.prepareStatement("DELETE FROM pizza_table WHERE CODE_PIZZA=?");
			selectPizzaSt.setString(1, codePizza);
			selectPizzaSt.executeUpdate();
		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Action impossible");
		} finally {
			try {
				if(selectPizzaSt != null)
				selectPizzaSt.close();
			} catch (SQLException e) {
				LOGERROR.error(e.getMessage());
				throw new RuntimeException("Problème de connection a la base : Action impossible");
			}
			database.close(condb);
		}
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) throws ArgumentNullException {
		java.sql.PreparedStatement selectPizzaSt = null;
		ResultSet resultats = null;
		condb = database.getConnection();
		Pizza p = null;
		try {
			selectPizzaSt = condb
					.prepareStatement("SELECT * FROM pizza_table WHERE CODE_PIZZA=?");
			selectPizzaSt.setString(1, codePizza);

			resultats = selectPizzaSt.executeQuery();
			while (resultats.next()) {
				p = new Pizza(resultats.getString("CODE_PIZZA"), resultats.getString("LIBELLE_PIZZA"),
						resultats.getDouble("PRIX"), CategoriePizza.valueOf(resultats.getString("CATEGORIE")));
			}
		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Action impossible");
		} finally {
			
			try {
				if (resultats != null)
				resultats.close();
				if (selectPizzaSt != null)
				selectPizzaSt.close();
			} catch (SQLException e) {
				LOGERROR.error(e.getMessage());
				throw new RuntimeException("Problème de connection a la base : Action impossible");
			}
			database.close(condb);
		}
		return p;
	}

	@Override
	public boolean pizzaExists(String codePizza) throws ArgumentNullException {
		java.sql.PreparedStatement selectPizzaSt = null;
		ResultSet resultats = null;
		condb = database.getConnection();
		boolean res;

		try {
			 selectPizzaSt = condb
					.prepareStatement("SELECT * FROM pizza_table WHERE CODE_PIZZA=?");
			selectPizzaSt.setString(1, codePizza);

			resultats = selectPizzaSt.executeQuery();
			res = resultats.next();

		} catch (SQLException e) {
			LOGERROR.error(e.getMessage());
			throw new RuntimeException("Problème de connection a la base : Action impossible");
		} finally {
			try {
				resultats.close();
			} catch (SQLException e) {
				LOGERROR.error(e.getMessage());
				throw new RuntimeException("Problème de connection a la base : Action impossible");
			}
			try {
				if (selectPizzaSt != null)
				selectPizzaSt.close();
			} catch (SQLException e) {
				LOGERROR.error(e.getMessage());
				throw new RuntimeException("Problème de connection a la base : Action impossible");
			}
			database.close(condb);
		}
		return res;
	}
}

package fr.pizzeria.dao;

import java.util.List;
import java.util.ResourceBundle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaJpaDao extends PizzaDao {

	private EntityManagerFactory emf;
	private EntityManager em;
	private EntityTransaction et;
	private String persistenceUnit;

	public PizzaJpaDao() {
		ResourceBundle properties = ResourceBundle.getBundle("conf");
		persistenceUnit = properties.getString("persistenceunit.name");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> listPizza = null;

		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();

		Query q = em.createQuery("SELECT p FROM Pizza p");
		if (q != null) {
			listPizza = (List<Pizza>) q.getResultList();
		}

		em.close();
		emf.close();

		return listPizza;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws ArgumentNullException {
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
		et = em.getTransaction();
		et.begin();

		em.persist(pizza);

		et.commit();
		em.close();
		emf.close();
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws ArgumentNullException, UpdatePizzaException {
		

		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
		et = em.getTransaction();
		et.begin();

		Query q = em.createQuery("UPDATE Pizza p SET p.code=:code, p.libelle=:libelle, p.prix=:prix, p.categorie=:categorie  WHERE p.code = :codepizza");
		q.setParameter("code", pizza.getCode());
		q.setParameter("libelle", pizza.getLibelle());
		q.setParameter("prix", pizza.getPrix());
		q.setParameter("categorie", pizza.getCategorie());
		q.setParameter("codepizza", codePizza);

		q.executeUpdate();
		
		
		et.commit();
		em.close();
		emf.close();
	}

	@Override
	public void deletePizza(String codePizza) throws ArgumentNullException {
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
		et = em.getTransaction();
		et.begin();

		Query q = em.createQuery("DELETE FROM Pizza p WHERE p.code = :codepizza");
		q.setParameter("codepizza", codePizza);
		q.executeUpdate();

		
		et.commit();
		em.close();
		emf.close();
	}

	@Override
	public Pizza findPizzaByCode(String codePizza) throws ArgumentNullException {
		
		Pizza p = null;
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Pizza p WHERE p.code = :codepizza");
		q.setParameter("codepizza", codePizza);
		p = (Pizza) q.getSingleResult();
		em.close();
		emf.close();
		return p;
	}

	@Override
	public boolean pizzaExists(String codePizza) throws ArgumentNullException {
		boolean b = false;
		emf = Persistence.createEntityManagerFactory(persistenceUnit);
		em = emf.createEntityManager();
		Query q = em.createQuery("SELECT p FROM Pizza p WHERE p.code = :codepizza");
		q.setParameter("codepizza", codePizza);

		if (q.getResultList().size() != 0)
			b = true;
		em.close();
		emf.close();
		return b;
	}

}

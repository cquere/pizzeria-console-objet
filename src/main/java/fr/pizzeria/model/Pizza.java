package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import fr.pizzeria.utils.Rule;
import fr.pizzeria.utils.StringUtils;
import fr.pizzeria.utils.ToString;

// TODO: Auto-generated Javadoc
/**
 * The Class Pizza.
 */
@Entity
@Table(name="pizza_table")
public class Pizza {
	
	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	/** The code. */
	@Column(name="CODE_PIZZA", length=3, nullable= false, unique= true)
	@Rule(length = 3, staticLength = true)
	@ToString(uppercase = true, separateurApres = " -> ")
	private String code;
	
	/** The libelle. */
	@Column(name="LIBELLE_PIZZA", length=50, nullable= false)
	@ToString
	private String libelle;
	
	
	/** The prix. */
	@Column(name="PRIX", nullable= false)
	@Rule(minValue = 0.0, minValueActivated= true)
	@ToString(separateurAvant = " (", separateurApres = " €)")
	private double prix;
	
	
	
	/** The id count.
	 * Used only for the PizzaMem and PizzaTxt implementation
	 */
	private static int idCount = 0;
	
	
	/** The categorie. */
	@Column(name="CATEGORIE")
	@Enumerated(EnumType.STRING)
	@ToString(separateurAvant = " - ")
	private CategoriePizza categorie;

	/**
	 * Instantiates a new pizza.
	 */
	public Pizza(){
		
	}
	
	
	/**
	 * Instantiates a new pizza.
	 *
	 * @param code the code
	 * @param libelle the libelle
	 * @param prix the prix
	 * @param categorie the categorie
	 */
	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
		this.id = idCount++;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
	}

	/**
	 * Instantiates a new pizza.
	 *
	 * @param id the id
	 * @param code the code
	 * @param libelle the libelle
	 * @param prix the prix
	 * @param categorie the categorie
	 */
	public Pizza(int id, String code, String libelle, double prix, CategoriePizza categorie) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
		if (idCount <= id) {
			idCount++;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return StringUtils.builtString(this);
	}

	/**
	 * Sets the pizza.
	 *
	 * @param code the code
	 * @param libelle the libelle
	 * @param prix the prix
	 * @param categorie the categorie
	 */
	public void setPizza(String code, String libelle, double prix, CategoriePizza categorie) {
		setCode(code);
		setLibelle(libelle);
		setPrix(prix);
		setCategorie(categorie);
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the libelle.
	 *
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * Sets the libelle.
	 *
	 * @param libelle the new libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Gets the prix.
	 *
	 * @return the prix
	 */
	public double getPrix() {
		return prix;
	}

	/**
	 * Sets the prix.
	 *
	 * @param prix the new prix
	 */
	public void setPrix(double prix) {
		this.prix = prix;
	}

	/**
	 * Gets the categorie.
	 *
	 * @return the categorie
	 */
	public CategoriePizza getCategorie() {
		return categorie;
	}

	/**
	 * Sets the categorie.
	 *
	 * @param categorie the new categorie
	 */
	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
		long temp;
		temp = Double.doubleToLongBits(prix);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Pizza))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pizza other = (Pizza) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (libelle == null) {
			if (other.libelle != null)
				return false;
		} else if (!libelle.equals(other.libelle))
			return false;
		if (Double.doubleToLongBits(prix) != Double.doubleToLongBits(other.prix))
			return false;
		return true;
	}

}

package fr.pizzeria.model;

import fr.pizzeria.utils.Rule;
import fr.pizzeria.utils.StringUtils;
import fr.pizzeria.utils.ToString;

public class Pizza {
	private int id;
	@Rule(length = 3, staticLength = true)
	@ToString(uppercase = true, separateurApres = " -> ")
	private String code;
	@ToString
	private String libelle;
	@Rule(minValue = 0.0, minValueActivated= true)
	@ToString(separateurAvant = " (", separateurApres = " €)")
	private double prix;
	private static int idCount = 0;
	@ToString(separateurAvant = " - ")
	private CategoriePizza categorie;

	public Pizza(String code, String libelle, double prix, CategoriePizza categorie) {
		this.id = idCount++;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		this.categorie = categorie;
	}

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

	public String toString() {
		return StringUtils.builtString(this);
	}

	public void setPizza(String code, String libelle, double prix, CategoriePizza categorie) {
		setCode(code);
		setLibelle(libelle);
		setPrix(prix);
		setCategorie(categorie);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

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

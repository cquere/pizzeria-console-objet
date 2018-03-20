package fr.pizzeria.model;

public class Pizza {
	private int id;
	private String code;
	private String libelle;
	private double prix;
	private static int idCount = 0;

	public Pizza(String code, String libelle, double prix) {
		this.id = idCount++;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
	}

	public Pizza(int id, String code, String libelle, double prix) {
		this.id = id;
		this.code = code;
		this.libelle = libelle;
		this.prix = prix;
		if (idCount <= id) {
			idCount++;
		}
	}

	public String toString() {
		return code + " -> " + libelle + " (" + prix + " €)";
	}

	public void setPizza(String code, String libelle, double prix) {
		setCode(code);
		setLibelle(libelle);
		setPrix(prix);
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
}

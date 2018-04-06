package fr.pizzeria.model;

public enum CategoriePizza {
	VIANDE("VIANDE"), POISSON("POISSON"), SANS_VIANDE("SANS_VIANDE");

	private String name;

	private CategoriePizza(String categoriePizza) {
		this.name = categoriePizza;
	}

	public static CategoriePizza getByIndex(int index) {

		CategoriePizza categ = VIANDE;

		switch (index) {
		case 1:
			categ = CategoriePizza.VIANDE;
			break;
		case 2:
			categ = CategoriePizza.POISSON;
			break;
		case 3:
			categ = CategoriePizza.SANS_VIANDE;
			break;
		}
		return categ;
	}
	
	
	public String toString() {
		return name;
	}
}

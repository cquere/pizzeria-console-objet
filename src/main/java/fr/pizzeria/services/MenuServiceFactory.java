package fr.pizzeria.services;

public class MenuServiceFactory {

	public static MenuService getMenuService(int r) {
		MenuService service = null;

		switch (r) {
		case 1:
			service = new ListerPizzasService();
			break;
		case 2:
			service = new AjouterPizzaService();
			break;
		case 3:
			service = new ModifierPizzaService();
			break;
		case 4:
			service = new SupprimerPizzaService();
			break;
		}
		return service;
	}
}
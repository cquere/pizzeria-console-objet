package fr.pizzeria.utils;

import java.lang.reflect.Field;

import fr.pizzeria.exception.StockageException;

public class Validator {

	public static void checkRule(Object obj) throws StockageException {

		Class<? extends Object> c = obj.getClass();

		for (Field field : c.getDeclaredFields()) {

			if (field.isAnnotationPresent(Rule.class)) {
				Object o = null;
				field.setAccessible(true);
				try {
					o = field.get(obj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				if (o != null) {
					String s = o.toString();
					Rule t = field.getAnnotation(Rule.class);
					if (t.staticLength() && t.length() != s.length())
						throw new StockageException("Le code Pizza doit contenir 3 caractères");
					if (t.minValueActivated() && t.minValue() < Integer.getInteger(s))
						throw new StockageException("Le prix de la Pizza doit être strictement positif");
				}
			}
		}
	
	}

}

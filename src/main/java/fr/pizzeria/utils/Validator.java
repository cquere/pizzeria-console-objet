package fr.pizzeria.utils;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.pizzeria.exception.ArgumentNullException;
import fr.pizzeria.exception.StockageException;

public class Validator {

	private static final Logger LOGERROR = LoggerFactory.getLogger("ERROR");

	
	public static void checkRule(Object obj) throws StockageException, ArgumentNullException {
		if (obj == null)
			throw new ArgumentNullException("Object is NULL");
		Class<? extends Object> c = obj.getClass();

		for (Field field : c.getDeclaredFields()) {

			if (field.isAnnotationPresent(Rule.class)) {
				Object o = null;
				field.setAccessible(true);
				try {
					o = field.get(obj);
				} catch (IllegalArgumentException e) {
					LOGERROR.error(e.getMessage());
				} catch (IllegalAccessException e) {
					LOGERROR.error(e.getMessage());
				}
				if (o != null) {
					String s = o.toString();
					Rule t = field.getAnnotation(Rule.class);
					if (t == null || s == null)
						continue;
					if (t.staticLength() && t.length() != s.length())
						throw new StockageException("Le code Pizza doit contenir 3 caractères");
					if (t.minValueActivated() && t.minValue() > Double.parseDouble(s))
						throw new StockageException("Le prix de la Pizza doit être strictement positif");
				}
			}
		}
	
	}

}

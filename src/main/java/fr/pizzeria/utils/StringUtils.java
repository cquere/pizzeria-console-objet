package fr.pizzeria.utils;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {

	private static final Logger LOGERROR = LoggerFactory.getLogger("ERROR");

	
	public static String builtString(Object obj) {

		Class<? extends Object> c = obj.getClass();
		StringBuilder result = new StringBuilder();

		for (Field field : c.getDeclaredFields()) {

			if (field.isAnnotationPresent(ToString.class)) {
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
					ToString t = field.getAnnotation(ToString.class);
					if (t.uppercase())
						s = s.toUpperCase();

					result.append(t.separateurAvant()).append(s).append(t.separateurApres());
				}
			}
		}
		return result.toString();
	}
}

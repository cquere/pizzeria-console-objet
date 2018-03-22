package fr.pizzeria.utils;

import java.lang.reflect.Field;

public class StringUtils {

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
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
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

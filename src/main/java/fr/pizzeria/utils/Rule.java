package fr.pizzeria.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Rule {

	public boolean minValueActivated() default false;
	public int minValue() default Integer.MIN_VALUE;
	public boolean staticLength() default false;
	public int length() default Integer.MAX_VALUE;
}

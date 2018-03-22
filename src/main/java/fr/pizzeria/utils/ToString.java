package fr.pizzeria.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ToString {

	public boolean uppercase() default false;

	public String separateurAvant() default "";
	public String separateurApres() default "";
}

package fr.pizzeria.services;

import static org.junit.contrib.java.lang.system.TextFromStandardInputStream.emptyStandardInputStream;

import java.util.Scanner;

import javax.annotation.Generated;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;
import org.mockito.Mockito;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.UpdatePizzaException;

public class ModifierPizzaServiceTest {

		
	
	/**
	 * Création d'une "Rule" qui va permettre de substituer le System.in utilisé
	 * par le Scanner par un mock: systemInMock
	 */
	@Rule
	public TextFromStandardInputStream systemInMock = emptyStandardInputStream();
	
	@Test(expected = UpdatePizzaException.class)
	public void testExecuteUC() throws Exception {
		ModifierPizzaService testSubject = new ModifierPizzaService();

		
		IPizzaDao mockedDao = Mockito.mock(IPizzaDao.class);
		
		// J'alimente le mock
		systemInMock.provideLines("PEP", "RER", "test", "2", "15");

		Mockito.when(mockedDao.findPizzaByCode(Mockito.anyString())).thenReturn(null);
		Scanner scan = new Scanner(System.in);

		// default test
		testSubject.executeUC(mockedDao, scan);
	}
}
/**
 * 
 */
package application;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import util.exception.UserInputException;

/**
 * @author domin
 *
 */
public class VerwaltungTest {

	Verwaltung v;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		v = new Verwaltung();
		// v.register("Olaf", "Mieller", "m", "71782", "Gera", "Hauptallee", "13",
		// LocalDate.of(2000, 10, 2),
		// "olaf.muelle@hsdf.de", "1234");
		v.login("olaf.muelle@hsdf.de", "1234");
	}

	// /**
	// * Test method for {@link application.Verwaltung#Verwaltung()}.
	// */
	// @Test
	// public void testVerwaltung() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for {@link application.Verwaltung#getCurrentNutzer()}.
	// */
	// @Test
	// public void testGetCurrentNutzer() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for {@link application.Verwaltung#getCurrentProfil()}.
	// */
	// @Test
	// public void testGetCurrentProfil() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for
	// * {@link application.Verwaltung#register(java.lang.String, java.lang.String,
	// java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	// java.lang.String, java.time.LocalDate, java.lang.String, java.lang.String)}.
	// */
	// @Test
	// public void testRegister() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for
	// * {@link application.Verwaltung#createUnternehmen(java.lang.String,
	// java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	// java.lang.String, java.time.LocalDate, int, java.lang.String,
	// java.lang.String, java.lang.String, java.lang.String, java.lang.String,
	// java.lang.String)}.
	// */
	// @Test
	// public void testCreateUnternehmen() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for
	// * {@link application.Verwaltung#createFreelancer(java.lang.String,
	// java.lang.String, java.lang.String[], java.lang.String, java.lang.String,
	// java.util.List)}.
	// */
	// @Test
	// public void testCreateFreelancer() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for
	// * {@link application.Verwaltung#createJobangebot(java.lang.String,
	// java.util.List, java.lang.String, java.time.LocalDate, int, int, int)}.
	// */
	// @Test
	// public void testCreateJobangebot() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link application.Verwaltung#changeNutzer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.time.LocalDate)}.
	 */
	@Test
	public void testChangeNutzer() {
		try {
			v.changeNutzer("Boris", "Schmidt", "m", "77772", "Stuttgart", "Strasse", "20", LocalDate.of(2000, 6, 3));
		} catch (UserInputException e) {
			e.printStackTrace();
		}
		// fail("Not yet implemented");
	}

	// /**
	// * Test method for
	// * {@link application.Verwaltung#login(java.lang.String, java.lang.String)}.
	// */
	// @Test
	// public void testLogin() {
	// fail("Not yet implemented");
	// }

}

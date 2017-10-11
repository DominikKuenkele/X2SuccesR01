/**
 * 
 */
package application;

import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import model.Jobangebot;
import util.exception.DBException;
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
	@Ignore
	@Before
	public void setUp() throws Exception {
		v = Verwaltung.getInstance();
		// v.register("Olaf", "Mieller", "m", "71782", "Gera", "Hauptallee", "13",
		// LocalDate.of(2000, 10, 2),
		// "olaf.muelle@hsdf.de", "1234");
		v.login("olaf.muelle@hsdf.de", "1234");
	}

	/**
	 * Test method for {@link application.Verwaltung#Verwaltung()}.
	 */
	@Ignore
	@Test
	public void testVerwaltung() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link application.Verwaltung#getCurrentNutzer()}.
	 */
	@Ignore
	@Test
	public void testGetCurrentNutzer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link application.Verwaltung#getCurrentProfil()}.
	 */
	@Ignore
	@Test
	public void testGetCurrentProfil() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link application.Verwaltung#register(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.time.LocalDate, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link application.Verwaltung#createUnternehmen(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.time.LocalDate, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testCreateUnternehmen() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link application.Verwaltung#createFreelancer(java.lang.String, java.lang.String, java.lang.String[], java.lang.String, java.lang.String, java.util.List)}.
	 */
	@Ignore
	@Test
	public void testCreateFreelancer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link application.Verwaltung#createJobangebot(java.lang.String, java.util.List, java.lang.String, java.time.LocalDate, int, int, int)}.
	 */
	@Ignore
	@Test
	public void testCreateJobangebot() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link application.Verwaltung#sucheJobangebote(String name, String abschluss, String branche, int minMitarbeiter, int maxMitarbeiter, int minGehalt)}.
	 */
	@Test
	public void testSucheJobangebote() {
		Set<Entry<Jobangebot, Integer>> set = Verwaltung.getInstance().sucheJobangebote("name", "abscluss", "branche",
				1, 2, 3);
		System.out.println(set);
		// for (int i = 0; i < map.size(); i++) {
		// System.out.println(map.entrySet());
		// }
	}

	/**
	 * Test method for
	 * {@link application.Verwaltung#changeNutzer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.time.LocalDate)}.
	 */
	@Ignore
	@Test
	public void testChangeNutzer() {
		try {
			v.changeNutzer("Boris", "Schmidt", "m", "77772", "Stuttgart", "Strasse", "20", LocalDate.of(2000, 6, 3));
		} catch (UserInputException | DBException e) {
			e.printStackTrace();
		}
		// fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link application.Verwaltung#login(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testLogin() {
		fail("Not yet implemented");
	}

}

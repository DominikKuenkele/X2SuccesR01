package persistence;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Ignore;
import org.junit.Test;

import model.Adresse;
import model.Nutzer;
import model.Status;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class NutzerDAOTest {

	/**
	 * Test method for {@link persistence.NutzerDAO#addNutzer(model.Nutzer)}.
	 */
	@Ignore
	@Test
	public void testAddNutzer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link persistence.NutzerDAO#getNutzer(String)}.
	 */
	@Ignore
	@Test
	public void testGetNutzerString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link persistence.NutzerDAO#getNutzer(int)}.
	 */
	@Ignore
	@Test
	public void testGetNutzerInt() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link persistence.NutzerDAO#getAllNutzer()}.
	 */
	@Ignore
	@Test
	public void testGetAllNutzer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link persistence.NutzerDAO#changeNutzer(model.Nutzer)}.
	 */
	@Ignore
	@Test
	public void testChangeNutzer() {
		Nutzer test;
		try {
			test = new Nutzer("Manuel", "Schmidt", "f", LocalDate.of(2000, 6, 3), "manuel.kuenkele@live.de", "pass",
					new Adresse("77772", "Stuttgart", "Strasse", "20"), Status.U);
			test.setId(6);
			new NutzerDAO().changeNutzer(test);
		} catch (ValidateConstrArgsException | SQLException e) {
			e.printStackTrace();
		}

		// fail("Not yet implemented");
	}

	/**
	 * Test method for {@link persistence.NutzerDAO#deleteNutzer(String)}.
	 */
	@Ignore
	@Test
	public void testDeleteNutzer() {
		fail("Not yet implemented");
	}

}

package persistence;

import static org.junit.Assert.fail;

import java.time.LocalDate;

import org.junit.Test;

import model.Adresse;
import model.Nutzer;
import model.Status;
import util.exception.ValidateConstrArgsException;

public class NutzerDAOTest {

	@Test
	public void testAddNutzer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNutzerString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNutzerInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAllNutzer() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeNutzer() {
		Nutzer test;
		try {
			test = new Nutzer("Manuel", "Schmidt", "f", LocalDate.of(2000, 6, 3), "manuel.kuenkele@live.de", "pass",
					new Adresse("77772", "Stuttgart", "Strasse", "20"), Status.U);
			test.setId(6);
			new NutzerDAO().changeNutzer(test);
		} catch (ValidateConstrArgsException e) {
			e.printStackTrace();
		}

		// fail("Not yet implemented");
	}

	@Test
	public void testDeleteNutzer() {
		fail("Not yet implemented");
	}

}

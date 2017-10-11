/**
 * 
 */
package persistence;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import model.Jobangebot;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class JobangebotDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link persistence.JobangebotDAO#addJobangebot(model.Jobangebot)}.
	 */
	@Ignore
	@Test
	public void testAddJobangebot() {
		List<String> sprachen = new LinkedList<>();
		sprachen.add("Englisch");
		Jobangebot[] j = new Jobangebot[5];
		try {
			for (int i = 0; i < j.length; i++) {
				j[i] = new Jobangebot("Ausbildung", "Banken", sprachen, "beschreibung", LocalDate.of(2019, 10, 5), 100,
						20, new UnternehmensprofilDAO().getUnternehmensprofil(i + 7));

				new JobangebotDAO().addJobangebot(j[i]);
				// j.setId(jid);
				// System.out.println(j);
			}
		} catch (ValidateConstrArgsException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for {@link persistence.JobangebotDAO#getJobangebot(int)}.
	 */
	@Ignore
	@Test
	public void testGetJobangebot() {
		try {
			System.out.println(new JobangebotDAO().getJobangebot(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link persistence.JobangebotDAO#getAllJobangebote()}.
	 */
	@Ignore
	@Test
	public void testGetAllJobangebote() {
		List<Jobangebot> j;
		try {
			j = new JobangebotDAO().getAllJobangebote();
			for (Jobangebot s : j) {
				System.out.println(s);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link persistence.JobangebotDAO#deleteJobangebot(int)}.
	 */
	@Ignore
	@Test
	public void testDeleteJobangebot() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link persistence.JobangebotDAO#changeJobangebot(model.Jobangebot)}.
	 */
	@Ignore
	@Test
	public void testChangeJobangebot() {
		try {
			Jobangebot j = new JobangebotDAO().getJobangebot(3);
			List<String> sprachen = new LinkedList<>();
			sprachen.add("Deutsch");
			Jobangebot j2 = new Jobangebot(j.getAbschluss(), j.getBranche(), sprachen, j.getBeschreibung(),
					j.getFrist(), 2000, j.getWochenstunden(), j.getUnternehmensproflil());
			j2.setId(j.getJID());
			new JobangebotDAO().changeJobangebot(j2);
		} catch (ValidateConstrArgsException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link persistence.JobangebotDAO#searchForName(java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testSearchForName() {
		List<Jobangebot> list;
		try {
			list = new JobangebotDAO().searchForNameTest("*le");
			for (Jobangebot j : list) {
				System.out.println(j);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link persistence.JobangebotDAO#searchForAbschluss(java.lang.String, java.lang.String)}.
	 */
	@Ignore
	@Test
	public void testSearchForAbschluss() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link persistence.JobangebotDAO#searchForGehalt(int)}.
	 */
	@Test
	public void testSearchForGehalt() {
		List<Jobangebot> list;
		try {
			list = new JobangebotDAO().searchForGehaltTest(100);
			for (Jobangebot j : list) {
				System.out.println(j);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link persistence.JobangebotDAO#searchForMitarbeiter(int, int)}.
	 */
	@Ignore
	@Test
	public void testSearchForMitarbeiter() {
		fail("Not yet implemented");
	}

}

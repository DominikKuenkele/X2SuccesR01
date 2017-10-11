/**
 * 
 */
package persistence;

import java.util.List;

import org.junit.Test;

import model.Jobangebot;

/**
 * @author domin
 *
 */
public class JobangebotDAOTest {

	// /**
	// * @throws java.lang.Exception
	// */
	// @Before
	// public void setUp() throws Exception {
	// }
	//
	// /**
	// * Test method for
	// * {@link persistence.JobangebotDAO#addJobangebot(model.Jobangebot)}.
	// */
	// @Test
	// public void testAddJobangebot() {
	// List<String> sprachen = new LinkedList<>();
	// sprachen.add("Englisch");
	// Jobangebot[] j = new Jobangebot[5];
	// try {
	// for (int i = 0; i < j.length; i++) {
	// j[i] = new Jobangebot("Ausbildung", "Banken", sprachen, "beschreibung",
	// LocalDate.of(2019, 10, 5), 100,
	// 200, 20, new UnternehmensprofilDAO().getUnternehmensprofil(i + 7));
	//
	// new JobangebotDAO().addJobangebot(j[i]);
	// // j.setId(jid);
	// // System.out.println(j);
	// }
	// } catch (ValidateConstrArgsException e) {
	// e.printStackTrace();
	// }
	//
	// }

	// /**
	// * Test method for {@link persistence.JobangebotDAO#getJobangebot(int)}.
	// */
	// @Test
	// public void testGetJobangebot() {
	// System.out.println(new JobangebotDAO().getJobangebot(2));
	// }

	// /**
	// * Test method for {@link persistence.JobangebotDAO#getAllJobangebote()}.
	// */
	// @Test
	// public void testGetAllJobangebote() {
	// List<Jobangebot> j = new JobangebotDAO().getAllJobangebote();
	// for (Jobangebot s : j) {
	// System.out.println(s);
	// }
	// }
	//
	// /**
	// * Test method for {@link persistence.JobangebotDAO#deleteJobangebot(int)}.
	// */
	// @Test
	// public void testDeleteJobangebot() {
	// fail("Not yet implemented");
	// }

	/**
	 * Test method for
	 * {@link persistence.JobangebotDAO#searchForName(java.lang.String)}.
	 */
	@Test
	public void testSearchForName() {

		List<Jobangebot> list = new JobangebotDAO().searchForNameTest("*le");
		for (Jobangebot j : list) {
			System.out.println(j);
		}
	}
	//
	// /**
	// * Test method for {@link
	// persistence.JobangebotDAO#searchForAbschluss(java.lang.String,
	// java.lang.String)}.
	// */
	// @Test
	// public void testSearchForAbschluss() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for {@link persistence.JobangebotDAO#searchForGehalt(int)}.
	// */
	// @Test
	// public void testSearchForGehalt() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for {@link persistence.JobangebotDAO#searchForMitarbeiter(int,
	// int)}.
	// */
	// @Test
	// public void testSearchForMitarbeiter() {
	// fail("Not yet implemented");
	// }

}

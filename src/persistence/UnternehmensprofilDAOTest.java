/**
 * 
 */
package persistence;

import org.junit.Test;

/**
 * @author domin
 *
 */
public class UnternehmensprofilDAOTest {

	// /**
	// * @throws java.lang.Exception
	// */
	// @Before
	// public void setUp() throws Exception {
	// Verwaltung v = Verwaltung.getInstance();
	// v.createUnternehmen("BurgerKing", "GmbH", "8746", "kjhsk", "gslkfjg", "23",
	// LocalDate.of(1244, 12, 4), 50, "df",
	// "df", "www.fg.vv", "sdg", "fs");
	// v.createUnternehmen("Apple", "GmbH", "8746", "kjhsk", "gslkfjg", "23",
	// LocalDate.of(1244, 12, 4), 50, "df",
	// "df", "www.fg.vv", "sdg", "fs");
	// v.createUnternehmen("Daimler", "GmbH", "8746", "kjhsk", "gslkfjg", "23",
	// LocalDate.of(1244, 12, 4), 50, "df",
	// "df", "www.fg.vv", "sdg", "fs");
	// v.createUnternehmen("Opel", "GmbH", "8746", "kjhsk", "gslkfjg", "23",
	// LocalDate.of(1244, 12, 4), 50, "df", "df",
	// "www.fg.vv", "sdg", "fs");
	// }

	// /**
	// * Test method for
	// * {@link
	// persistence.UnternehmensprofilDAO#addUnternehmensprofil(model.Unternehmensprofil)}.
	// */
	// @Test
	// public void testAddUnternehmensprofil() {
	// String[] name = { "BurgerKing", "Daimler", "Apple", "Microsoft", "Opel" };
	// try {
	// for (int i = 0; i < 5; i++) {
	// Unternehmensprofil u;
	// u = new Unternehmensprofil(name[i], "GmbH", new Adresse("8746", "kjhsk",
	// "gslkfjg", "23"),
	// LocalDate.of(1244, 12, 4), 50, "df", "df", "www.fg.vv", "sdg", "fs",
	// new NutzerDAO().getNutzer(22));
	//
	// new UnternehmensprofilDAO().addUnternehmensprofil(u);
	// }
	// } catch (ValidateConstrArgsException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	/**
	 * Test method for
	 * {@link persistence.UnternehmensprofilDAO#getUnternehmensprofil(int)}.
	 */
	@Test
	public void testGetUnternehmensprofil() {
		System.out.println(new UnternehmensprofilDAO().getUnternehmensprofil(7));
	}
	//
	// /**
	// * Test method for
	// * {@link persistence.UnternehmensprofilDAO#getAllUnternehmen()}.
	// */
	// @Test
	// public void testGetAllUnternehmen() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for {@link
	// persistence.UnternehmensprofilDAO#deleteUnternehmensprofil(int)}.
	// */
	// @Test
	// public void testDeleteUnternehmensprofil() {
	// fail("Not yet implemented");
	// }

	// /**
	// * Test method for
	// * {@link
	// persistence.UnternehmensprofilDAO#changeUnternehmen(model.Unternehmensprofil)}.
	// */
	// @Test
	// public void testChangeUnternehmen() {
	// Verwaltung v = Verwaltung.getInstance();
	// v.login("olaf.muelle@hsdf.de", "1234");
	// Nutzer nutzer = v.getCurrentNutzer();
	// Unternehmensprofil unternehmen;
	// try {
	// unternehmen = new Unternehmensprofil("Test", "GmbH", new Adresse("8746",
	// "kjhsk", "gslkfjg", "23"),
	// LocalDate.of(1244, 12, 4), 50, "sd", "df", "www.fg.vv", "sdg", "fs", nutzer);
	// unternehmen.setId(1);
	// new UnternehmensprofilDAO().changeUnternehmen(unternehmen);
	// } catch (ValidateConstrArgsException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

}

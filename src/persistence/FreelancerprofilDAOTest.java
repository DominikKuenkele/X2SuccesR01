/**
 * 
 */
package persistence;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import model.Freelancerprofil;

/**
 * @author domin
 *
 */
public class FreelancerprofilDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	// /**
	// * Test method for
	// * {@link
	// persistence.FreelancerprofilDAO#addFreelancerprofil(model.Freelancerprofil)}.
	// */
	// @Test
	// public void testAddFreelancerprofil() {
	// String[] skills = { "d", "d", "g" };
	// List<String> sprachen = new LinkedList<>();
	// sprachen.add("Englisch");
	// Freelancerprofil f = null;
	// try {
	// f = new Freelancerprofil("Ausbildung", "Banken", "beschreibung", skills,
	// "lebenslauf", "benfits", sprachen,
	// new NutzerDAO().getNutzer(22));
	// } catch (ValidateConstrArgsException e) {
	// e.printStackTrace();
	// }
	// int fid = new FreelancerprofilDAO().addFreelancerprofil(f);
	// f.setId(fid);
	// System.out.println(f);
	// }

	/**
	 * Test method for
	 * {@link persistence.FreelancerprofilDAO#getFreelancerprofil(int)}.
	 */
	@Test
	public void testGetFreelancerprofil() {
		Freelancerprofil f;
		try {
			f = new FreelancerprofilDAO().getFreelancerprofil(5);
			System.out.println(f);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// /**
	// * Test method for {@link persistence.FreelancerprofilDAO#getAllFreelancer()}.
	// */
	// @Test
	// public void testGetAllFreelancer() {
	// fail("Not yet implemented");
	// }
	//
	// /**
	// * Test method for
	// * {@link persistence.FreelancerprofilDAO#deleteFreelancerprofil(int)}.
	// */
	// @Test
	// public void testDeleteFreelancerprofil() {
	// fail("Not yet implemented");
	// }

}

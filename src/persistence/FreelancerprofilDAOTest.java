/**
 * 
 */
package persistence;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import model.Freelancerprofil;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class FreelancerprofilDAOTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Ignore
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link persistence.FreelancerprofilDAO#addFreelancerprofil(model.Freelancerprofil)}.
	 */
	@Ignore
	@Test
	public void testAddFreelancerprofil() {
		try {
			String[] skills = { "d", "d", "g" };
			List<String> sprachen = new LinkedList<>();
			sprachen.add("Englisch");
			Freelancerprofil f = new Freelancerprofil("Ausbildung", "Banken", "beschreibung", skills, "lebenslauf",
					sprachen, new NutzerDAO().getNutzer(22));
			int fid = new FreelancerprofilDAO().addFreelancerprofil(f);
			f.setId(fid);
			System.out.println(f);
		} catch (ValidateConstrArgsException | SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Test method for
	 * {@link persistence.FreelancerprofilDAO#getFreelancerprofil(int)}.
	 */
	@Ignore
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

	/**
	 * Test method for {@link persistence.FreelancerprofilDAO#getAllFreelancer()}.
	 */
	@Ignore
	@Test
	public void testGetAllFreelancer() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link persistence.FreelancerprofilDAO#deleteFreelancerprofil(int)}.
	 */
	@Ignore
	@Test
	public void testDeleteFreelancerprofil() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for
	 * {@link persistence.FreelancerprofilDAO#changeFreelancerprofil(model.Freelancerprofil)}.
	 */
	@Ignore
	@Test
	public void testChangeFreelancerprofil() {
		List<String> sprachen = new LinkedList<>();
		sprachen.add("Deutsch");
		try {
			for (int i = 4; i < 18; i++) {
				Freelancerprofil f = new FreelancerprofilDAO().getFreelancerprofil(i);
				String branche = new BrancheDAO().getBranche(i % 5 + 1);
				String abschluss = new AbschlussDAO().getAbschluss((i % 4) + 4);
				Freelancerprofil f2 = new Freelancerprofil(abschluss, branche, f.getBeschreibung(), f.getSkills(),
						f.getLebenslauf(), f.getSprachen(), f.getNutzer());
				f2.setId(f.getFID());
				new FreelancerprofilDAO().changeFreelancerprofil(f2);
			}
		} catch (ValidateConstrArgsException | SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Test method for
	 * {@link persistence.FreelancerprofilDAO#searchForAbschluss(String, String)}.
	 */
	@Ignore
	@Test
	public void testSearchForAbschluss() {
		List<Freelancerprofil> list;
		try {
			list = new FreelancerprofilDAO().searchForAbschlussTest("Bachelor", "*Banken*");
			for (Freelancerprofil j : list) {
				System.out.println(j);
			}
			System.out.println(list.size() + " results");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link persistence.FreelancerprofilDAO#searchForGehalt(int)}.
	 */
	@Ignore
	@Test
	public void testSearchForGehalt() {
		List<Freelancerprofil> list;
		try {
			list = new FreelancerprofilDAO().searchForGehaltTest(100);
			for (Freelancerprofil j : list) {
				System.out.println(j);
			}
			System.out.println(list.size() + " results");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

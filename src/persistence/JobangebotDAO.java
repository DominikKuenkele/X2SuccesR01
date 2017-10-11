package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import model.Jobangebot;
import model.Unternehmensprofil;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class JobangebotDAO {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	private void open() throws SQLException, ClassNotFoundException {
		DBConnection dbconnection = new DBConnection();
		connect = dbconnection.getConnection();
	}

	/**
	 * 
	 */
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (preparedStatement != null) {
				preparedStatement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		}
	}

	/**
	 * @param jobangebot
	 * @return the generated ID of the new {@link model.Jobangebot}
	 */
	public int addJobangebot(Jobangebot jobangebot) {
		int unternehmensId = jobangebot.getUnternehmensproflil().getId();
		int jid = -1;
		try {
			open();
			preparedStatement = connect
					.prepareStatement("INSERT INTO Jobangebot values (default, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, unternehmensId);
			int gid = new AbschlussDAO().getAbschluss(jobangebot.getAbschluss());
			preparedStatement.setInt(2, gid);
			int bid = new BrancheDAO().getBranche(jobangebot.getBranche());
			preparedStatement.setInt(3, bid);
			preparedStatement.setString(4, jobangebot.getBeschreibung());
			preparedStatement.setObject(5, jobangebot.getFrist());
			preparedStatement.setInt(6, jobangebot.getMinGehalt());
			preparedStatement.setInt(7, jobangebot.getMaxGehalt());
			preparedStatement.setInt(8, jobangebot.getWochenstunden());
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("SELECT LAST_INSERT_ID()");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				jid = resultSet.getInt("last_insert_id()");
			}

			List<String> sprachen = jobangebot.getSprachen();

			for (int i = 0; i < sprachen.size(); i++) {
				int sid = new SpracheDAO().getId(sprachen.get(i));

				preparedStatement = connect.prepareStatement("INSERT INTO SprachenzuordnungJA values (?, ?)");
				preparedStatement.setInt(1, jid);
				preparedStatement.setInt(2, sid);

				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
		return jid;
	}

	/**
	 * @param jid
	 * @return a {@link model.Jobangebot} with given ID
	 */
	public Jobangebot getJobangebot(int jid) {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT JID, UID, graduation.graduation, branche.branche, "
					+ "description, deadline, minSalary, maxSalary, weeklyHours " + "FROM jobangebot "
					+ "INNER JOIN graduation ON jobangebot.GID=graduation.GID "
					+ "INNER JOIN branche ON jobangebot.BID = branche.BID " + "WHERE JID = ?");
			preparedStatement.setInt(1, jid);
			resultSet = preparedStatement.executeQuery();
			return getJobangebotFromResultSet(resultSet).get(0);
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			return null;
		} finally {
			close();
		}
	}

	/**
	 * @return a List of all {@link model.Jobangebot Jobangebote} in database
	 */
	public List<Jobangebot> getAllJobangebote() {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT JID, UID, graduation.graduation, branche.branche, "
					+ "description, deadline, minSalary, maxSalary, weeklyHours " + "FROM jobangebot "
					+ "INNER JOIN graduation ON jobangebot.GID=graduation.GID "
					+ "INNER JOIN branche ON jobangebot.BID = branche.BID");
			resultSet = preparedStatement.executeQuery();
			return getJobangebotFromResultSet(resultSet);
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
			return null;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			return null;
		} finally {
			close();
		}
	}

	/**
	 * @param jid
	 */
	public void deleteJobangebot(int jid) {
		try {
			open();
			preparedStatement = connect.prepareStatement("DELETE FROM Jobangebot WHERE JID = ?");
			preparedStatement.setInt(1, jid);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
	}

	private List<Jobangebot> getJobangebotFromResultSet(ResultSet resultSet) throws SQLException {
		List<Jobangebot> result = new LinkedList<>();
		while (resultSet.next()) {
			int jobangebotsId = resultSet.getInt("jobangebot.JID");
			int unternehmensId = resultSet.getInt("jobangebot.uid");
			Unternehmensprofil unternehmen = new UnternehmensprofilDAO().getUnternehmensprofil(unternehmensId);
			String graduation = resultSet.getString("graduation.graduation");
			String branche = resultSet.getString("branche.branche");
			String description = resultSet.getString("jobangebot.description");
			Date deadlineSQL = resultSet.getDate("jobangebot.deadline");
			LocalDate deadline = deadlineSQL.toLocalDate();
			int minSalary = resultSet.getInt("jobangebot.minSalary");
			int maxSalary = resultSet.getInt("jobangebot.maxSalary");
			int weeklyHours = resultSet.getInt("jobangebot.weeklyHours");
			List<String> sprachen = getLanguageInJobangebot(jobangebotsId);
			try {
				Jobangebot tempJobangebot = new Jobangebot(graduation, branche, sprachen, description, deadline,
						minSalary, maxSalary, weeklyHours, unternehmen);
				tempJobangebot.setId(jobangebotsId);
				result.add(tempJobangebot);

			} catch (ValidateConstrArgsException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private List<String> getLanguageInJobangebot(int jid) throws SQLException {
		List<String> result = new LinkedList<>();
		ResultSet resultSetSprache = null;
		try {
			preparedStatement = connect.prepareStatement("SELECT SID FROM SprachenzuordnungJA WHERE JID = ?");
			preparedStatement.setInt(1, jid);
			resultSetSprache = preparedStatement.executeQuery();

			while (resultSetSprache.next()) {
				int sid = resultSetSprache.getInt("SID");

				String sprache = new SpracheDAO().getSprache(sid);
				result.add(sprache);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			resultSetSprache.close();
		}
		return result;
	}

	public List<Jobangebot> searchForNameTest(String aName) {
		List<Jobangebot> result = new LinkedList<>();
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT jobangebot.JID " + "FROM jobangebot "
					+ "INNER JOIN unternehmensprofil ON jobangebot.UID=unternehmensprofil.UID "
					+ "WHERE unternehmensprofil.name LIKE ?");
			preparedStatement.setString(1, "%" + aName + "%");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				int jid = resultSet.getInt("jobangebot.JID");
				result.add(new JobangebotDAO().getJobangebot(jid));
			}
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
		return result;
	}

	public List<Jobangebot> searchForName(String aName) {

		List<Jobangebot> list = new LinkedList<>();
		try {
			Jobangebot[] jA = new Jobangebot[4];
			for (int i = 0; i < 4; i++) {
				jA[i] = new Jobangebot("sdf", "sdf", new LinkedList<String>(), "sdf", LocalDate.of(1999, 12, 1), 1, 2,
						3, null);
				jA[i].setId(i * 2);
			}
			list = Arrays.asList(jA);
		} catch (ValidateConstrArgsException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Jobangebot> searchForAbschluss(String abschluss, String branche) {
		List<Jobangebot> list = new LinkedList<>();
		try {
			Jobangebot[] jA = new Jobangebot[4];
			for (int i = 0; i < 4; i++) {
				jA[i] = new Jobangebot("sdf", "sdf", new LinkedList<String>(), "sdf", LocalDate.of(1999, 12, 1), 1, 2,
						3, null);
				jA[i].setId(i * 2);
			}
			list = Arrays.asList(jA);
		} catch (ValidateConstrArgsException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Jobangebot> searchForGehalt(int gehalt) {
		List<Jobangebot> list = new LinkedList<>();
		try {
			Jobangebot[] jA = new Jobangebot[4];
			for (int i = 0; i < 4; i++) {
				jA[i] = new Jobangebot("dg", "sdf", new LinkedList<String>(), "sdf", LocalDate.of(1999, 12, 1), 1, 2, 3,
						null);
				jA[i].setId(i * 3);
			}
			list = Arrays.asList(jA);
		} catch (ValidateConstrArgsException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Jobangebot> searchForMitarbeiter(int min, int max) {
		List<Jobangebot> list = new LinkedList<>();
		try {
			Jobangebot[] jA = new Jobangebot[4];
			for (int i = 0; i < 4; i++) {
				jA[i] = new Jobangebot(Integer.toString(i), "sdf", new LinkedList<String>(), "sdf",
						LocalDate.of(1999, 12, 1), 1, 2, 3, null);
				jA[i].setId(4 - i);
			}
			list = Arrays.asList(jA);
		} catch (ValidateConstrArgsException e) {
			e.printStackTrace();
		}
		return list;
	}
}

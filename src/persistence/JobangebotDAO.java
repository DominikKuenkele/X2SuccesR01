package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
					.prepareStatement("INSERT INTO Jobangebot values (default, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, unternehmensId);
			preparedStatement.setString(2, jobangebot.getAbschluss());
			preparedStatement.setString(3, jobangebot.getBeschreibung());
			preparedStatement.setObject(4, jobangebot.getFrist());
			preparedStatement.setInt(5, jobangebot.getMinGehalt());
			preparedStatement.setInt(6, jobangebot.getMaxGehalt());
			preparedStatement.setInt(7, jobangebot.getWochenstunden());
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

			for (int i = 0; i < sprachen.size(); i++) {
				int sid = 0;
				preparedStatement = connect.prepareStatement("SELECT SID FROM Sprachen WHERE sprache = ?");
				preparedStatement.setString(1, sprachen.get(i));
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					sid = resultSet.getInt("SID");
				}
				preparedStatement = connect.prepareStatement("INSERT INTO SprachenzuordnungJA values (?, ?)");
				preparedStatement.setInt(1, jid);
				preparedStatement.setInt(2, sid);

				preparedStatement.executeUpdate();
			}
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
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
			preparedStatement = connect.prepareStatement("SELECT * FROM Jobangebot WHERE JID = ?");
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
			preparedStatement = connect.prepareStatement("SELECT * FROM Jobangebot");
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
			int jobangebotsId = resultSet.getInt("JID");
			int unternehmensId = resultSet.getInt("uid");
			Unternehmensprofil unternehmen = new UnternehmensprofilDAO().getUnternehmensprofil(unternehmensId);
			String graduation = resultSet.getString("graduation");
			String description = resultSet.getString("description");
			Date deadlineSQL = resultSet.getDate("deadline");
			LocalDate deadline = deadlineSQL.toLocalDate();
			int minSalary = resultSet.getInt("minSalary");
			int maxSalary = resultSet.getInt("maxSalary");
			int weeklyHours = resultSet.getInt("weeklyHours");
			List<String> sprachen = getLanguageInJobangebot(jobangebotsId);
			try {
				Jobangebot tempJobangebot = new Jobangebot(graduation, sprachen, description, deadline, minSalary,
						maxSalary, weeklyHours, unternehmen);
				tempJobangebot.setId(jobangebotsId);
				result.add(tempJobangebot);

			} catch (ValidateConstrArgsException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private List<String> getLanguageInJobangebot(int jid) {
		List<String> result = new LinkedList<>();
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT SID FROM SprachenzuordnungJA WHERE JID = ?");
			preparedStatement.setInt(1, jid);

			while (resultSet.next()) {
				int sid = resultSet.getInt("SID");

				String sprache = new SpracheDAO().getSprache(sid);
				result.add(sprache);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}

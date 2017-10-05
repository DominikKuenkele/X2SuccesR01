package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Freelancerprofil;
import model.Nutzer;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class FreelancerprofilDAO {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void open() throws SQLException, ClassNotFoundException {
		DBConnection dbconnection = new DBConnection();
		connect = dbconnection.getConnection();
	}

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

	public int addFreelancerprofil(Freelancerprofil freelancer) {
		int nutzerId = freelancer.getNutzer().getId();
		int fid = -1;
		try {
			open();

			preparedStatement = connect
					.prepareStatement("INSERT INTO Freelancerprofil values (default, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, nutzerId);
			preparedStatement.setString(2, freelancer.getAbschluss());
			preparedStatement.setString(3, freelancer.getBeschreibung());
			Gson gson = new GsonBuilder().create();
			String skillsJSON = gson.toJson(freelancer.getSkills());
			preparedStatement.setString(4, skillsJSON);
			preparedStatement.setString(5, freelancer.getLebenslauf());
			preparedStatement.setString(6, freelancer.getBenefits());

			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("SELECT LAST_INSERT_ID()");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				fid = resultSet.getInt("last_insert_id()");
			}

			List<String> sprachen = freelancer.getSprachen();
			for (int i = 0; i < sprachen.size(); i++) {
				int sid = new SpracheDAO().getId(sprachen.get(i));

				preparedStatement = connect.prepareStatement("INSERT INTO SprachenzuordnungFP values (?, ?)");
				preparedStatement.setInt(1, fid);
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
		return fid;
	}

	public Freelancerprofil getFreelancerprofil(int fid) {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT * FROM Freelancerprofil WHERE FID = ?");
			preparedStatement.setInt(1, fid);

			resultSet = preparedStatement.executeQuery();
			return getFreelancerprofilFromResultSet(resultSet).get(0);
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

	public List<Freelancerprofil> getAllFreelancer() {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT * FROM Freelancerprofil");
			resultSet = preparedStatement.executeQuery();
			return getFreelancerprofilFromResultSet(resultSet);
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

	public void deleteFreelancerprofil(int fid) {
		try {
			open();
			preparedStatement = connect.prepareStatement("DELETE FROM Freelancer WHERE fid = ?");
			preparedStatement.setInt(1, fid);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
	}

	private List<Freelancerprofil> getFreelancerprofilFromResultSet(ResultSet resultSet) throws SQLException {
		List<Freelancerprofil> result = new LinkedList<Freelancerprofil>();
		while (resultSet.next()) {
			int freelancerId = resultSet.getInt("FID");
			int nutzerId = resultSet.getInt("NID");
			Nutzer nutzer = new NutzerDAO().getNutzer(nutzerId);
			String graduation = resultSet.getString("graduation");
			String description = resultSet.getString("description");
			String skillsJSON = resultSet.getString("skills");
			Gson gson = new GsonBuilder().create();
			String[] skills = gson.fromJson(skillsJSON, String[].class);
			String career = resultSet.getString("career");
			String benefits = resultSet.getString("benefits");
			List<String> sprachen = getLanguageInFreelancerprofil(freelancerId);

			try {
				Freelancerprofil tempFreelancer = new Freelancerprofil(graduation, description, skills, career,
						benefits, sprachen, nutzer);
				tempFreelancer.setId(freelancerId);
				result.add(tempFreelancer);
			} catch (ValidateConstrArgsException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	private List<String> getLanguageInFreelancerprofil(int fid) {
		List<String> result = new LinkedList<String>();
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT SID FROM SprachenzuordnungFP WHERE FID = ?");
			preparedStatement.setInt(1, fid);

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

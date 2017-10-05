package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @author domin
 *
 */
public class SpracheDAO {
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

	public int getId(String sprache) {
		int result = 0;
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT SID FROM Sprachen WHERE sprache = ?");
			preparedStatement.setString(1, sprache);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				int sid = resultSet.getInt("SID");
				result = sid;
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

	public String getSprache(int id) {
		String result = "";
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT * FROM Sprachen WHERE id = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();
			result = getSpracheFromResultSet(resultSet).get(0);
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
		return result;
	}

	public List<String> getAllSprachen() {
		List<String> result = new LinkedList<String>();
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT * FROM Sprachen");
			resultSet = preparedStatement.executeQuery();
			result = getSpracheFromResultSet(resultSet);
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
		return result;
	}

	private List<String> getSpracheFromResultSet(ResultSet resultSet) throws SQLException {
		List<String> result = new LinkedList<String>();
		while (resultSet.next()) {
			String sprache = resultSet.getString("Sprache");
			result.add(sprache);
		}
		return result;
	}
}

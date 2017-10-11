/**
 * 
 */
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
public class BrancheDAO {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void open() throws SQLException {
		DBConnection dbconnection = new DBConnection();
		connect = dbconnection.getConnection();
	}

	private void close() throws SQLException {
		if (resultSet != null) {
			resultSet.close();
		}

		if (preparedStatement != null) {
			preparedStatement.close();
		}

		if (connect != null) {
			connect.close();
		}

	}

	/**
	 * @param bid
	 * @return the name of the branche with given ID
	 * @throws SQLException
	 */
	public String getBranche(int bid) throws SQLException {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT branche FROM branche WHERE BID=?");
			preparedStatement.setInt(1, bid);

			resultSet = preparedStatement.executeQuery();
			return getBrancheFromResultSet(resultSet).get(0);
		} finally {
			close();
		}
	}

	/**
	 * @param branche
	 * @return the id of the branche with given name
	 * @throws SQLException
	 */
	public int getBranche(String branche) throws SQLException {
		int bid = -1;
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT BID FROM branche WHERE branche=?");
			preparedStatement.setString(1, branche);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bid = resultSet.getInt("BID");
			}
		} finally {
			close();
		}
		return bid;
	}

	private List<String> getBrancheFromResultSet(ResultSet resultSet) throws SQLException {
		List<String> result = new LinkedList<>();
		while (resultSet.next()) {
			String branche = resultSet.getString("branche");
			result.add(branche);
		}
		return result;
	}
}

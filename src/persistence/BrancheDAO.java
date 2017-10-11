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

	/**
	 * @param bid
	 * @return a {@link model.Freelancerprofil} with given ID
	 */
	public String getBranche(int bid) {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT branche FROM branche WHERE BID=?");
			preparedStatement.setInt(1, bid);

			resultSet = preparedStatement.executeQuery();
			return getBrancheFromResultSet(resultSet).get(0);
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
	 * @param branche
	 * @return a {@link model.Freelancerprofil} with given ID
	 */
	public int getBranche(String branche) {
		int bid = -1;
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT BID FROM branche WHERE branche=?");
			preparedStatement.setString(1, branche);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				bid = resultSet.getInt("BID");
			}
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
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

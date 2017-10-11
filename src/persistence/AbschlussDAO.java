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
public class AbschlussDAO {
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
	 * @param gid
	 * @return the name of the graduation with given ID
	 * @throws SQLException
	 */
	public String getAbschluss(int gid) throws SQLException {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT graduation FROM graduation WHERE GID=?");
			preparedStatement.setInt(1, gid);

			resultSet = preparedStatement.executeQuery();
			return getAbschlussFromResultSet(resultSet).get(0);
		} finally {
			close();
		}
	}

	/**
	 * @param abschluss
	 * @return the id of the graduation with given name
	 * @throws SQLException
	 */
	public int getAbschluss(String abschluss) throws SQLException {
		int gid = -1;
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT GID FROM graduation WHERE graduation=?");
			preparedStatement.setString(1, abschluss);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				gid = resultSet.getInt("GID");
			}
		} finally {
			close();
		}
		return gid;
	}

	private List<String> getAbschlussFromResultSet(ResultSet resultSet) throws SQLException {
		List<String> result = new LinkedList<>();
		while (resultSet.next()) {
			String abschluss = resultSet.getString("graduation");
			result.add(abschluss);
		}
		return result;
	}
}

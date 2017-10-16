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
 * Class is a DAO for the table 'graduation'
 * 
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

	/**
	 * @return a List of all graduations in the database
	 * @throws SQLException
	 */
	public List<String> getAllAbschluss() throws SQLException {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT graduation FROM graduation");
			resultSet = preparedStatement.executeQuery();
			return getAbschlussFromResultSet(resultSet);
		} finally {
			close();
		}
	}

	private List<String> getAbschlussFromResultSet(ResultSet resultSet) throws SQLException {
		List<String> result = new LinkedList<>();
		while (resultSet.next()) {
			String abschluss = resultSet.getString("graduation");
			result.add(abschluss);
		}
		return result;
	}

	/**
	 * @param aAbschluss
	 * @return the hierarchy of a given graduation
	 * @throws SQLException
	 */
	public int getHierarchy(String aAbschluss) throws SQLException {
		int hier = -1;
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT hierarchy FROM graduation WHERE graduation=?");
			preparedStatement.setString(1, aAbschluss);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				hier = resultSet.getInt("hierarchy");
			}
		} finally {
			close();
		}
		return hier;
	}
}

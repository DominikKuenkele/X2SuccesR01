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
	 * @param gid
	 * @return a {@link model.Freelancerprofil} with given ID
	 */
	public String getAbschluss(int gid) {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT graduation FROM graduation WHERE GID=?");
			preparedStatement.setInt(1, gid);

			resultSet = preparedStatement.executeQuery();
			return getAbschlussFromResultSet(resultSet).get(0);
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
	 * @param abschluss
	 * @return a {@link model.Freelancerprofil} with given ID
	 */
	public int getAbschluss(String abschluss) {
		int gid = -1;
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT GID FROM graduation WHERE graduation=?");
			preparedStatement.setString(1, abschluss);

			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				gid = resultSet.getInt("GID");
			}
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
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

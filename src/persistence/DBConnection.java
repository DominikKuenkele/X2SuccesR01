package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author domin
 *
 */
public class DBConnection {

	protected final Connection getConnection() throws SQLException {
		Connection connect = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		connect = DriverManager.getConnection("jdbc:mysql://localhost/x2succes?" + "user=root&password=password");
		// TODO Password in configfile
		return connect;
	}

}

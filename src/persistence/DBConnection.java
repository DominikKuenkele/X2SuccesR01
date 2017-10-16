package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class open the connection to a database
 * 
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

		String connectionURL = "jdbc:mysql://localhost/x2succes?autoReconnect=true&useSSL=false";
		connect = DriverManager.getConnection(connectionURL, "root", "password");

		// TODO Password in configfile
		return connect;
	}

}

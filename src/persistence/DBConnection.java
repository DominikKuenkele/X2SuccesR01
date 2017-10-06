package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author domin
 *
 */
public class DBConnection {

	protected final Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connect = null;
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/x2succes?" + "user=root&password=password");
		// TODO Password in configfile
		return connect;
	}

}

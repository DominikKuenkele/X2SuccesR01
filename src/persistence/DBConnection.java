package persistence;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

		Properties prop = new Properties();

		try (InputStream input = new FileInputStream("config.properties")) {
			prop.load(input);
			prop.getProperty("database");
			prop.getProperty("dbuser");
			prop.getProperty("dbpassword");
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		String connectionURL = "jdbc:mysql://" + prop.getProperty("database")
				+ "/x2succes?autoReconnect=true&useSSL=false";
		connect = DriverManager.getConnection(connectionURL, prop.getProperty("dbuser"),
				prop.getProperty("dbpassword"));

		return connect;
	}

}

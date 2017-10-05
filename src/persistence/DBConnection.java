package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	// protected Connection connect = null;

	// public Connection getConnection() throws DAOException {
	// Connection result = null;
	// String dbConnString = getConnectionString(aDbName);
	// try {
	//
	// DataSource datasource = (DataSource)initialContext.lookup(dbConnString);
	// if ( datasource == null ){
	// fLogger.severe("Datasource is null for : " + dbConnString);
	// }
	// result = datasource.getConnection();
	// }
	// catch (NamingException ex){
	// throw new DAOException(
	// "Config error with JNDI and datasource, for db " + Util.quote(dbConnString),
	// ex
	// );
	// }
	// catch (SQLException ex ){
	// throw new DAOException(
	// "Cannot get JNDI connection from datasource, for db " +
	// Util.quote(dbConnString),
	// ex
	// );
	// }
	// return result;
	//
	// }

	protected final Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection connect = null;
		Class.forName("com.mysql.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/x2succes?" + "user=root&password=password"); //TODO Password in configfile
		return connect;
	}

}

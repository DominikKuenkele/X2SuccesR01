package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import model.Adresse;
import model.Nutzer;
import model.Status;
import util.exception.DuplicateEntryException;
import util.exception.ValidateConstrArgsException;

public class NutzerDAO {
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	private void open() throws SQLException, ClassNotFoundException {
		DBConnection dbconnection = new DBConnection();
		connect = dbconnection.getConnection();
	}

	// You need to close the resultSet
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

	public int addNutzer(Nutzer nutzer) throws DuplicateEntryException {
		Adresse address = nutzer.getAddress();
		int nid = -1;
		if (getNutzer(nutzer.geteMail()) != null) {
			throw new DuplicateEntryException("E-Mail wird schon verwendet!");
		}
		try {
			open();

			preparedStatement = connect
					.prepareStatement("INSERT INTO Nutzer values (default, ?, ?, ?, ? , ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, nutzer.getFirstName());
			preparedStatement.setString(2, nutzer.getLastName());
			preparedStatement.setString(3, nutzer.getSex());
			preparedStatement.setObject(4, nutzer.getBirthdate());
			preparedStatement.setString(5, nutzer.geteMail());
			preparedStatement.setString(6, nutzer.getPassword());
			preparedStatement.setString(7, address.getPlz());
			preparedStatement.setString(8, address.getCity());
			preparedStatement.setString(9, address.getStrasse());
			preparedStatement.setString(10, address.getNumber());
			preparedStatement.setString(11, nutzer.getStatus().getText());
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("SELECT LAST_INSERT_ID()");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				nid = resultSet.getInt("last_insert_id()");
			}
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
		return nid;
	}

	private List<Nutzer> getNutzerFromResultSet(ResultSet resultSet) throws SQLException {
		List<Nutzer> result = new LinkedList<Nutzer>();
		while (resultSet.next()) {
			int nutzerId = resultSet.getInt("NID");
			String firstName = resultSet.getString("firstName");
			String lastName = resultSet.getString("lastName");
			String sex = resultSet.getString("sex");
			Date birthdateSQL = resultSet.getDate("birthdate");
			LocalDate birthdate = birthdateSQL.toLocalDate();
			String eMail = resultSet.getString("eMail");
			String password = resultSet.getString("password");
			String plz = resultSet.getString("plz");
			String city = resultSet.getString("city");
			String street = resultSet.getString("street");
			String number = resultSet.getString("number");
			String status = resultSet.getString("status");
			try {
				Nutzer tempNutzer = new Nutzer(firstName, lastName, sex, birthdate, eMail, password,
						new Adresse(plz, city, street, number), Status.valueOf(status));
				tempNutzer.setId(nutzerId);
				result.add(tempNutzer);
				
			} catch (ValidateConstrArgsException e) {
				// TODO Validaiation
				e.printStackTrace();
			}
		}
		return result;
	}

	public Nutzer getNutzer(String eMail) {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT * FROM Nutzer WHERE eMail = ?");
			preparedStatement.setString(1, eMail);

			resultSet = preparedStatement.executeQuery();
			List<Nutzer> result = getNutzerFromResultSet(resultSet);
			if (result.isEmpty()) {
				return null;
			} else {
				return result.get(0);
			}
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

	public Nutzer getNutzer(int id) {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT * FROM Nutzer WHERE NID = ?");
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();
			return getNutzerFromResultSet(resultSet).get(0);
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

	public List<Nutzer> getAllNutzer() {
		try {
			open();
			preparedStatement = connect.prepareStatement("SELECT * FROM Nutzer");
			resultSet = preparedStatement.executeQuery();
			return getNutzerFromResultSet(resultSet);
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

	public void deleteNutzer(String eMail) {
		try {
			open();
			preparedStatement = connect.prepareStatement("DELETE FROM Nutzer WHERE eMail = ?");
			preparedStatement.setString(1, eMail);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
	}
}

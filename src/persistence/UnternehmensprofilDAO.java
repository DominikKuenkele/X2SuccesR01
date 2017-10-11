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
import model.Unternehmensprofil;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class UnternehmensprofilDAO {
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
	 * @param unternehmen
	 * @return the generated ID of the new {@link model.Unternehmensprofil}
	 */
	public int addUnternehmensprofil(Unternehmensprofil unternehmen) {
		Adresse address = unternehmen.getAddress();
		int nutzerId = unternehmen.getNutzer().getId();
		int uid = -1;
		try {
			open();

			preparedStatement = connect.prepareStatement(
					"INSERT INTO Unternehmensprofil values (default, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setInt(1, nutzerId);
			preparedStatement.setString(2, unternehmen.getName());
			preparedStatement.setString(3, unternehmen.getLegalForm());
			preparedStatement.setObject(4, unternehmen.getFounding());
			preparedStatement.setInt(5, unternehmen.getEmployees());
			preparedStatement.setString(6, unternehmen.getDescription());
			preparedStatement.setString(7, unternehmen.getBenefits());
			preparedStatement.setString(8, unternehmen.getWebsite());
			preparedStatement.setString(9, unternehmen.getCeoFirstName());
			preparedStatement.setString(10, unternehmen.getCeoLastName());
			preparedStatement.setString(11, address.getPlz());
			preparedStatement.setString(12, address.getCity());
			preparedStatement.setString(13, address.getStrasse());
			preparedStatement.setString(14, address.getNumber());
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("SELECT LAST_INSERT_ID()");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				uid = resultSet.getInt("last_insert_id()");
			}
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
		return uid;
	}

	/**
	 * @param uid
	 * @return a {@link model.Unternehmensprofil} with given ID
	 */
	public Unternehmensprofil getUnternehmensprofil(int uid) {
		try {
			open();
			preparedStatement = connect
					.prepareStatement("SELECT UID, NID, name, legalForm, founding, employees, description, "
							+ "benefits, website, ceoFirstName, ceoLastName, plz, city, street, "
							+ "number FROM Unternehmensprofil WHERE UID = ?");
			preparedStatement.setInt(1, uid);

			resultSet = preparedStatement.executeQuery();
			return getUnternehmensprofilFromResultSet(resultSet).get(0);
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
	 * @return a List of all {@link model.Unternehmensprofil Unternehmensprofile} in
	 *         database
	 */
	public List<Unternehmensprofil> getAllUnternehmen() {
		try {
			open();
			preparedStatement = connect
					.prepareStatement("SELECT UID, NID, name, legalForm, founding, employees, description, "
							+ "benefits, website, ceoFirstName, ceoLastName, plz, city, street, "
							+ "number FROM Unternehmensprofil");
			resultSet = preparedStatement.executeQuery();
			return getUnternehmensprofilFromResultSet(resultSet);
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
	 * @param uid
	 */
	public void deleteUnternehmensprofil(int uid) {
		try {
			open();
			preparedStatement = connect.prepareStatement("DELETE FROM Unternehmesnprofil WHERE UID = ?");
			preparedStatement.setInt(1, uid);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e); // TODO syso
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
	}

	private List<Unternehmensprofil> getUnternehmensprofilFromResultSet(ResultSet resultSet) throws SQLException {
		List<Unternehmensprofil> result = new LinkedList<>();
		while (resultSet.next()) {
			int unternehmensId = resultSet.getInt("UID");
			int nutzerId = resultSet.getInt("NID");
			Nutzer nutzer = new NutzerDAO().getNutzer(nutzerId);
			String name = resultSet.getString("name");
			String legalForm = resultSet.getString("legalForm");
			Date foundingSQL = resultSet.getDate("founding");
			LocalDate founding = foundingSQL.toLocalDate();
			int employees = resultSet.getInt("employees");
			String description = resultSet.getString("description");
			String benefits = resultSet.getString("benefits");
			String website = resultSet.getString("website");
			String ceoFirstName = resultSet.getString("ceoFirstName");
			String ceoLastName = resultSet.getString("ceoLastName");
			String plz = resultSet.getString("plz");
			String city = resultSet.getString("city");
			String street = resultSet.getString("street");
			String number = resultSet.getString("number");
			try {
				Unternehmensprofil tempUnternehmen = new Unternehmensprofil(name, legalForm,
						new Adresse(plz, city, street, number), founding, employees, description, benefits, website,
						ceoFirstName, ceoLastName, nutzer);
				tempUnternehmen.setId(unternehmensId);
				result.add(tempUnternehmen);
			} catch (ValidateConstrArgsException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * @param aUnternehmen
	 */
	public void changeUnternehmen(Unternehmensprofil aUnternehmen) {
		Adresse address = aUnternehmen.getAddress();
		int nutzerId = aUnternehmen.getNutzer().getId();
		try {
			open();
			this.preparedStatement = this.connect.prepareStatement(
					"UPDATE Unternehmensprofil SET NID = ?, name = ?, legalForm = ?, founding = ?, employees = ?, description = ?, "
							+ "benefits = ?, website = ?, ceoFirstName = ?, ceoLastName = ?, plz = ?, city = ?, street = ?,"
							+ " number = ? WHERE UID = ?");
			preparedStatement.setInt(1, nutzerId);
			preparedStatement.setString(2, aUnternehmen.getName());
			preparedStatement.setString(3, aUnternehmen.getLegalForm());
			preparedStatement.setObject(4, aUnternehmen.getFounding());
			preparedStatement.setInt(5, aUnternehmen.getEmployees());
			preparedStatement.setString(6, aUnternehmen.getDescription());
			preparedStatement.setString(7, aUnternehmen.getBenefits());
			preparedStatement.setString(8, aUnternehmen.getWebsite());
			preparedStatement.setString(9, aUnternehmen.getCeoFirstName());
			preparedStatement.setString(10, aUnternehmen.getCeoLastName());
			preparedStatement.setString(11, address.getPlz());
			preparedStatement.setString(12, address.getCity());
			preparedStatement.setString(13, address.getStrasse());
			preparedStatement.setString(14, address.getNumber());
			preparedStatement.setInt(15, aUnternehmen.getId());
			preparedStatement.executeUpdate();
		} catch (final SQLException e) {
			System.out.println(e); // TODO syso
		} catch (final ClassNotFoundException e) {
			System.out.println(e);
		} finally {
			close();
		}
	}
}

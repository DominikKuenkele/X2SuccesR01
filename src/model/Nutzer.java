package model;

import java.time.LocalDate;

import util.Validate;
import util.exception.ValidateConstrArgsException;

public class Nutzer {
	private int nid = -1;
	private String firstName;
	private String lastName;
	private String sex;
	private LocalDate birthdate;
	private String eMail;
	private String password;
	private Adresse address;
	private Status status;

	/**
	 * @param firstName
	 * @param lastName
	 * @param sex
	 * @param birthdate
	 * @param eMail
	 * @param password
	 * @param address
	 * @param status
	 * @throws ValidateConstrArgsException
	 */
	public Nutzer(String firstName, String lastName, String sex, LocalDate birthdate, String eMail, String password,
			Adresse address, Status status) throws ValidateConstrArgsException {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.birthdate = birthdate;
		this.eMail = eMail;
		this.password = password;
		this.address = address;
		this.status = status;

		validateState();
	}

	public Status getStatus() {
		return status;
	}

	/**
	 * @return the eMail
	 */
	public String geteMail() {
		return eMail;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @return the birthdate
	 */
	public LocalDate getBirthdate() {
		return birthdate;
	}

	/**
	 * @return the address
	 */
	public Adresse getAddress() {
		return address;
	}

	/**
	 * @return the nid
	 */
	public int getId() {
		return nid;
	}

	/**
	 * @param nid the nid to set
	 */
	public void setId(int nid) {
		this.nid = nid;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

	private void validateState() throws ValidateConstrArgsException {
		try {
			Validate.checkForEMail(eMail);
			Validate.checkForContent(password);
			Validate.checkForAlpha(firstName);
			Validate.checkForAlpha(lastName);
			Validate.checkForAlpha(sex);
			Validate.checkForDateInPast(birthdate);
		} catch (IllegalArgumentException e) {
			throw new ValidateConstrArgsException(e.getMessage());
		}
	}

	@Override
	public String toString() {
		return "Nutzer [eMail=" + eMail + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", sex=" + sex + ", birthdate=" + birthdate + ", address=" + address + ", status=" + status
				+ "]\n";
	}

}

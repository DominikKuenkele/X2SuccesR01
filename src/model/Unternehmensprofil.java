package model;

import java.time.LocalDate;

import util.Validate;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class Unternehmensprofil implements Profil {
	private int uid = -1;
	private String name;
	private String legalForm;
	private Adresse address;
	private LocalDate founding;
	private int employees;
	private String description;
	private String branche;
	private String website;
	private String ceoFirstName;
	private String ceoLastName;
	private Nutzer nutzer;

	/**
	 * @param name
	 * @param legalForm
	 * @param address
	 * @param founding
	 * @param employees
	 * @param description
	 * @param branche
	 * @param website
	 * @param ceoFirstName
	 * @param ceoLastName
	 * @param nutzer
	 * @throws ValidateConstrArgsException
	 */
	public Unternehmensprofil(String name, String legalForm, Adresse address, LocalDate founding, int employees,
			String description, String branche, String website, String ceoFirstName, String ceoLastName, Nutzer nutzer)
			throws ValidateConstrArgsException {
		this.name = name;
		this.legalForm = legalForm;
		this.address = address;
		this.founding = founding;
		this.employees = employees;
		this.description = description;
		this.branche = branche;
		this.website = website;
		this.ceoFirstName = ceoFirstName;
		this.ceoLastName = ceoLastName;
		this.nutzer = nutzer;

		validateState();
	}

	/**
	 * @return the uid
	 */
	public int getId() {
		return uid;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the legalForm
	 */
	public String getLegalForm() {
		return legalForm;
	}

	/**
	 * @return the address
	 */
	public Adresse getAddress() {
		return address;
	}

	/**
	 * @return the founding
	 */
	public LocalDate getFounding() {
		return founding;
	}

	/**
	 * @return the employees
	 */
	public int getEmployees() {
		return employees;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the branche
	 */
	public String getBranche() {
		return branche;
	}

	/**
	 * @return the website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * @return the ceoFirstName
	 */
	public String getCeoFirstName() {
		return ceoFirstName;
	}

	/**
	 * @return the ceoLastName
	 */
	public String getCeoLastName() {
		return ceoLastName;
	}

	/**
	 * @return the nutzer
	 */
	public Nutzer getNutzer() {
		return nutzer;
	}

	private void validateState() throws ValidateConstrArgsException {
		String message = "";

		try {
			Validate.checkForContent(name);
		} catch (IllegalArgumentException e) {
			message = message + "\nName: " + e.getMessage();
		}
		try {
			Validate.checkForContent(description);
		} catch (IllegalArgumentException e) {
			message = message + "\nBeschreibung: " + e.getMessage();
		}
		try {
			Validate.checkForAlpha(ceoFirstName);
		} catch (IllegalArgumentException e) {
			message = message + "\nCEO Vorname: " + e.getMessage();
		}
		try {
			Validate.checkForAlpha(ceoFirstName);
		} catch (IllegalArgumentException e) {
			message = message + "\nCEO-Nachname: " + e.getMessage();
		}

		if (message != "") {
			throw new ValidateConstrArgsException(message);
		}
	}

	/**
	 * @param uid
	 */
	public void setId(int uid) {
		this.uid = uid;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Unternehmensprofil [uid=" + this.uid + ", name=" + this.name + ", legalForm=" + this.legalForm
				+ ", address=" + this.address + ", founding=" + this.founding + ", employees=" + this.employees
				+ ", description=" + this.description + ", branche=" + this.branche + ", website=" + this.website
				+ ", ceoFirstName=" + this.ceoFirstName + ", ceoLastName=" + this.ceoLastName + ", nutzer="
				+ this.nutzer + "]";
	}
}

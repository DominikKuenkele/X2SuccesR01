package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import util.Validate;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class Freelancerprofil implements Profil {
	private static final int ANZAHL_STAERKEN = 3;

	private int fid = -1;
	private String abschluss;
	private String fachgebiet;
	private String beschreibung;
	private String[] skills = new String[ANZAHL_STAERKEN];
	private String lebenslauf;
	private List<String> sprachen;
	private Nutzer nutzer;

	/**
	 * @param abschluss
	 * @param fachgebiet
	 * @param beschreibung
	 * @param skills
	 * @param lebenslauf
	 * @param sprachen
	 * @param nutzer
	 * @throws ValidateConstrArgsException
	 */
	public Freelancerprofil(final String abschluss, final String fachgebiet, final String beschreibung,
			final String[] skills, final String lebenslauf, final List<String> sprachen, Nutzer nutzer)
			throws ValidateConstrArgsException {
		this.abschluss = abschluss;
		this.fachgebiet = fachgebiet;
		this.beschreibung = beschreibung;
		this.skills = Arrays.copyOf(skills, skills.length);
		this.lebenslauf = lebenslauf;
		this.sprachen = sprachen;
		this.nutzer = nutzer;

		validateState();
	}

	@Override
	public String toString() {
		return "Freelancerprofil [fid=" + this.fid + ", abschluss=" + this.abschluss + ", fachgebiet=" + this.fachgebiet
				+ ", beschreibung=" + this.beschreibung + ", skills=" + Arrays.toString(this.skills) + ", lebenslauf="
				+ this.lebenslauf + ", sprachen=" + this.sprachen + ", nutzer=" + this.nutzer + "]";
	}

	/**
	 * @return the abschluss
	 */
	public String getAbschluss() {
		return abschluss;
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @return the fachgebiet
	 */
	public String getFachgebiet() {
		return this.fachgebiet;
	}

	/**
	 * @return the skills
	 */
	public String[] getSkills() {
		return Arrays.copyOf(skills, skills.length);
	}

	/**
	 * @return the lebenslauf
	 */
	public String getLebenslauf() {
		return lebenslauf;
	}

	/**
	 * @return the sprachen
	 */
	public List<String> getSprachen() {
		return Collections.unmodifiableList(sprachen);
	}

	/**
	 * @return the fid
	 */
	public int getFID() {
		return fid;
	}

	/**
	 * @param fid
	 *            the fid to set
	 */
	public void setId(int fid) {
		this.fid = fid;
	}

	/**
	 * @return the nutzer
	 */
	public Nutzer getNutzer() {
		return nutzer;
	}

	private void validateSkills(final String... skills) {
		if (skills.length > ANZAHL_STAERKEN) {
			throw new IllegalArgumentException("Es d�rfen nur drei Skills angegeben werden.");
		}
		for (int i = 0; i < skills.length; i++) {
			Validate.checkForContent(skills[i]);
		}
	}

	private void validateState() throws ValidateConstrArgsException {
		String message = "";

		try {
			Validate.checkForContent(abschluss);
		} catch (IllegalArgumentException e) {
			message = message + "\nAbschluss: " + e.getMessage();
		}
		try {
			Validate.checkForContent(beschreibung);
		} catch (IllegalArgumentException e) {
			message = message + "\nBeschreibung: " + e.getMessage();
		}
		try {
			validateSkills(skills);
		} catch (IllegalArgumentException e) {
			message = message + "\nSkills: " + e.getMessage();
		}

		try {
			Validate.checkForContent(lebenslauf);
		} catch (IllegalArgumentException e) {
			message = message + "\nLebenslauf: " + e.getMessage();
		}

		if (message != "") {
			throw new ValidateConstrArgsException(message);
		}
	}

	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object != null && object instanceof Freelancerprofil) {
			if (((Freelancerprofil) object).getFID() == fid) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fid);
	}
}

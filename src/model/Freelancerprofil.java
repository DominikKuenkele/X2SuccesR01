package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
	private String branche;
	private String beschreibung;
	private String[] skills = new String[ANZAHL_STAERKEN];
	private String lebenslauf;
	private String benefits;
	private List<String> sprachen;
	private Nutzer nutzer;

	/**
	 * @param abschluss
	 * @param branche
	 * @param beschreibung
	 * @param skills
	 * @param lebenslauf
	 * @param benefits
	 * @param sprachen
	 * @param nutzer
	 * @throws ValidateConstrArgsException
	 */
	public Freelancerprofil(final String abschluss, final String branche, final String beschreibung,
			final String[] skills, final String lebenslauf, final String benefits, final List<String> sprachen,
			Nutzer nutzer) throws ValidateConstrArgsException {
		this.abschluss = abschluss;
		this.branche = branche;
		this.beschreibung = beschreibung;
		this.skills = Arrays.copyOf(skills, skills.length);
		this.lebenslauf = lebenslauf;
		this.benefits = benefits;
		this.sprachen = sprachen;
		this.nutzer = nutzer;

		validateState();
	}

	@Override
	public String toString() {
		return "Freelancerprofil [fid=" + this.fid + ", abschluss=" + this.abschluss + ", branche=" + this.branche
				+ ", beschreibung=" + this.beschreibung + ", skills=" + Arrays.toString(this.skills) + ", lebenslauf="
				+ this.lebenslauf + ", benefits=" + this.benefits + ", sprachen=" + this.sprachen + ", nutzer="
				+ this.nutzer + "]";
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
	 * @return the branche
	 */
	public String getBranche() {
		return this.branche;
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
	 * @return the benefits
	 */
	public String getBenefits() {
		return benefits;
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
	public int getId() {
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
			throw new IllegalArgumentException("Es dürfen nur drei Skills angegeben werden.");
		}
		for (int i = 0; i < skills.length; i++) {
			Validate.checkForContent(skills[i]);
		}
	}

	private void validateState() throws ValidateConstrArgsException {
		try {
			Validate.checkForContent(abschluss);
			Validate.checkForAlpha(branche);
			Validate.checkForContent(beschreibung);
			validateSkills(skills);
			Validate.checkForContent(lebenslauf);
			Validate.checkForContent(benefits);
		} catch (IllegalArgumentException e) {
			throw new ValidateConstrArgsException(e.getMessage());
		}
	}
}

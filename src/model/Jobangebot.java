package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import util.Validate;
import util.exception.ValidateConstrArgsException;

public class Jobangebot {
	private int jid = -1;
	private String abschluss;
	private List<String> sprachen;
	private String beschreibung;
	private LocalDate frist;
	private int minGehalt;
	private int maxGehalt;
	private int wochenstunden;
	private Unternehmensprofil unternehmensproflil;

	/**
	 * @param JID
	 * @param abschluss
	 * @param sprachen
	 * @param beschreibung
	 * @param frist
	 * @param minGehalt
	 * @param maxGehalt
	 * @param wochenstunden
	 * @param unternehmensprofil
	 * @throws ValidateConstrArgsException
	 */
	public Jobangebot(String abschluss, List<String> sprachen, String beschreibung, LocalDate frist, int minGehalt,
			int maxGehalt, int wochenstunden, Unternehmensprofil unternehmensprofil)
			throws ValidateConstrArgsException {
		this.abschluss = abschluss;
		this.sprachen = sprachen;
		this.beschreibung = beschreibung;
		this.frist = frist;
		this.minGehalt = minGehalt;
		this.maxGehalt = maxGehalt;
		this.wochenstunden = wochenstunden;
		this.unternehmensproflil = unternehmensprofil;

		validateState();
	}

	/**
	 * @return the JID
	 */
	public int getJID() {
		return jid;
	}

	/**
	 * @return the abschluss
	 */
	public String getAbschluss() {
		return abschluss;
	}

	/**
	 * @return the sprachen
	 */
	public List<String> getSprachen() {
		return Collections.unmodifiableList(sprachen);
	}

	/**
	 * @return the beschreibung
	 */
	public String getBeschreibung() {
		return beschreibung;
	}

	/**
	 * @return the frist
	 */
	public LocalDate getFrist() {
		return frist;
	}

	/**
	 * @return the minGehalt
	 */
	public int getMinGehalt() {
		return minGehalt;
	}

	/**
	 * @return the maxGehalt
	 */
	public int getMaxGehalt() {
		return maxGehalt;
	}

	/**
	 * @return the wochenstunden
	 */
	public int getWochenstunden() {
		return wochenstunden;
	}

	/**
	 * @return the unternehmensproflil
	 */
	public Unternehmensprofil getUnternehmensproflil() {
		return unternehmensproflil;
	}

	/**
	 * @param jID
	 *            the jID to set
	 */
	public void setId(int jID) {
		this.jid = jID;
	}

	private void validateState() throws ValidateConstrArgsException {
		try {
			Validate.checkForAlphaNumeric(beschreibung);
			Validate.checkForPositive(minGehalt);
			Validate.checkForGreaterValue(maxGehalt, minGehalt);
			Validate.checkForPositive(wochenstunden);
			// TODO validate unternehmensprofil
		} catch (IllegalArgumentException e) {
			throw new ValidateConstrArgsException(e.getMessage());
		}
	}

}

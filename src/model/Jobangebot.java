package model;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import util.Validate;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class Jobangebot {
	private int jid = -1;
	private String abschluss;
	private String branche;
	private List<String> sprachen;
	private String beschreibung;
	private LocalDate frist;
	private int gehalt;
	private int wochenstunden;
	private Unternehmensprofil unternehmensproflil;

	/**
	 * @param abschluss
	 * @param branche
	 * @param sprachen
	 * @param beschreibung
	 * @param frist
	 * @param gehalt
	 * @param wochenstunden
	 * @param unternehmensprofil
	 * @throws ValidateConstrArgsException
	 */
	public Jobangebot(String abschluss, String branche, List<String> sprachen, String beschreibung, LocalDate frist,
			int gehalt, int wochenstunden, Unternehmensprofil unternehmensprofil) throws ValidateConstrArgsException {
		this.abschluss = abschluss;
		this.branche = branche;
		this.sprachen = sprachen;
		this.beschreibung = beschreibung;
		this.frist = frist;
		this.gehalt = gehalt;
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
	 * @return the branche
	 */
	public String getBranche() {
		return this.branche;
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
	 * @return the gehalt
	 */
	public int getGehalt() {
		return gehalt;
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
	public Unternehmensprofil getUnternehmensprofil() {
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
			Validate.checkForPositive(gehalt);
			Validate.checkForPositive(wochenstunden);
			// TODO validate unternehmensprofil
		} catch (IllegalArgumentException e) {
			throw new ValidateConstrArgsException(e.getMessage());
		}
	}

	@Override
	public boolean equals(Object object) {
		boolean result = false;
		if (object != null && object instanceof Jobangebot) {
			if (((Jobangebot) object).getJID() == jid) {
				result = true;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return Objects.hash(jid);
	}

	@Override
	public String toString() {
		return "Jobangebot [jid=" + this.jid + ", abschluss=" + this.abschluss + ", branche=" + this.branche
				+ ", sprachen=" + this.sprachen + ", beschreibung=" + this.beschreibung + ", frist=" + this.frist
				+ ", gehalt=" + this.gehalt + ", wochenstunden=" + this.wochenstunden + ", unternehmensproflil="
				+ this.unternehmensproflil + "]";
	}
}

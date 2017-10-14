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
	private String fachgebiet;
	private List<String> sprachen;
	private String jobTitel;
	private String beschreibung;
	private LocalDate frist;
	private int gehalt;
	private int wochenstunden;
	private Unternehmensprofil unternehmensproflil;

	/**
	 * @param abschluss
	 * @param fachgebiet
	 * @param sprachen
	 * @param jobTitel
	 * @param beschreibung
	 * @param frist
	 * @param gehalt
	 * @param wochenstunden
	 * @param unternehmensprofil
	 * @throws ValidateConstrArgsException
	 */
	public Jobangebot(String abschluss, String fachgebiet, List<String> sprachen, String jobTitel, String beschreibung,
			LocalDate frist, int gehalt, int wochenstunden, Unternehmensprofil unternehmensprofil)
			throws ValidateConstrArgsException {
		this.abschluss = abschluss;
		this.fachgebiet = fachgebiet;
		this.sprachen = sprachen;
		this.jobTitel = jobTitel;
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
	 * @return the fachgebiet
	 */
	public String getFachgebiet() {
		return this.fachgebiet;
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
	 * @return the jobTitel
	 */
	public String getJobTitel() {
		return this.jobTitel;
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

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Jobangebot [jid=" + this.jid + ", abschluss=" + this.abschluss + ", fachgebiet=" + this.fachgebiet
				+ ", sprachen=" + this.sprachen + ", jobTitel=" + this.jobTitel + ", beschreibung=" + this.beschreibung
				+ ", frist=" + this.frist + ", gehalt=" + this.gehalt + ", wochenstunden=" + this.wochenstunden
				+ ", unternehmensproflil=" + this.unternehmensproflil + "]";
	}
}

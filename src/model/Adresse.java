package model;

import util.Validate;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class Adresse {
	private String strasse;
	private String number;
	private String city;
	private String plz;

	/**
	 * @param strasse
	 * @param number
	 * @param city
	 * @param plz
	 * @throws ValidateConstrArgsException
	 */
	public Adresse(final String plz, final String city, final String strasse, final String number)
			throws ValidateConstrArgsException {
		this.strasse = strasse;
		this.number = number;
		this.city = city;
		this.plz = plz;

		validateState();
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return strasse;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @return the plz
	 */
	public String getPlz() {
		return plz;
	}

	private void validateState() throws ValidateConstrArgsException {
		try {
			Validate.checkForAlpha(strasse);
			Validate.checkForPositive(Integer.parseInt(number));
			Validate.checkForPositive(Integer.parseInt(plz));
			Validate.checkForAlpha(city);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw new ValidateConstrArgsException(e.getMessage());
		}
	}
}

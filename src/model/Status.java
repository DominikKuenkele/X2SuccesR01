/**
 * 
 */
package model;

/**
 * @author domin
 *
 */
public enum Status {
	N("N"), F("F"), U("U");

	private String text;

	private Status(String text) {
		this.text = text;
	}

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

}

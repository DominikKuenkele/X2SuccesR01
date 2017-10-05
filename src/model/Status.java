/**
 * 
 */
package model;

/**
 * @author domin
 *
 */
public enum Status {
	NONE(""), F("F"), U("U");
	
	private String text;

	private Status(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}
	
}

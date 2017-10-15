/**
 * 
 */
package util.exception;

/**
 * throwable User Input-Exception for presentation-layer
 * 
 * @author domin
 *
 */
public class UserInputException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7183575704464610813L;

	/**
	 * 
	 */
	public UserInputException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UserInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UserInputException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public UserInputException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public UserInputException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}

/**
 * 
 */
package util.exception;

/**
 * throwable DB-Exception for logical-layer
 * 
 * @author domin
 *
 */
public class DuplicateEntryException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1154685803747114315L;

	/**
	 * 
	 */
	public DuplicateEntryException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public DuplicateEntryException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public DuplicateEntryException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DuplicateEntryException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DuplicateEntryException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

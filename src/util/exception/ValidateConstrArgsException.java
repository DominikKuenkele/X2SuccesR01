package util.exception;

/**
 * throwable Validation-Exception for logical-layer
 * 
 * @author domin
 *
 */
public class ValidateConstrArgsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4732122161608535198L;

	/**
	 * 
	 */
	public ValidateConstrArgsException() {
		super();
	}

	/**
	 * @param message
	 */
	public ValidateConstrArgsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidateConstrArgsException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidateConstrArgsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ValidateConstrArgsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}

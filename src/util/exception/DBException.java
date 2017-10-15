/**
 * 
 */
package util.exception;

/**
 * throwable DB-Exception for presentation-layer
 * 
 * @author domin
 *
 */
public class DBException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3484252720856531408L;

	/**
	 * 
	 */
	public DBException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aMessage
	 */
	public DBException(String aMessage) {
		super(aMessage);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aCause
	 */
	public DBException(Throwable aCause) {
		super(aCause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aMessage
	 * @param aCause
	 */
	public DBException(String aMessage, Throwable aCause) {
		super(aMessage, aCause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param aMessage
	 * @param aCause
	 * @param aEnableSuppression
	 * @param aWritableStackTrace
	 */
	public DBException(String aMessage, Throwable aCause, boolean aEnableSuppression, boolean aWritableStackTrace) {
		super(aMessage, aCause, aEnableSuppression, aWritableStackTrace);
		// TODO Auto-generated constructor stub
	}

}

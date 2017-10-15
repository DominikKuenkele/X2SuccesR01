/**
 * 
 */
package application;

import model.Nutzer;
import model.Unternehmensprofil;

/**
 * 
 * This interface provides the methods for an Observer for an
 * {@link model.Unternehmensprofil Unternehmensprofil-Object}.
 * 
 * @author domin
 *
 */
public interface UnternehmerObserver {
	/**
	 * Method is called, when {@link model.Unternehmensprofil
	 * Unternehmensprofil-Object} has been changed.
	 * 
	 * @param unternehmensprofil
	 */
	public void updateUnternehmer(Unternehmensprofil unternehmensprofil);

	/**
	 * Method is called, when {@link model.Nutzer Nutzer-Object} has been changed.
	 * 
	 * @param nutzer
	 */
	public void updateNutzer(Nutzer nutzer);

}

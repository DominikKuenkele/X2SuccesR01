/**
 * 
 */
package application;

import model.Nutzer;
import model.Unternehmensprofil;

/**
 * @author domin
 *
 */
public interface UnternehmerObserver {
	public void updateUnternehmer(Unternehmensprofil unternehmer);

	public void updateNutzer(Nutzer nutzer);

}

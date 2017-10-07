/**
 * 
 */
package application;

import model.Freelancerprofil;
import model.Nutzer;

/**
 * @author domin
 *
 */
public interface FreelancerObserver {

	public void updateFreelancer(Freelancerprofil freelancer);

	public void updateNutzer(Nutzer nutzer);

}

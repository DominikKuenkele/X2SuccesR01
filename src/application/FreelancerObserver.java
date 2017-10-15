package application;

import model.Freelancerprofil;
import model.Nutzer;

/**
 * This interface provides the methods for an Observer for an
 * {@link model.Freelancerprofil Freelancerprofil-Object}.
 * 
 * @author domin
 */
public interface FreelancerObserver {

	/**
	 * Method is called, when {@link model.Freelancerprofil Freelancerprofil-Object}
	 * has been changed.
	 * 
	 * @param freelancer
	 */
	public void updateFreelancer(Freelancerprofil freelancer);

	/**
	 * Method is called, when {@link model.Nutzer Nutzer-Object} has been changed.
	 * 
	 * @param nutzer
	 */
	public void updateNutzer(Nutzer nutzer);

}

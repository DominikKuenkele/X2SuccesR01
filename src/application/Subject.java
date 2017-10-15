/**
 * 
 */
package application;

import java.util.ArrayList;
import java.util.List;

import model.Freelancerprofil;
import model.Nutzer;
import model.Unternehmensprofil;

/**
 * Class is an abstract Subject-Class that provides methods for
 * Observer-Classes.
 * 
 * @author domin
 *
 */
public abstract class Subject {
	private final List<UnternehmerObserver> observerUnternehmerList = new ArrayList<>();
	private final List<FreelancerObserver> observerFreelancerList = new ArrayList<>();

	/**
	 * Registers a new Freelancer-Observer at this Subject-Class.
	 * 
	 * @param newObserver
	 */
	public void registerAsFreelancer(FreelancerObserver newObserver) {
		observerFreelancerList.add(newObserver);
	}

	/**
	 * Unregisters a Freelancer-Observer from this Subject-Class.
	 * 
	 * @param newObserver
	 */
	public void unregisterAsFreelancer(FreelancerObserver newObserver) {
		observerFreelancerList.remove(newObserver);
	}

	/**
	 * Registers a new Unternehmensprofil-Observer at this Subject-Class.
	 * 
	 * @param newObserver
	 */
	public void registerAsUnternehmer(UnternehmerObserver newObserver) {
		observerUnternehmerList.add(newObserver);
	}

	/**
	 * Unregisters a Unternehmensprofil-Observer from this Subject-Class.
	 * 
	 * @param newObserver
	 */
	public void unregisterAsUnternehmer(UnternehmerObserver newObserver) {
		observerUnternehmerList.remove(newObserver);
	}

	/**
	 * Notifies all Freelancer-Observers with a given {@link model.Freelancerprofil
	 * Freelancer-Object}.
	 * 
	 * @param freelancerprofil
	 */
	public void notifyFreelancerObeserver(Freelancerprofil freelancerprofil) {
		for (FreelancerObserver observer : observerFreelancerList) {
			observer.updateFreelancer(freelancerprofil);
		}
	}

	/**
	 * Notifies all Unternehmensprofil-Observers with a given
	 * {@link model.Unternehmensprofil Unternehmensprofil-Object}.
	 * 
	 * @param unternehmensprofil
	 */
	public void notifyUnternehmerObeserver(Unternehmensprofil unternehmensprofil) {
		for (UnternehmerObserver observer : observerUnternehmerList) {
			observer.updateUnternehmer(unternehmensprofil);
		}
	}

	/**
	 * Notifies all Observers with a given {@link model.Nutzer Nutzer-Object}.
	 * 
	 * 
	 * @param nutzer
	 */
	public void notifyAllObservers(Nutzer nutzer) {
		for (FreelancerObserver observer : observerFreelancerList) {
			observer.updateNutzer(nutzer);
		}
		for (UnternehmerObserver observer : observerUnternehmerList) {
			observer.updateNutzer(nutzer);
		}
	}
}

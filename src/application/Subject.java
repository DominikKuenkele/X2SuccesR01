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
 * @author domin
 *
 */
public abstract class Subject {
	private final List<UnternehmerObserver> observerUnternehmerList = new ArrayList<>();
	private final List<FreelancerObserver> observerFreelancerList = new ArrayList<>();

	public void registerAsFreelancer(FreelancerObserver newObserver) {
		observerFreelancerList.add(newObserver);
	}

	public void unregisterAsFreelancer(FreelancerObserver newObserver) {
		observerFreelancerList.remove(newObserver);
	}

	public void registerAsUnternehmer(UnternehmerObserver newObserver) {
		observerUnternehmerList.add(newObserver);
	}

	public void unregisterAsUnternehmer(UnternehmerObserver newObserver) {
		observerUnternehmerList.remove(newObserver);
	}

	public void notifyFreelancerObeserver(Freelancerprofil freelancerprofil) {
		for (FreelancerObserver observer : observerFreelancerList) {
			observer.updateFreelancer(freelancerprofil);
		}
	}

	public void notifyUnternehmerObeserver(Unternehmensprofil unternehmensprofil) {
		for (UnternehmerObserver observer : observerUnternehmerList) {
			observer.updateUnternehmer(unternehmensprofil);
		}
	}

	public void notifyAllObservers(Nutzer nutzer) {
		for (FreelancerObserver observer : observerFreelancerList) {
			observer.updateNutzer(nutzer);
		}
		for (UnternehmerObserver observer : observerUnternehmerList) {
			observer.updateNutzer(nutzer);
		}
	}
}

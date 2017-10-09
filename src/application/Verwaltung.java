package application;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import model.Adresse;
import model.Freelancerprofil;
import model.Jobangebot;
import model.Nutzer;
import model.Profil;
import model.Status;
import model.Unternehmensprofil;
import persistence.FreelancerprofilDAO;
import persistence.JobangebotDAO;
import persistence.NutzerDAO;
import persistence.UnternehmensprofilDAO;
import util.PassHash;
import util.exception.DuplicateEntryException;
import util.exception.UserInputException;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class Verwaltung extends Subject {

	private static Verwaltung instance;

	/**
	 * private Constructor prevents instantiation of this class
	 */
	private Verwaltung() {
	}

	private Nutzer currentNutzer;
	private Unternehmensprofil currentUnternehmen;
	private Freelancerprofil currentFreelancer;

	/**
	 * @return the Instance of this Singleton-Class
	 */
	public static Verwaltung getInstance() {
		if (instance == null) {
			instance = new Verwaltung();
		}
		return instance;
	}

	/**
	 * @return the current {@link model.Nutzer}
	 */
	public Nutzer getCurrentNutzer() {
		return this.currentNutzer;
	}

	/**
	 * @return the current profile
	 */
	public Profil getCurrentProfil() {
		switch (this.currentNutzer.getStatus()) {
		case F:
			return this.currentFreelancer;
		case U:
			return this.currentUnternehmen;
		default:
			return null;
		}
	}

	/**
	 * @param fName
	 * @param lName
	 * @param sex
	 * @param plz
	 * @param city
	 * @param street
	 * @param number
	 * @param date
	 * @param eMail
	 * @param password
	 * @throws UserInputException
	 */
	public void register(final String fName, final String lName, final String sex, final String plz, final String city,
			final String street, final String number, final LocalDate date, final String eMail, final String password)
			throws UserInputException {
		try {
			final String passHash = PassHash.generateStrongPasswordHash(password);
			final Nutzer nutzer = new Nutzer(fName, lName, sex, date, eMail, passHash,
					new Adresse(plz, city, street, number), Status.N);
			final int nid = new NutzerDAO().addNutzer(nutzer);
			nutzer.setId(nid);
			setCurrentNutzer(nutzer);
		} catch (ValidateConstrArgsException | DuplicateEntryException e) {
			throw new UserInputException(e.getMessage());
		}

	}

	/**
	 * @param name
	 * @param form
	 * @param plz
	 * @param city
	 * @param street
	 * @param number
	 * @param founding
	 * @param employees
	 * @param description
	 * @param benefits
	 * @param branche
	 * @param website
	 * @param ceoFirstName
	 * @param ceoLastName
	 * @throws UserInputException
	 */
	public void createUnternehmen(final String name, final String form, final String plz, final String city,
			final String street, final String number, final LocalDate founding, final int employees,
			final String description, final String benefits, final String branche, final String website,
			final String ceoFirstName, final String ceoLastName) throws UserInputException {
		if (this.currentNutzer.getStatus() == Status.F) {
			throw new UserInputException("Nutzer ist schon Freelancer!");
		}
		try {
			final Unternehmensprofil unternehmen = new Unternehmensprofil(name, form,
					new Adresse(plz, city, street, number), founding, employees, description, benefits, branche,
					website, ceoFirstName, ceoLastName, this.currentNutzer);
			final UnternehmensprofilDAO unternehmensprofilDao = new UnternehmensprofilDAO();
			final int uid = unternehmensprofilDao.addUnternehmensprofil(unternehmen);
			unternehmen.setId(uid);
			setCurrentUnternehmensprofil(unternehmen);
			this.currentNutzer.setStatus(Status.U);
			new NutzerDAO().changeNutzer(currentNutzer);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		}
	}

	/**
	 * @param name
	 * @param form
	 * @param plz
	 * @param city
	 * @param street
	 * @param number
	 * @param founding
	 * @param employees
	 * @param description
	 * @param benefits
	 * @param branche
	 * @param website
	 * @param ceoFirstName
	 * @param ceoLastName
	 * @throws UserInputException
	 */
	public void changeUnternehmen(final String name, final String form, final String plz, final String city,
			final String street, final String number, final LocalDate founding, final int employees,
			final String description, final String benefits, final String branche, final String website,
			final String ceoFirstName, final String ceoLastName) throws UserInputException {
		try {
			final Unternehmensprofil unternehmen = new Unternehmensprofil(name, form,
					new Adresse(plz, city, street, number), founding, employees, description, benefits, branche,
					website, ceoFirstName, ceoLastName, this.currentNutzer);
			unternehmen.setId(this.currentUnternehmen.getId());
			new UnternehmensprofilDAO().changeUnternehmen(unternehmen);
			setCurrentUnternehmensprofil(unternehmen);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		}
	}

	/**
	 * @param abschluss
	 * @param beschreibung
	 * @param skills
	 * @param lebenslauf
	 * @param benefits
	 * @param sprachen
	 * @throws UserInputException
	 */
	public void createFreelancer(final String abschluss, final String beschreibung, final String[] skills,
			final String lebenslauf, final String benefits, final List<String> sprachen) throws UserInputException {
		if (this.currentNutzer.getStatus() == Status.U) {
			throw new UserInputException("Nutzer ist schon Unternehmer!");
		}
		try {
			final Freelancerprofil freelancer = new Freelancerprofil(abschluss, beschreibung, skills, lebenslauf,
					benefits, sprachen, this.currentNutzer);
			final FreelancerprofilDAO freelancerprofilDao = new FreelancerprofilDAO();
			final int fid = freelancerprofilDao.addFreelancerprofil(freelancer);
			freelancer.setId(fid);
			setCurrentFreelancer(freelancer);
			this.currentNutzer.setStatus(Status.F);
			new NutzerDAO().changeNutzer(currentNutzer);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		}
	}

	/**
	 * @param abschluss
	 * @param sprachen
	 * @param beschreibung
	 * @param frist
	 * @param minGehalt
	 * @param maxGehalt
	 * @param wochenstunden
	 * @throws UserInputException
	 */
	public void createJobangebot(final String abschluss, final List<String> sprachen, final String beschreibung,
			final LocalDate frist, final int minGehalt, final int maxGehalt, final int wochenstunden)
			throws UserInputException {
		if (this.currentNutzer.getStatus() == Status.F) {
			throw new UserInputException("Ein Freelancer kann kein Jobangebot erstellen.");
		}
		try {
			final Jobangebot jobangebot = new Jobangebot(abschluss, sprachen, beschreibung, frist, minGehalt, maxGehalt,
					wochenstunden, this.currentUnternehmen);
			final int jid = new JobangebotDAO().addJobangebot(jobangebot);
			jobangebot.setId(jid);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		}
	}

	/**
	 * @param fName
	 * @param lName
	 * @param sex
	 * @param plz
	 * @param city
	 * @param street
	 * @param number
	 * @param date
	 * @throws UserInputException
	 */
	public void changeNutzer(final String fName, final String lName, final String sex, final String plz,
			final String city, final String street, final String number, final LocalDate date)
			throws UserInputException {
		try {
			final Nutzer nutzer = new Nutzer(fName, lName, sex, date, this.currentNutzer.geteMail(),
					this.currentNutzer.getPassword(), new Adresse(plz, city, street, number),
					this.currentNutzer.getStatus());
			nutzer.setId(this.currentNutzer.getId());
			new NutzerDAO().changeNutzer(nutzer);
			setCurrentNutzer(nutzer);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		}
	}

	/**
	 * @param eMail
	 * @param password
	 * @return if login was successful
	 */
	public boolean login(final String eMail, final String password) {
		final Nutzer nutzer = new NutzerDAO().getNutzer(eMail);
		final boolean validation = PassHash.validatePassword(password, nutzer.getPassword());
		if (validation == false) {
			return false;
		} else {
			setCurrentNutzer(nutzer);
			return true;
		}
	}

	public List<Jobangebot> sucheJobangebote(String name, String abschluss, String branche, int minMitarbeiter,
			int maxMitarbeiter, int minGehalt) {
		JobangebotDAO jobangebotDao = new JobangebotDAO();

		List<List<Jobangebot>> searchList = new LinkedList<>();
		searchList.add(jobangebotDao.searchForName(name));
		searchList.add(jobangebotDao.searchForAbschluss(abschluss, branche));
		searchList.add(jobangebotDao.searchForMitarbeiter(minMitarbeiter, maxMitarbeiter));
		searchList.add(jobangebotDao.searchForGehalt(minGehalt));

		HashMap<Integer, Integer> prioList = new HashMap<>();
		for (List<Jobangebot> sL : searchList) {
			prioList = prioritize(prioList, sL);
		}
		return null;
	}

	private HashMap<Integer, Integer> prioritize(HashMap<Integer, Integer> prioList, List<Jobangebot> searchList) {
		for (Jobangebot jobangebot : searchList) {
			int id = jobangebot.getJID();
			int prio;
			if (!prioList.containsKey(id)) {
				prio = 1;
			} else {
				prio = prioList.get(id) + 1;
			}
			prioList.put(id, prio);
		}
		return prioList;
	}

	private void setCurrentNutzer(final Nutzer aNutzer) {
		this.currentNutzer = aNutzer;
		notifyAllObservers(this.currentNutzer);
	}

	private void setCurrentFreelancer(final Freelancerprofil aFreelancerprofil) {
		this.currentFreelancer = aFreelancerprofil;
		notifyFreelancerObeserver(this.currentFreelancer);
	}

	private void setCurrentUnternehmensprofil(final Unternehmensprofil aUnternehmensprofil) {
		this.currentUnternehmen = aUnternehmensprofil;
		notifyUnternehmerObeserver(this.currentUnternehmen);
	}
}

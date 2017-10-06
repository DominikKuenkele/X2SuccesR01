package application;

import java.time.LocalDate;
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
public class Verwaltung {

	private Nutzer currentNutzer;
	private Unternehmensprofil currentUnternehmen;
	private Freelancerprofil currentFreelancer;

	/**
	 * @return the current {@link model.Nutzer}
	 */
	public Nutzer getCurrentNutzer() {
		return currentNutzer;
	}

	/**
	 * @return the current profile
	 */
	public Profil getCurrentProfil() {
		switch (currentNutzer.getStatus()) {
		case F:
			return currentFreelancer;
		case U:
			return currentUnternehmen;
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
	public void register(String fName, String lName, String sex, String plz, String city, String street, String number,
			LocalDate date, String eMail, String password) throws UserInputException {
		try {
			String passHash = PassHash.generateStrongPasswordHash(password);
			Nutzer nutzer = new Nutzer(fName, lName, sex, date, eMail, passHash, new Adresse(plz, city, street, number),
					Status.NONE);
			int nid = new NutzerDAO().addNutzer(nutzer);
			nutzer.setId(nid);
			currentNutzer = nutzer;
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
	public void createUnternehmen(String name, String form, String plz, String city, String street, String number,
			LocalDate founding, int employees, String description, String benefits, String branche, String website,
			String ceoFirstName, String ceoLastName) throws UserInputException {
		if (currentNutzer.getStatus() == Status.F) {
			throw new UserInputException("Nutzer ist schon Freelancer!");
		}
		try {
			Unternehmensprofil unternehmen = new Unternehmensprofil(name, form, new Adresse(plz, city, street, number),
					founding, employees, description, benefits, branche, website, ceoFirstName, ceoLastName,
					currentNutzer);
			UnternehmensprofilDAO unternehmensprofilDao = new UnternehmensprofilDAO();
			int uid = unternehmensprofilDao.addUnternehmensprofil(unternehmen);
			unternehmen.setId(uid);
			currentUnternehmen = unternehmen;
			currentNutzer.setStatus(Status.U);
		} catch (ValidateConstrArgsException e) {
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
	public void createFreelancer(String abschluss, String beschreibung, String[] skills, String lebenslauf,
			String benefits, List<String> sprachen) throws UserInputException {
		if (currentNutzer.getStatus() == Status.U) {
			throw new UserInputException("Nutzer ist schon Unternehmer!");
		}
		try {
			Freelancerprofil freelancer = new Freelancerprofil(abschluss, beschreibung, skills, lebenslauf, benefits,
					sprachen, currentNutzer);
			FreelancerprofilDAO freelancerprofilDao = new FreelancerprofilDAO();
			int fid = freelancerprofilDao.addFreelancerprofil(freelancer);
			freelancer.setId(fid);
			currentFreelancer = freelancer;
			currentNutzer.setStatus(Status.F);
		} catch (ValidateConstrArgsException e) {
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
	public void createJobangebot(String abschluss, List<String> sprachen, String beschreibung, LocalDate frist,
			int minGehalt, int maxGehalt, int wochenstunden) throws UserInputException {
		if (currentNutzer.getStatus() == Status.F) {
			throw new UserInputException("Ein Freelancer kann kein Jobangebot erstellen.");
		}
		try {
			Jobangebot jobangebot = new Jobangebot(abschluss, sprachen, beschreibung, frist, minGehalt, maxGehalt,
					wochenstunden, currentUnternehmen);
			int jid = new JobangebotDAO().addJobangebot(jobangebot);
			jobangebot.setId(jid);
		} catch (ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		}
	}

	/**
	 * @param eMail
	 * @param password
	 * @return if login was successful
	 */
	public boolean login(String eMail, String password) {
		Nutzer nutzer = new NutzerDAO().getNutzer(eMail);
		boolean validation = PassHash.validatePassword(password, nutzer.getPassword());
		if (validation == false) {
			return false;
		} else {
			currentNutzer = nutzer;
			return true;
		}
	}
}

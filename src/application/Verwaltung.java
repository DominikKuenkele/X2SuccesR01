package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
import util.exception.DBException;
import util.exception.DuplicateEntryException;
import util.exception.UserInputException;
import util.exception.ValidateConstrArgsException;

/**
 * @author domin
 *
 */
public class Verwaltung extends Subject {

	private Nutzer currentNutzer;
	private Unternehmensprofil currentUnternehmen;
	private Freelancerprofil currentFreelancer;

	private static Verwaltung instance;

	/**
	 * private Constructor prevents instantiation of this class
	 */
	private Verwaltung() {
	}

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
	 * @param status
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void register(final String fName, final String lName, final String sex, final String plz, final String city,
			final String street, final String number, final LocalDate date, final String eMail, final String password,
			Status status) throws UserInputException, DBException {
		try {
			final String passHash = PassHash.generateStrongPasswordHash(password);
			final Nutzer nutzer = new Nutzer(fName, lName, sex, date, eMail, passHash,
					new Adresse(plz, city, street, number), status);
			final int nid = new NutzerDAO().addNutzer(nutzer);
			nutzer.setNID(nid);
			setCurrentNutzer(nutzer);
		} catch (ValidateConstrArgsException | DuplicateEntryException e) {
			throw new UserInputException(e.getMessage());
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
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
	 * @param branche
	 * @param website
	 * @param ceoFirstName
	 * @param ceoLastName
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void createUnternehmen(final String name, final String form, final String plz, final String city,
			final String street, final String number, final LocalDate founding, final int employees,
			final String description, final String branche, final String website, final String ceoFirstName,
			final String ceoLastName) throws UserInputException, DBException {
		if (this.currentNutzer.getStatus() == Status.F) {
			throw new UserInputException("Nutzer ist schon Freelancer!");
		}
		try {
			final Unternehmensprofil unternehmen = new Unternehmensprofil(name, form,
					new Adresse(plz, city, street, number), founding, employees, description, branche, website,
					ceoFirstName, ceoLastName, this.currentNutzer);
			final UnternehmensprofilDAO unternehmensprofilDao = new UnternehmensprofilDAO();
			final int uid = unternehmensprofilDao.addUnternehmensprofil(unternehmen);
			unternehmen.setId(uid);
			setCurrentUnternehmensprofil(unternehmen);
			this.currentNutzer.setStatus(Status.U);
			new NutzerDAO().changeNutzer(currentNutzer);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
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
	 * @param branche
	 * @param website
	 * @param ceoFirstName
	 * @param ceoLastName
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void changeUnternehmen(final String name, final String form, final String plz, final String city,
			final String street, final String number, final LocalDate founding, final int employees,
			final String description, final String branche, final String website, final String ceoFirstName,
			final String ceoLastName) throws UserInputException, DBException {
		try {
			final Unternehmensprofil unternehmen = new Unternehmensprofil(name, form,
					new Adresse(plz, city, street, number), founding, employees, description, branche, website,
					ceoFirstName, ceoLastName, this.currentNutzer);
			unternehmen.setId(this.currentUnternehmen.getId());
			new UnternehmensprofilDAO().changeUnternehmen(unternehmen);
			setCurrentUnternehmensprofil(unternehmen);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!\n"
							+ e.getMessage());
		}
	}

	/**
	 * @param abschluss
	 * @param expertise
	 * @param beschreibung
	 * @param skills
	 * @param lebenslauf
	 * @param sprachen
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void changeFreelancer(final String abschluss, final String expertise, final String beschreibung,
			final String[] skills, final String lebenslauf, final List<String> sprachen)
			throws UserInputException, DBException {
		try {
			final Freelancerprofil freelancerprofil = new Freelancerprofil(abschluss, expertise, beschreibung, skills,
					lebenslauf, sprachen, this.currentNutzer);
			freelancerprofil.setId(this.currentFreelancer.getFID());
			new FreelancerprofilDAO().changeFreelancerprofil(freelancerprofil);
			setCurrentFreelancer(freelancerprofil);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
		}
	}

	/**
	 * @param abschluss
	 * @param expertise
	 * @param beschreibung
	 * @param skills
	 * @param lebenslauf
	 * @param sprachen
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void createFreelancer(final String abschluss, final String expertise, final String beschreibung,
			final String[] skills, final String lebenslauf, final List<String> sprachen)
			throws UserInputException, DBException {
		if (this.currentNutzer.getStatus() == Status.U) {
			throw new UserInputException("Nutzer ist schon Unternehmer!");
		}

		try {
			Freelancerprofil f = new FreelancerprofilDAO().getFreelancerprofilByNutzer(this.currentNutzer.getNID());
			if (f != null) {
				throw new UserInputException("Nutzer hat schon ein Freelancerprofil!");
			}
		} catch (SQLException e1) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
		}

		try {
			final Freelancerprofil freelancer = new Freelancerprofil(abschluss, expertise, beschreibung, skills,
					lebenslauf, sprachen, this.currentNutzer);
			final FreelancerprofilDAO freelancerprofilDao = new FreelancerprofilDAO();
			final int fid = freelancerprofilDao.addFreelancerprofil(freelancer);
			freelancer.setId(fid);
			setCurrentFreelancer(freelancer);
			this.currentNutzer.setStatus(Status.F);
			new NutzerDAO().changeNutzer(currentNutzer);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
		}
	}

	/**
	 * @param abschluss
	 * @param expertise
	 * @param sprachen
	 * @param jobTitel
	 * @param beschreibung
	 * @param frist
	 * @param gehalt
	 * @param wochenstunden
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void createJobangebot(final String abschluss, final String expertise, final List<String> sprachen,
			final String jobTitel, final String beschreibung, final LocalDate frist, final int gehalt,
			final int wochenstunden) throws UserInputException, DBException {
		if (this.currentNutzer.getStatus() == Status.F) {
			throw new UserInputException("Ein Freelancer kann kein Jobangebot erstellen.");
		}
		try {
			final Jobangebot jobangebot = new Jobangebot(abschluss, expertise, sprachen, jobTitel, beschreibung, frist,
					gehalt, wochenstunden, this.currentUnternehmen);
			final int jid = new JobangebotDAO().addJobangebot(jobangebot);
			jobangebot.setId(jid);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
		}
	}

	/**
	 * @param fName
	 * @param lName
	 * @param eMail
	 * @param sex
	 * @param plz
	 * @param city
	 * @param street
	 * @param number
	 * @param date
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void changeNutzer(final String fName, final String lName, final String eMail, final String sex,
			final String plz, final String city, final String street, final String number, final LocalDate date)
			throws UserInputException, DBException {
		try {
			final Nutzer nutzer = new Nutzer(fName, lName, sex, date, eMail, this.currentNutzer.getPassword(),
					new Adresse(plz, city, street, number), this.currentNutzer.getStatus());
			nutzer.setNID(this.currentNutzer.getNID());
			new NutzerDAO().changeNutzer(nutzer);
			setCurrentNutzer(nutzer);
		} catch (final ValidateConstrArgsException e) {
			throw new UserInputException(e.getMessage());
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
		}
	}

	/**
	 * @param oldPassword
	 * @param newPassword
	 * @throws UserInputException
	 * @throws DBException
	 */
	public void changePassword(String oldPassword, String newPassword) throws UserInputException, DBException {
		final boolean validation = PassHash.validatePassword(oldPassword, currentNutzer.getPassword());

		if (validation == true) {
			try {
				final String passHash = PassHash.generateStrongPasswordHash(newPassword);
				Nutzer nutzer = new Nutzer(currentNutzer.getFirstName(), currentNutzer.getLastName(),
						currentNutzer.getSex(), currentNutzer.getBirthdate(), currentNutzer.geteMail(), passHash,
						currentNutzer.getAddress(), currentNutzer.getStatus());
				nutzer.setNID(currentNutzer.getNID());
				new NutzerDAO().changeNutzer(nutzer);
				setCurrentNutzer(nutzer);
			} catch (ValidateConstrArgsException e) {
				throw new UserInputException("Geben Sie ein neues Passwort ein!", e);
			} catch (SQLException e) {
				throw new DBException(
						"Auf die Datenbank kann im Moment nicht zugegriffen werden. Probieren Sie es später erneut!",
						e);
			}
		} else {
			throw new UserInputException("Das alte Passwort stimmt nicht");
		}
	}

	/**
	 * @param eMail
	 * @param password
	 * @return true, if login was successful
	 * @throws DBException
	 */
	public boolean login(final String eMail, final String password) throws DBException {
		boolean result = false;
		try {
			Nutzer nutzer = new NutzerDAO().getNutzer(eMail);
			final boolean validation = PassHash.validatePassword(password, nutzer.getPassword());

			if (validation == true) {
				setCurrentNutzer(nutzer);
				switch (nutzer.getStatus()) {
				case F:
					Freelancerprofil f = new FreelancerprofilDAO().getFreelancerprofilByNutzer(nutzer.getNID());
					setCurrentFreelancer(f);
					break;
				case U:
					Unternehmensprofil u = new UnternehmensprofilDAO().getUnternehmensprofilByNutzer(nutzer.getNID());
					setCurrentUnternehmensprofil(u);
					break;
				default:
					// do nothing
				}
				result = true;
			}
		} catch (SQLException e) {
			throw new DBException(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut!");
		}
		return result;
	}

	public void logout() {
		instance = null;
	}

	/**
	 * @param name
	 * @param abschluss
	 * @param expertise
	 * @param branche
	 * @param minMitarbeiter
	 * @param maxMitarbeiter
	 * @param minGehalt
	 * @return a List of {@link model.Jobangebot Jobangebote} with search-Priority
	 * @throws SQLException
	 */
	public List<Entry<Jobangebot, Integer>> sucheJobangebote(String name, String abschluss, String expertise,
			String branche, int minMitarbeiter, int maxMitarbeiter, int minGehalt) throws SQLException {
		JobangebotDAO jobangebotDao = new JobangebotDAO();

		List<List<Jobangebot>> searchList = new LinkedList<>();
		searchList.add(jobangebotDao.searchForName(name));
		searchList.add(jobangebotDao.searchForAbschluss(abschluss, expertise));
		searchList.add(jobangebotDao.searchForBranche(branche));
		searchList.add(jobangebotDao.searchForMitarbeiter(minMitarbeiter, maxMitarbeiter));
		searchList.add(jobangebotDao.searchForGehalt(minGehalt));

		Set<Entry<Jobangebot, Integer>> prioList = prioritize(searchList);
		List<Map.Entry<Jobangebot, Integer>> list = new LinkedList<>(prioList);
		Collections.sort(list, new Comparator<Map.Entry<Jobangebot, Integer>>() {
			@Override
			public int compare(Map.Entry<Jobangebot, Integer> e1, Map.Entry<Jobangebot, Integer> e2) {
				return (e2.getValue()).compareTo(e1.getValue());
			}
		});

		return list;
	}

	private Set<Entry<Jobangebot, Integer>> prioritize(List<List<Jobangebot>> searchList) {
		HashMap<Jobangebot, Integer> prioList = new HashMap<>();
		for (List<Jobangebot> sL : searchList) {
			for (Jobangebot jobangebot : sL) {
				int prio;
				if (!prioList.containsKey(jobangebot)) {
					prio = 1;
				} else {
					prio = prioList.get(jobangebot) + 1;
				}
				prioList.put(jobangebot, prio);
			}
		}
		return prioList.entrySet();
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

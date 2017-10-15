package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.format.DateTimeFormatter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Jobangebot;
import model.Unternehmensprofil;

public class ViewFJobangebot {

	@FXML
	private Label companyname;

	@FXML
	private ImageView companypicture;

	@FXML
	private Label date;

	@FXML
	private Label employees;

	@FXML
	private Label branche;

	@FXML
	private Label Jobtitel;

	@FXML
	private TextArea jobdescription;

	@FXML
	private Label worktime;

	@FXML
	private Label salary;

	@FXML
	private Label degree;

	@FXML
	private Label topicofdegree;

	@FXML
	private Label contactname;

	@FXML
	private Label contactmail;

	@FXML
	private ImageView star;

	private Jobangebot jobangebot;

	// @FXML
	// void addfavorite(MouseEvent event) {
	//
	// // Pfad ändern ist glaub falsch?!
	// if (star.getOpacity() == 1) {
	// star.setImage(new Image("url=@Icons/stern_voll.png"));
	// star.setOpacity(0.99);
	// // Favorit speichern
	//
	// } else {
	// star.setImage(new Image("url=@Icons/stern_leer.png"));
	// star.setOpacity(1);
	// // Favorit löschen
	// }
	//
	// }

	@FXML
	void mailTo(MouseEvent event) throws URISyntaxException, IOException {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.MAIL)) {
				URI mailto = new URI("mailto:" + jobangebot.getUnternehmensprofil().getNutzer().geteMail());
				desktop.mail(mailto);
			}
		}
	}

	public void fillFormular() {
		Unternehmensprofil u = jobangebot.getUnternehmensprofil();

		companyname.setText(u.getName());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LLLL.yyyy");
		String formattedDate = u.getFounding().format(formatter);
		date.setText("Gründungsdatum: " + formattedDate);
		employees.setText("Mitarbeiteranzahl: " + u.getEmployees());
		branche.setText("Branche: " + jobangebot.getFachgebiet());
		Jobtitel.setText(jobangebot.getJobTitel());
		jobdescription.setText(jobangebot.getBeschreibung());
		salary.setText(Integer.toString(jobangebot.getGehalt()) + " EURO/Monat");
		worktime.setText("Wochenstunden: " + Integer.toString(jobangebot.getWochenstunden()));
		degree.setText("Benötigter Abschluss: " + jobangebot.getAbschluss() + " in " + jobangebot.getFachgebiet());
		contactname.setText(u.getCeoFirstName() + " " + u.getCeoLastName());
		contactmail.setText(u.getNutzer().geteMail());
	}

	public void setJobangebot(Jobangebot aJobangebot) {
		this.jobangebot = aJobangebot;
		fillFormular();
	}
}

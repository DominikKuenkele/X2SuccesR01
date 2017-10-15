package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Verwaltung;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Freelancerprofil;
import model.Jobangebot;
import model.Nutzer;
import model.Unternehmensprofil;

public class ViewUFreelancerprofil implements Initializable {

	@FXML
	private Label username;

	@FXML
	private ImageView Freelancerpicture;

	@FXML
	private TextArea skills;

	@FXML
	private Label contactphone;

	@FXML
	private Label contactmail;

	@FXML
	private ImageView star;

	@FXML
	private TextArea description;

	@FXML
	private Label degree;

	@FXML
	private Label languages;

	@FXML
	void addfavorite(MouseEvent event) {

		// Pfad ändern ist glaub falsch?!
		if (star.getOpacity() == 1) {
			star.setImage(new Image("url=@Icons/stern_voll.png"));
			star.setOpacity(0.99);
			// Favorit speichern

		} else {
			star.setImage(new Image("url=@Icons/stern_leer.png"));
			star.setOpacity(1);
			// Favorit löschen
		}
	}

	@FXML
	void mailTo(MouseEvent event) throws URISyntaxException, IOException {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.MAIL)) {
				String mail = contactmail.getText();
				URI mailto = new URI("mailto:john@example.com?subject=Hello%20World");
				desktop.mail(mailto);
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		Verwaltung v = Verwaltung.getInstance();
		Nutzer n = v.getCurrentNutzer();  //Nutzer nach ID raussuchen nicht den aktuellen!!!!
		Freelancerprofil f = (Freelancerprofil) v.getCurrentProfil(); //Auch nach ID raussuchen
		
		username.setText(n.getFirstName()+n.getLastName());
		description.setText(f.getBeschreibung());
		degree.setText(f.getAbschluss()+"in "+f.getAbschluss()); //Fachrichtung statt Abschluss!!!!
		contactmail.setText(n.geteMail());
		languages.setText(f.getSprachen().toString());
	}

}

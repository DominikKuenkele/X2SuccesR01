package controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Jobangebot;
import model.Unternehmensprofil;
import util.exception.ValidateConstrArgsException;

public class ViewUJobangebot implements Initializable {

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
	private TextArea skills;

	@FXML
	private Label contactname;

	@FXML
	private Label contactphone;

	@FXML
	private Label contactmail;

	@FXML
	private ImageView star;


	@FXML
	void addfavorite(MouseEvent event) {

		// Pfad �ndern ist glaub falsch?!
		if (star.getOpacity()==1) {
			star.setImage(new Image("url=@Icons/stern_voll.png"));
			star.setOpacity(0.99);
			//Favorit speichern

		} else {
			star.setImage(new Image("url=@Icons/stern_leer.png"));
			star.setOpacity(1);
			//Favorit l�schen
		}

	}

	@FXML
	void mailTo(MouseEvent event) throws URISyntaxException, IOException {
		if (Desktop.isDesktopSupported()) {
    	    Desktop desktop = Desktop.getDesktop();
    	    if (desktop.isSupported(Desktop.Action.MAIL)) {
    	        URI mailto = new URI("mailto:john@example.com?subject=Hello%20World");
    	        desktop.mail(mailto);
    	    }
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		

			Jobangebot j = new Jobangebot(null, null, null, null, null, 0, 0, null);
			Unternehmensprofil u =j.getUnternehmensprofil();
		

		companyname.setText(u.getName());
		date.setText("Gr�ndungsdatum: " + u.getFounding());
		employees.setText("Mitarbeiteranzahl: " + u.getEmployees());
		branche.setText("Branche: " +j.getBranche());
		Jobtitel.setText("");
		jobdescription.setText(j.getBeschreibung());
		salary.setText(Integer.toString(j.getGehalt()));
		worktime.setText("Wochenstunden: "+Integer.toString(j.getWochenstunden()));
		degree.setText("Ben�tigter Abschluss: " +j.getAbschluss()+ " in " + "");
		skills.setText("");
		contactname.setText("");
		contactphone.setText("");
		contactmail.setText("");
	}

}

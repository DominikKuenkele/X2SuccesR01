package view2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class JobangebotController implements Initializable {

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

	void oeffnen() { // Beim öffnen von der Scene/ wie geht des?

		companypicture.setImage(new Image("file:C:/Users/Tim/Desktop/stern_voll.png"));
		companyname.setText("");
		date.setText("Gründungsdatum: " + "");
		employees.setText("Mitarbeiteranzahl: " + "");
		branche.setText("Branche: " + "");
		Jobtitel.setText("");
		jobdescription.setText("");
		worktime.setText("");
		degree.setText("Benötigter Abschluss: " + "" + " in " + "");
		skills.setText("");
		contactname.setText("");
		contactphone.setText("");
		contactmail.setText("");

	}

	@FXML
	void addfavorite(MouseEvent event) {

		// Pfad ändern und einspeichern/auslesen von db
		if (true) {
			star.setImage(new Image("file:C:/Users/Tim/Desktop/stern_voll.png"));

		} else {
			star.setImage(new Image("file:C:/Users/Tim/Desktop/stern_leer.png"));

		}

	}

	@FXML
	void mailTo(MouseEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}

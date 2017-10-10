package view3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Nutzer;

public class ViewSettings implements Initializable {

	// Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> GenderList = FXCollections.observableArrayList("Männlich", "Weiblich", "Anderes");

	@FXML
	private TextField newprenom;

	@FXML
	private TextField newname;

	@FXML
	private TextField city;

	@FXML
	private TextField street;

	@FXML
	private TextField plz;

	@FXML
	private TextField streetnumber;

	@FXML
	private Button changeUserDataButton;

	@FXML
	private TextField email;

	@FXML
	private PasswordField oldpassword;

	@FXML
	private PasswordField newpassword;

	@FXML
	private PasswordField newpassword2;

	@FXML
	private Button changepwbutton;

	@FXML
	private DatePicker birthdate;

	@FXML
	private ChoiceBox<String> newgender;

	@FXML
	void changePW(ActionEvent event) {
		if (newpassword.getText().equals(newpassword2.getText())) {
			// Abfragen ob PW stimmt und wenn ja ändern
		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Fehler");
			alert.setHeaderText(null);
			alert.setContentText("Altes Passwort falsch oder neues Passwort nicht identisch");

			alert.showAndWait();
		}

	}

	@FXML
	void changeUserData(ActionEvent event) {

		String newprenom1 = newprenom.getText();
		String newname1 = newname.getText();
		String city1 = city.getText();
		String street1 = street.getText();

		// int plz1=Integer.parseInt(plz.getText());
		// int streetnumber1=Integer.parseInt(streetnumber.getText());
		String email1 = email.getText();
		String gender = newgender.getValue();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Verwaltung v = Verwaltung.getInstance();
		Nutzer nutzer = v.getCurrentNutzer();// Daten ausfüllen
		if (nutzer != null) {
			newprenom.setText(nutzer.getFirstName());
			newname.setText(nutzer.getLastName());
			city.setText(nutzer.getAddress().getCity());
			street.setText(nutzer.getAddress().getStrasse());
			plz.setText(nutzer.getAddress().getPlz());
			streetnumber.setText(nutzer.getAddress().getNumber());
			email.setText(nutzer.geteMail());
			birthdate.setValue(nutzer.getBirthdate());

			newprenom.setText(nutzer.getFirstName());
			newgender.setValue("Geschlecht auswählen"); // Anfangswert
			newgender.setItems(GenderList); // Name der Liste
		}

	}

}

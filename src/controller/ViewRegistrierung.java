package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import application.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.exception.DBException;
import util.exception.UserInputException;

public class ViewRegistrierung implements Initializable {

	ObservableList<String> GenderList = FXCollections.observableArrayList("Männlich", "Weiblich", "Anderes");

	private Verwaltung verwaltung;

	@FXML
	private TextField UserVorname;

	@FXML
	private TextField UserNachname;

	@FXML
	private ChoiceBox<String> UserGeschlecht;

	@FXML
	private DatePicker UserDatum;

	@FXML
	private TextField UserStadt;

	@FXML
	private TextField UserStraße;

	@FXML
	private TextField UserNr;

	@FXML
	private TextField UserPlz;

	@FXML
	private TextField UserMail;

	@FXML
	private PasswordField UserPW;

	@FXML
	private PasswordField UserPW2;

	@FXML
	private Button addfreelancerb;

	@FXML
	private Button addcompanyb;

	void changescene(String fxmlname) throws IOException {

		// schliesst aktuelles Fenster
		Stage stage2 = (Stage) addfreelancerb.getScene().getWindow();
		stage2.close();

		Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource(fxmlname));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	private void NutzerAnlegen() {

		// pw muss gehasht werden

		// Höchste UserId aus DB holen
		// XY = USERID+1

		// TODO Email abfrage muss auch noch rein!
		if (UserPW.getText().equals(UserPW2.getText())) {
			String vorname = UserVorname.getText();
			String nachname = UserNachname.getText();
			String geschlecht = UserGeschlecht.getValue();
			String stadt = UserStadt.getText();
			String plz = UserPlz.getText();
			String strasse = UserStraße.getText();
			String hausnummer = UserNr.getText();
			// GEBURTSDATUM ALS STRING?!
			LocalDate localDate = UserDatum.getValue();
			String eMail = UserMail.getText();
			String passwort = ""; // TODO hash

			try {
				verwaltung.register(vorname, nachname, geschlecht, stadt, plz, strasse, hausnummer, localDate, eMail,
						passwort);
			} catch (UserInputException | DBException e) {
				// TODO VALIDIERUNG!
				e.printStackTrace();
			}
			// Nutzer XY =new Nutzer
			// (eMail,passwort,Vorname,Nachname,Geschlecht,Geburtsdatum);
		}
	}

	@FXML
	void addcompanyb(ActionEvent event) throws IOException {
		NutzerAnlegen();
		changescene("/view/UProfilErstellen.fxml");
	}

	@FXML
	void addfreelancer(ActionEvent event) throws IOException {
		NutzerAnlegen();
		changescene("/view/FProfilErstellen.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UserGeschlecht.setValue("Geschlecht auswählen"); // Anfangswert
		UserGeschlecht.setItems(GenderList); // Name der Liste
	}

}

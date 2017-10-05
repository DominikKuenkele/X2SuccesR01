package view;

import java.io.IOException;
import java.time.LocalDate;

import application.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.exception.UserInputException;

public class UserRegistrierungController {

	private Verwaltung verwaltung;

	@FXML
	private TextField UserVorname;

	@FXML
	private TextField UserNachname;

	@FXML
	private SplitMenuButton UserGeschlecht;

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
	private Button NeuerNutzer;

	@FXML
	void switchScene2() {
		try {
			Stage prevstage = (Stage) NeuerNutzer.getScene().getWindow();

			Stage stage = new Stage();
			stage.setTitle("Shop Management");
			Pane myPane = null;
			myPane = FXMLLoader.load(getClass().getResource("Unternehmensregistration.fxml"));
			Scene scene = new Scene(myPane);
			stage.setScene(scene);

			stage.show();
		    prevstage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void NutzerAnlegen(ActionEvent event) {

		// pw muss gehasht werden

		// Höchste UserId aus DB holen
		// XY = USERID+1

		// TODO Email abfrage muss auch noch rein!
		if (UserPW.getText().equals(UserPW2.getText())) {
			String vorname = UserVorname.getText();
			String nachname = UserNachname.getText();
			String geschlecht = UserGeschlecht.getText();
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
			} catch (UserInputException e) {
				// TODO VALIDIERUNG!
				e.printStackTrace();
			}
			// Nutzer XY =new Nutzer
			// (eMail,passwort,Vorname,Nachname,Geschlecht,Geburtsdatum);
		}
	}

}

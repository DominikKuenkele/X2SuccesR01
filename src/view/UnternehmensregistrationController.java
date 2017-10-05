package view;

import java.io.IOException;
import java.time.LocalDate;

import application.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.exception.UserInputException;

public class UnternehmensregistrationController {

	private Verwaltung verwaltung;

	boolean v = true;

	public void validieren(String name) { // Prüfen ob Mussfelder Einträge enthalten, Plz, Hausnummer etc prüfen?!
		if (name.equals(""))
			v = false;
		if (Ubranche.equals(""))
			v = false;
	}

	@FXML
	private TextField UName;

	@FXML
	private TextField UForm;

	@FXML
	private TextField UStadt;

	@FXML
	private TextField UPlz;

	@FXML
	private TextField UStraße;

	@FXML
	private TextField UNr;

	@FXML
	private DatePicker UDatum;

	@FXML
	private TextField UMitarbeiter;

	private ComboBox<?> Ubranche;
	// ObservableList<String> options =
	// FXCollections.observableArrayList(
	// "Option 1",
	// "Option 2",
	// "Option 3"
	// );
	// @FXML
	// final ComboBox Ubranche =new ComboBox(options);
	// in xml datei wie in Html schreiben?!

	@FXML
	private TextArea UBeschreibung;

	@FXML
	private Button UAnlegenButton;

	@FXML
	void switchScene() {
		try {
			Stage prevStage = (Stage) UAnlegenButton.getScene().getWindow();
			Stage stage = new Stage();
			stage.setTitle("Shop Management");
			Pane myPane = null;
			myPane = FXMLLoader.load(getClass().getResource("UserRegistrierung.fxml"));
			Scene scene = new Scene(myPane);
			stage.setScene(scene);

			prevStage.close();

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void Unternehmenanlegen(ActionEvent event) {

		String name = UName.getText();
		String form = UForm.getText();
		String stadt = UStadt.getText();
		String plz = UPlz.getText();
		String strasse = UStraße.getText();
		String hausnummer = UNr.getText();
		LocalDate gruendung = UDatum.getValue();
		int mitarbeiter = Integer.parseInt(UMitarbeiter.getText());
		String beschreibung = UBeschreibung.getText();
		String branche = Ubranche.getSelectionModel().getSelectedItem().toString();
		validieren(name); // TODO Inhalt prüfen
		if (v = false) {
			Alert alert = new Alert(AlertType.ERROR); // Statt .Error geht auch .Warning etc
			alert.setTitle("Error"); // Fenstername
			alert.setHeaderText("Registrierung fehlgeschlagen");
			alert.setContentText("Bitte füllen sie alle Pflichtfelder (*) aus");

			alert.showAndWait();
		} else {
			try {
				verwaltung.createUnternehmen(name, form, plz, stadt, strasse, hausnummer, gruendung, mitarbeiter,
						beschreibung, "benefits", branche, "www.test.de", "Vorname", "Nachname");
			} catch (UserInputException e) {
				// TODO VALIDATION!
				e.printStackTrace();
			}

			// Höchste UnternehmensID aus DB holen
			// UID = UID+1;
			// Unternehmensprofil XY =new Unternehmensprofil(Name,Beschreibung,Branche,)
		}

	}

}

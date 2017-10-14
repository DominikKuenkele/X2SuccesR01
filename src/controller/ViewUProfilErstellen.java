package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import persistence.BrancheDAO;
import util.exception.DBException;
import util.exception.UserInputException;

public class ViewUProfilErstellen implements Initializable {

	ObservableList<String> Branchenliste = FXCollections.observableArrayList("Branche1", "Branche2", "Branche3");

	private Verwaltung verwaltung;

	boolean v = true;

	@FXML
	private TextField UName;

	@FXML
	private TextField UForm;

	@FXML
	private TextField UStadt;

	@FXML
	private TextField UPlz;

	@FXML
	private TextField UStraﬂe;

	@FXML
	private TextField UNr;

	@FXML
	private DatePicker UDatum;

	@FXML
	private TextField UBranche;

	@FXML
	private TextField UMitarbeiter;

	@FXML
	private TextArea UBeschreibung;

	@FXML
	private Button UAnlegenButton;

	@FXML
	void switchScene(String fxmlname) {
		try {
			Stage prevStage = (Stage) UAnlegenButton.getScene().getWindow();
			Stage stage = new Stage();
			stage.setTitle("X2Success");
			Pane myPane = null;
			myPane = FXMLLoader.load(getClass().getResource(fxmlname));
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
		String strasse = UStraﬂe.getText();
		String hausnummer = UNr.getText();
		String branche = UBranche.getText();
		LocalDate gruendung = UDatum.getValue();
		int mitarbeiter = Integer.parseInt(UMitarbeiter.getText());
		String beschreibung = UBeschreibung.getText();

		try {
			verwaltung.createUnternehmen(name, form, plz, stadt, strasse, hausnummer, gruendung, mitarbeiter,
					beschreibung, branche, "benefits", "www.test.de", "Vorname", "Nachname");
			switchScene("/view/URahmen.fxml");
		} catch (UserInputException | DBException e) {
			Alert alert = new Alert(AlertType.ERROR); // Statt .Error geht auch .Warning etc
			alert.setTitle("Error"); // Fenstername
			alert.setHeaderText("Registrierung fehlgeschlagen");
			alert.setContentText(e.getMessage());
			alert.showAndWait();

			e.printStackTrace();

		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ObservableList<String> graduation = FXCollections.observableArrayList(new BrancheDAO().);
			degree1.setValue(graduation.get(0)); // Anfangswert
			degree1.setItems(graduation); // Name der Liste

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

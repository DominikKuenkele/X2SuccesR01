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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Unternehmensprofil;
import persistence.BrancheDAO;
import util.exception.DBException;
import util.exception.UserInputException;

public class ViewUProfil implements Initializable {

	// Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> Branche = FXCollections.observableArrayList("Inhalt1", "Inhalt2", "Inhalt3");

	@FXML
	private TextField cName;

	@FXML
	private DatePicker cDate;

	@FXML
	private TextField cEmployees;

	@FXML
	private TextField cCity;

	@FXML
	private TextField cStreet;

	@FXML
	private TextField cPlz;

	@FXML
	private TextField cNumber;

	@FXML
	private ChoiceBox<String> cBranche;

	@FXML
	private TextArea cDescription;

	@FXML
	private Button changeCompany;

	void changeScene(String fxmlname) throws IOException {

		// schliesst aktuelles Fenster
		Stage stage2 = (Stage) changeCompany.getScene().getWindow();
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
	void changeUnternehmen(ActionEvent event) {
		Verwaltung verwaltung = Verwaltung.getInstance();

		String name = cName.getText();
		LocalDate founding = cDate.getValue();
		String city = cCity.getText();
		String plz = cPlz.getText();
		String street = cStreet.getText();
		String number = cNumber.getText();
		String branche = cBranche.getValue();
		String description = cDescription.getText();

		try {
			verwaltung.changeUnternehmen(name, "form", plz, city, street, number, founding, 80000, description, branche,
					"www.fff.de", "vorname", "nachname");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setHeaderText("Unternehmensprofil geändert");
			alert.setContentText("Das Unternehmensprofil wurde erfolgreich geändert!");
			alert.showAndWait();

		} catch (UserInputException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Änderung fehlgeschlagen");
			alert.setContentText(e.getMessage());
			alert.showAndWait();

			e.printStackTrace();
		} catch (DBException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Änderung fehlgeschlagen");
			alert.setContentText(
					"Auf die Datenbank kann im Moment nicht zugegriffen werden. Versuchen Sie es später erneut.");
			alert.showAndWait();

			e.printStackTrace();
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Verwaltung v = Verwaltung.getInstance();
		Unternehmensprofil u = (Unternehmensprofil) v.getCurrentProfil();

		try {
			ObservableList<String> branche = FXCollections.observableArrayList(new BrancheDAO().getAllBranchen());
			cBranche.setItems(branche);
			if (!u.getBranche().equals("")) {
				cBranche.setValue(u.getBranche());
			} else {
				cBranche.setValue(branche.get(0));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cName.setText(u.getName());
		cDate.setValue(u.getFounding());
		cEmployees.setText(Integer.toString(u.getEmployees()));
		cCity.setText(u.getAddress().getCity());
		cStreet.setText(u.getAddress().getStrasse());
		cPlz.setText(u.getAddress().getPlz());
		cNumber.setText(u.getAddress().getNumber());
		cDescription.setText(u.getDescription());
	}
}

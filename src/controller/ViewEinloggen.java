package controller;

import java.io.IOException;

import application.Verwaltung;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.exception.DBException;

public class ViewEinloggen {

	@FXML
	private TextField EmailEingabe;

	@FXML
	private PasswordField PasswortEingabe;

	@FXML
	private Button LoginButton;

	void changescene(final String fxmlname) throws IOException {

		// schliesst aktuelles Fenster
		final Stage stage2 = (Stage) this.LoginButton.getScene().getWindow();
		stage2.close();

		final Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource(fxmlname));
		final Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void Login(final ActionEvent event) throws IOException {
		Verwaltung v = Verwaltung.getInstance();
		String eMail = EmailEingabe.getText();
		String password = PasswortEingabe.getText();

		try {
			if (v.login(eMail, password)) {
				switch (v.getCurrentNutzer().getStatus()) {
				case F:
					changescene("/view/FRahmen.fxml");
					break;
				case U:
					changescene("/view/URahmen.fxml");
					break;
				default:

				}

			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Login fehlgeschlagen");
				alert.setContentText("E-Mail-Adresse oder Passwort sind falsch!");
				alert.showAndWait();
			}
		} catch (DBException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Login fehlgeschlagen");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

		// PW und Benutzernamen prüfen

	}

}

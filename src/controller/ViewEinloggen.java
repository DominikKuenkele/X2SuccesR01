package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

		// PW und Benutzernamen prüfen
		if (true) { // Wenn Benutzer Freelancer ist
				changescene("/view/FRahmen.fxml"); // Wenn Benutzer Favoriten hat
			
		} else {
				changescene("/view/URahmen.fxml");// Benutzer hat keine Favoriten
		}
	}


}

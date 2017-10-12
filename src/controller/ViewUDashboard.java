package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewUDashboard {

	@FXML
	private ImageView homebutton;

	void changescene(String fxmlname) throws IOException {

		// schliesst aktuelles Fenster
		Stage stage2 = (Stage) homebutton.getScene().getWindow();

		Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource(fxmlname));
		Scene scene = new Scene(myPane);
		stage2.setScene(scene);

	}

	@FXML
	void createnewoffer(MouseEvent event) throws IOException {
		changescene("UJobangebotErstellen.fxml");
	}

	@FXML
	void openSearch(MouseEvent event) throws IOException {
		changescene("USuche.fxml");

	}

}

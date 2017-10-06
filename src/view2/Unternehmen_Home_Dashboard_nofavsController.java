package view2;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Unternehmen_Home_Dashboard_nofavsController {

	@FXML
	private ImageView homebutton;

	@FXML
	private ImageView profilbutton;

	@FXML
	private ImageView searchbutton;

	@FXML
	private ImageView settingsbutton;

	@FXML
	private ImageView signoutbutton;

	@FXML
	private ImageView userpicture;

	@FXML
	private ImageView addofferbutton;

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
		changescene("Unternehmen_createoffer.fxml");
	}

	@FXML
	void openHome(MouseEvent event) throws IOException {

		// Abfrage ob es schon Favoriten gibt
		if (false)
			changescene("Unternehmen_Home_Dashboard_nofavs.fxml");
		else
			changescene("Unternehmen_Home_Dashboard_favs.fxml");

	}

	@FXML
	void openProfil(MouseEvent event) throws IOException {
		changescene("Unternehmen_Profil.fxml");
	}

	@FXML
	void openSearch(MouseEvent event) throws IOException {
		changescene("Unternehmen_Suche_Dashboard.fxml");

	}

	@FXML
	void openSettings(MouseEvent event) throws IOException {
		changescene("Unternehmen_Settings_Dashboard.fxml");

	}

	@FXML
	void openSignOut(MouseEvent event) throws IOException {
		changescene("Einloggen.fxml");
	}

}

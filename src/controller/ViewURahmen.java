package controller;

import java.io.IOException;

import application.UnternehmerObserver;
import application.Verwaltung;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Nutzer;
import model.Unternehmensprofil;

public class ViewURahmen implements UnternehmerObserver {

	@FXML
	private Label labelName;
	@FXML
	private AnchorPane pane;
	@FXML
	private Label titel;
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

	private void openStage(String datei) {
		try {
			Stage prevStage = (Stage) labelName.getScene().getWindow();
			prevStage.close();

			Stage stage = new Stage();
			stage.setTitle("X2Success");
			Pane myPane = null;
			myPane = FXMLLoader.load(getClass().getResource("/view/" + datei));
			Scene scene = new Scene(myPane);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void openSubScene(String datei, String name) {
		try {
			Pane myPane = FXMLLoader.load(getClass().getResource("/view/" + datei));
			pane.getChildren().clear();
			pane.getChildren().add(myPane);
			titel.setText(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		Verwaltung verwaltung = Verwaltung.getInstance();
		verwaltung.registerAsUnternehmer(this);
		Nutzer nutzer = verwaltung.getCurrentNutzer();
		labelName.setText(nutzer.getFirstName() + " " + nutzer.getLastName());
		openSubScene("UDashboard.fxml", "Dashboard");
	}

	// Event Listener on ImageView[#homebutton].onMouseClicked
	@FXML
	public void openHome(MouseEvent event) {
		openSubScene("UDashboard.fxml", "Dashboard");
	}

	// Event Listener on ImageView[#profilbutton].onMouseClicked
	@FXML
	public void openProfil(MouseEvent event) {
		openSubScene("UProfil.fxml", "Profil bearbeiten");
	}

	// Event Listener on ImageView[#searchbutton].onMouseClicked
	@FXML
	public void openSearch(MouseEvent event) {
		openSubScene("USuche.fxml", "Suche");
	}

	// Event Listener on ImageView[#settingsbutton].onMouseClicked
	@FXML
	public void openSettings(MouseEvent event) {
		openSubScene("Settings.fxml", "Einstellungen");
	}

	// Event Listener on ImageView[#signoutbutton].onMouseClicked
	@FXML
	public void openSignOut(MouseEvent event) {
		Verwaltung verwaltung = Verwaltung.getInstance();
		verwaltung.logout();
		openStage("Startseite.fxml");
	}

	// Event Listener on ImageView[#addofferbutton].onMouseClicked
	@FXML
	public void createnewoffer(MouseEvent event) {
		openSubScene("UJobangebotErstellen.fxml", "Jobangebot erstellen");
	}

	@Override
	public void updateUnternehmer(Unternehmensprofil aUnternehmer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNutzer(Nutzer aNutzer) {
		labelName.setText(aNutzer.getFirstName() + " " + aNutzer.getLastName());
	}

}

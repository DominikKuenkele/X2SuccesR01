package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
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
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Freelancerprofil;
import persistence.AbschlussDAO;
import persistence.ExpertiseDAO;
import persistence.SpracheDAO;
import util.exception.DBException;
import util.exception.UserInputException;

public class ViewFProfil implements Initializable {

	@FXML
	private ImageView homebutton;

	@FXML
	private ImageView userpicture;

	@FXML
	private ChoiceBox<String> degree1;

	@FXML
	private ChoiceBox<String> topic1;

	@FXML
	private TextArea cv;

	@FXML
	private ChoiceBox<String> language1;

	@FXML
	private ChoiceBox<String> language2;

	@FXML
	private ChoiceBox<String> language3;

	@FXML
	private ChoiceBox<String> language4;

	@FXML
	private TextArea tAskills;

	@FXML
	private TextArea selfDescription;

	@FXML
	private Button changefreelancerbutton;

	@FXML
	private Button showfreelancerbutton;

	@FXML
	void changefreelancer(ActionEvent event) { // Änderungen übernehmen
		Verwaltung verwaltung = Verwaltung.getInstance();

		String abschluss = degree1.getValue();
		String expertise = topic1.getValue();
		String beschreibung = selfDescription.getText();
		String[] skills = tAskills.getText().split("\n");
		String lebenslauf = cv.getText();
		List<String> sprachenTemp = new LinkedList<>();
		if (!language1.getValue().equals("")) {
			sprachenTemp.add(language1.getValue());
		}
		if (!language2.getValue().equals("")) {
			sprachenTemp.add(language2.getValue());
		}
		if (!language3.getValue().equals("")) {
			sprachenTemp.add(language3.getValue());
		}
		if (!language4.getValue().equals("")) {
			sprachenTemp.add(language4.getValue());
		}

		List<String> sprachen = new LinkedList<>();
		for (String sprache : sprachenTemp) {
			if (!sprachen.contains(sprache)) {
				sprachen.add(sprache);
			}
		}

		try {
			verwaltung.changeFreelancer(abschluss, expertise, beschreibung, skills, lebenslauf, sprachen);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Info");
			alert.setHeaderText("Freelancerprofil geändert");
			alert.setContentText("Das Freelancerprofil wurde erfolgreich geändert!");
			alert.showAndWait();

		} catch (UserInputException | DBException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Registrierung fehlgeschlagen");
			alert.setContentText(e.getMessage());
			alert.showAndWait();

			e.printStackTrace();
		}
	}

	void changescene(String fxmlname) throws IOException {
		Stage stage2 = (Stage) homebutton.getScene().getWindow();
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
	void showfreelancer(ActionEvent event) throws IOException {
		changescene("/view/Freelancerprofil.fxml");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Verwaltung v = Verwaltung.getInstance();
		Freelancerprofil f = (Freelancerprofil) v.getCurrentProfil();

		try {
			ObservableList<String> sprachen = FXCollections.observableArrayList(new SpracheDAO().getAllSprachen());
			sprachen.add(0, "");
			List<String> sprachenFP = f.getSprachen();

			language1.setItems((ObservableList<String>) sprachen);
			if (sprachenFP.size() > 0) {
				language1.setValue(sprachenFP.get(0));
			} else {
				language1.setValue(sprachen.get(0));
			}

			language2.setItems((ObservableList<String>) sprachen);
			if (sprachenFP.size() > 1) {
				language2.setValue(sprachenFP.get(1));
			} else {
				language2.setValue(sprachen.get(0));
			}

			language3.setItems((ObservableList<String>) sprachen);
			if (sprachenFP.size() > 2) {
				language3.setValue(sprachenFP.get(2));
			} else {
				language3.setValue(sprachen.get(0));
			}

			language4.setItems((ObservableList<String>) sprachen);
			if (sprachenFP.size() > 3) {
				language4.setValue(sprachenFP.get(3));
			} else {
				language4.setValue(sprachen.get(0));
			}

			ObservableList<String> expertises = FXCollections
					.observableArrayList(new ExpertiseDAO().getAllExpertises());
			topic1.setItems(expertises);
			if (!f.getFachgebiet().equals("")) {
				topic1.setValue(f.getFachgebiet());
			} else {
				topic1.setValue(expertises.get(0));
			}

			ObservableList<String> graduation = FXCollections.observableArrayList(new AbschlussDAO().getAllAbschluss());
			degree1.setItems(graduation);
			if (!f.getAbschluss().equals("")) {
				degree1.setValue(f.getAbschluss());
			} else {
				degree1.setValue(graduation.get(0));
			}

			cv.setText(f.getLebenslauf());

			String skillsArray[] = f.getSkills();
			for (String skill : skillsArray) {
				tAskills.setText(skill + "\n");
			}
			selfDescription.setText(f.getBeschreibung());

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

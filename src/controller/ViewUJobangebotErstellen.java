package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import persistence.AbschlussDAO;
import persistence.ExpertiseDAO;
import util.exception.DBException;
import util.exception.UserInputException;

public class ViewUJobangebotErstellen implements Initializable {

	@FXML
	private ImageView signoutbutton;

	@FXML
	private ImageView addofferbutton;

	@FXML
	private TextField jobtitel;

	@FXML
	private TextArea jobdescription;

	@FXML
	private TextField worktime;

	@FXML
	private TextField salary;

	@FXML
	private ChoiceBox<String> necessarydegree;

	@FXML
	private ChoiceBox<String> topic;

	@FXML
	private Button createofferb;

	@FXML
	private Button seeofferb;

	@FXML
	private TextField contactname;

	@FXML
	private TextField contactemail;

	@FXML
	private TextField contactphone;

	@FXML
	void createoffer(MouseEvent event) {
		Verwaltung verwaltung = Verwaltung.getInstance();

		try {

			String jobTitle = jobtitel.getText();
			String description = jobdescription.getText();
			int weeklyworktime;
			int monthlysalary;
			weeklyworktime = Integer.parseInt(worktime.getText());
			monthlysalary = Integer.parseInt(salary.getText());
			String degree = necessarydegree.getValue();
			String topic1 = topic.getValue();
			String cname = contactname.getText();
			String cmail = contactemail.getText();

			try {
				verwaltung.createJobangebot(degree, topic1, new LinkedList<String>(), jobTitle, description,
						LocalDate.of(1050, 12, 31), monthlysalary, weeklyworktime);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Info");
				alert.setHeaderText("Jobangebot erstellt");
				alert.setContentText("Das Jobangebot wurde erfolgreich erstellt!");
				alert.showAndWait();
			} catch (UserInputException | DBException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Registrierung fehlgeschlagen");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Ung�ltige Eingabe");
			alert.setContentText("Das ist keine Zahl!");
			alert.showAndWait();
		}
	}

	void changescene(String fxmlname) throws IOException {

		// schliesst aktuelles Fenster
		Stage stage2 = (Stage) seeofferb.getScene().getWindow();
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
	void createnewoffer(MouseEvent event) throws IOException {
		changescene("UJobangebotErstellen.fxml");
	}

	@FXML
	void seeoffer(ActionEvent event) throws IOException {

		Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("Jobangebot.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			ObservableList<String> expertises = FXCollections
					.observableArrayList(new ExpertiseDAO().getAllExpertises());
			topic.setItems(expertises);
			topic.setValue(expertises.get(0));

			ObservableList<String> graduation = FXCollections.observableArrayList(new AbschlussDAO().getAllAbschluss());
			necessarydegree.setItems(graduation);
			necessarydegree.setValue(graduation.get(0));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

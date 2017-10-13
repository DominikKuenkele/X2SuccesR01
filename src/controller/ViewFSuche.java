package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import application.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Jobangebot;
import view.JobangebotAnzeige;

public class ViewFSuche implements Initializable {

	// Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> TestListe = FXCollections.observableArrayList("Inhalt1", "Inhalt2", "Inhalt3");

	@FXML
	private TextField searchcompanyname;

	@FXML
	private ChoiceBox<String> searchnecessarydegree;

	@FXML
	private ChoiceBox<String> searchExpertise;

	@FXML
	private ChoiceBox<String> searchbranche;

	@FXML
	private AnchorPane result1;

	@FXML
	private AnchorPane result4;

	@FXML
	private AnchorPane result3;

	@FXML
	private AnchorPane result6;

	@FXML
	private AnchorPane result2;

	@FXML
	private AnchorPane result5;

	@FXML
	private TextField minimumemployees;

	@FXML
	private Slider salarySlider;

	@FXML
	private TextField Salary;

	@FXML
	private TextField maximumemployees;

	@FXML
	private Button serachoffers;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	void changeslider(KeyEvent event) {

		salarySlider.setValue(Double.parseDouble(Salary.getText()));
	}

	@FXML
	void dragdone(MouseEvent event) {
		Salary.setText(Double.toString(Math.round(salarySlider.getValue())));

	}

	void changescene(String fxmlname) throws IOException {

		// schliesst aktuelles Fenster
		Stage stage2 = (Stage) serachoffers.getScene().getWindow();
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
	void searchoffers(ActionEvent event) throws IOException {

		String cName = searchcompanyname.getText();
		int minEmployees = Integer.parseInt(minimumemployees.getText()); // Inhalt prüfen
		int maxEmployees = Integer.parseInt(maximumemployees.getText()); // Inhalt prüfen
		String expertise = searchExpertise.getValue();
		String branche = searchbranche.getValue();
		String graduation = searchnecessarydegree.getValue();
		int salary = Integer.parseInt(Salary.getText());
		//
		// result1.setVisible(true); // Je nachdem sichtbar machen

		Verwaltung v = Verwaltung.getInstance();

		List<Entry<Jobangebot, Integer>> searchList;
		try {
			searchList = v.sucheJobangebote(cName, graduation, expertise, branche, minEmployees, maxEmployees, salary);
			JobangebotAnzeige[] jA = new JobangebotAnzeige[searchList.size()];

			GridPane searchGrid = new GridPane();

			int index = 0;
			for (Entry<Jobangebot, Integer> entry : searchList) {
				jA[index] = new JobangebotAnzeige();
				jA[index].setJobangebot(entry.getKey());
				jA[index].setOnMouseClicked(jAoeffnen());
				searchGrid.add(jA[index], index % 3, index / 3);
				index++;
			}

			scrollPane.setContent(searchGrid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private EventHandler<? super MouseEvent> jAoeffnen() throws IOException {
		Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = FXMLLoader.load(getClass().getResource("/view/UJobangebot.fxml"));

		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();

		return null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchnecessarydegree.setValue("Inhalt1"); // Anfangswert
		searchnecessarydegree.setItems(TestListe); // Name der Liste
		searchExpertise.setValue("Inhalt1"); // Anfangswert
		searchExpertise.setItems(TestListe); // Name der Liste
		searchbranche.setValue("Inhalt1"); // Anfangswert
		searchbranche.setItems(TestListe); // Name der Liste

	}

}

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import application.Verwaltung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
	private ChoiceBox<String> searchtopic;

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
	void searchoffers(ActionEvent event) {

		// String cname = searchcompanyname.getText();
		// int min = Integer.parseInt(minimumemployees.getText()); // Inhalt prüfen
		// int max = Integer.parseInt(maximumemployees.getText()); // Inhalt prüfen
		// String branche = searchbranche.getValue();
		// String degree = searchnecessarydegree.getValue();
		// int salary1 = Integer.parseInt(Salary.getText());
		//
		// result1.setVisible(true); // Je nachdem sichtbar machen

		Verwaltung v = Verwaltung.getInstance();
		Set<Entry<Jobangebot, Integer>> searchList = v.sucheJobangebote("", "", "", 1, 2, 5);

		JobangebotAnzeige[] jA = new JobangebotAnzeige[searchList.size()];
		int r = 0;
		int c = 0;
		GridPane searchGrid = new GridPane();

		for (int i = 0; i < searchList.size(); i++) {

			jA[i] = new JobangebotAnzeige();
			jA[i].setGehalt("4");
			searchGrid.add(jA[i], i % 3, i / 3);

		}
		scrollPane.setContent(searchGrid);

	}

	void openoffer() throws IOException {

		Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("Jobangebot.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();

	}

	@FXML
	void showresult1(MouseEvent event) throws IOException {

		openoffer();
		// ID muss noch mitgegeben werden

	}

	@FXML
	void showresult2(MouseEvent event) throws IOException {
		openoffer();
		// ID muss noch mitgegeben werden

	}

	@FXML
	void showresult3(MouseEvent event) throws IOException {
		openoffer();
		// ID muss noch mitgegeben werden

	}

	@FXML
	void showresult4(MouseEvent event) throws IOException {
		openoffer();
		// ID muss noch mitgegeben werden

	}

	@FXML
	void showresult5(MouseEvent event) throws IOException {
		openoffer();
		// ID muss noch mitgegeben werden

	}

	@FXML
	void showresult6(MouseEvent event) throws IOException {
		openoffer();
		// ID muss noch mitgegeben werden

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchnecessarydegree.setValue("Inhalt1"); // Anfangswert
		searchnecessarydegree.setItems(TestListe); // Name der Liste
		searchtopic.setValue("Inhalt1"); // Anfangswert
		searchtopic.setItems(TestListe); // Name der Liste
		searchbranche.setValue("Inhalt1"); // Anfangswert
		searchbranche.setItems(TestListe); // Name der Liste

	}

}

package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Map.Entry;

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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Jobangebot;
import view.JobangebotAnzeige;

public class ViewUSuche implements Initializable{
	
	// Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> TestListe = FXCollections.observableArrayList("Inhalt1", "Inhalt2", "Inhalt3");


    @FXML
    private TextField searchfreelancername;

    @FXML
    private ChoiceBox<String> searchdegree;

    @FXML
    private ChoiceBox<String> searchtopic;

    @FXML
    private Button serachoffers;

    @FXML
    private ChoiceBox<String> searchlanguage1;

    @FXML
    private ChoiceBox<String> searchlanguage2;

    @FXML
    private ChoiceBox<String> searchlanguage3;

    @FXML
    private ChoiceBox<String> searchlanguage4;
    
	@FXML
	private ScrollPane scrollPane;

    @FXML
    void searchoffers(ActionEvent event) throws IOException {


		Verwaltung v = Verwaltung.getInstance();
		Set<Entry<Jobangebot, Integer>> searchList = v.sucheJobangebote("", "", "", 1, 2, 5);

		JobangebotAnzeige[] jA = new JobangebotAnzeige[searchList.size()];
		int r = 0;
		int c = 0;
		GridPane searchGrid = new GridPane();

		for (int i = 0; i < searchList.size(); i++) {
			
			jA[i].setOnMouseClicked(jAoeffnen());

			jA[i] = new JobangebotAnzeige();
			jA[i].setGehalt("4");
			searchGrid.add(jA[i], i % 3, i / 3);

		}
		scrollPane.setContent(searchGrid);

    }
    
	private EventHandler<? super MouseEvent> jAoeffnen() throws IOException {
		Stage stage = new Stage();
		stage.setTitle("X2Success");
		Pane myPane = null;
		myPane = FXMLLoader.load(getClass().getResource("/view/Freelancerprofil.fxml"));
		Scene scene = new Scene(myPane);
		stage.setScene(scene);
		stage.show();
		
		return null;
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchdegree.setValue("Inhalt1"); // Anfangswert
		searchdegree.setItems(TestListe); // Name der Liste
		searchtopic.setValue("Inhalt1"); // Anfangswert
		searchtopic.setItems(TestListe); // Name der Liste
		searchlanguage1.setValue("Inhalt1"); // Anfangswert
		searchlanguage1.setItems(TestListe); // Name der Liste
		searchlanguage2.setValue("Inhalt1"); // Anfangswert
		searchlanguage2.setItems(TestListe); // Name der Liste
		searchlanguage3.setValue("Inhalt1"); // Anfangswert
		searchlanguage3.setItems(TestListe); // Name der Liste
		searchlanguage4.setValue("Inhalt1"); // Anfangswert
		searchlanguage4.setItems(TestListe); // Name der Liste

	}


}

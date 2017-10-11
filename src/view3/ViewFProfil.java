package view3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewFProfil implements Initializable{
	
	//Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> TestListe = FXCollections.observableArrayList("Inhalt1","Inhalt2","Inhalt3");
	
	//Liste für die Choice Boxen. Aus DB ziehen
		ObservableList<String> Sprachen = 
				FXCollections.observableArrayList("Deutsch","Englisch","Französisch");

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
    private ChoiceBox degree1;

    @FXML
    private ChoiceBox degree2;

    @FXML
    private ChoiceBox topic1;

    @FXML
    private ChoiceBox topic2;

    @FXML
    private ChoiceBox degree3;

    @FXML
    private ChoiceBox topic3;

    @FXML
    private TextArea cv;

    @FXML
    private ChoiceBox language1;
    
    @FXML
    private ChoiceBox language2;
    
    @FXML
    private ChoiceBox language3;
    
    @FXML
    private ChoiceBox language4;

    @FXML
    private TextArea skills;

    @FXML
    private TextArea selfDescription;

    @FXML
    private Button changefreelancerbutton;

    @FXML
    private Button showfreelancerbutton;

    @FXML
    void changefreelancer(ActionEvent event) {  //Änderungen übernehmen
    	
    	String d1=(String) degree1.getValue();
    	String t1=(String) topic1.getValue();

    	
    	String cv1=cv.getText();
    //	String languages1=languages.getText();
    	String skills1=skills.getText();
    	String selfDescription1=selfDescription.getText();
    	


    }
    
    void changescene(String fxmlname) throws IOException {
   	 
   	 //schliesst aktuelles Fenster
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
    void showfreelancer(ActionEvent event) throws IOException {  //Eigenes Profil anzeigen
    	
       	changescene("Freelancerprofil.fxml");

    }

    //Für die choice boxen
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		degree1.setValue("Inhalt1"); //Anfangswert
    	degree1.setItems(TestListe); //Name der Liste
    	topic1.setValue("Inhalt1"); //Anfangswert
    	topic1.setItems(TestListe); //Name der Liste
//    	language1.setValue("Sprache1"); //Anfangswert
//    	language1.setItems(Sprachen); //Name der Liste
//    	language2.setValue("Sprache2"); //Anfangswert
//    	language2.setItems(Sprachen); //Name der Liste
//    	language3.setValue("Sprache3"); //Anfangswert
//    	language3.setItems(Sprachen); //Name der Liste
//    	language4.setValue("Sprache4"); //Anfangswert
//    	language4.setItems(Sprachen); //Name der Liste
		
	}

}

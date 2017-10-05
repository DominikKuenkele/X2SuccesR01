package view2;

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

public class Unternehmen_createofferController implements Initializable{

	//Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> Abschluss = FXCollections.observableArrayList("Inhalt1","Inhalt2","Inhalt3");
	//Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> Schwerpunkt = FXCollections.observableArrayList("Inhalt1","Inhalt2","Inhalt3");
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
    	
    	String title=jobtitel.getText();
    	String descriptiob=jobdescription.getText();
    	int weeklyworktime=Integer.parseInt(worktime.getText());
    	int monthlysalary=Integer.parseInt(salary.getText());
    	String degree=necessarydegree.getValue();
    	String topic1=topic.getValue();
    	String cname=contactname.getText();
    	String cmail=contactemail.getText();
    	String cphone=contactphone.getText();
    	

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
   void createnewoffer(MouseEvent event) throws IOException {
   	changescene("Unternehmen_createoffer.fxml");
   }

   @FXML
   void openHome(MouseEvent event) throws IOException {

   	//Abfrage ob es schon Favoriten gibt
  if(false)     changescene("Unternehmen_Home_Dashboard_nofavs.fxml");
  else      changescene("Unternehmen_Home_Dashboard_favs.fxml");
   
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
		necessarydegree.setValue("Inhalt1"); //Anfangswert
    	necessarydegree.setItems(Abschluss); //Name der Liste
    	topic.setValue("Inhalt1"); //Anfangswert
    	topic.setItems(Schwerpunkt); //Name der Liste
		
	}

}

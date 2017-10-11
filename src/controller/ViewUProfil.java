package controller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Unternehmensprofil;

public class ViewUProfil implements Initializable{

	//Liste für die Choice Boxen. Aus DB ziehen
	ObservableList<String> Branche = FXCollections.observableArrayList("Inhalt1","Inhalt2","Inhalt3");
	
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

    @FXML
    private TextField cname;

    @FXML
    private DatePicker cdate;

    @FXML
    private TextField cemployees;

    @FXML
    private TextField ccity;

    @FXML
    private TextField cstreet;

    @FXML
    private TextField cplz;

    @FXML
    private TextField cnumber;

    @FXML
    private ChoiceBox<String> cbranche;

    @FXML
    private TextArea cdescription;

    @FXML
    private Button changecompanyb;

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
  if(true)     changescene("Unternehmen_Home_Dashboard_nofavs.fxml");
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
    void savechanges(ActionEvent event) {
    	
    	String name=cname.getText();
    	LocalDate date=cdate.getValue();
    	String city=ccity.getText();
    	int plz=Integer.parseInt(cplz.getText());
    	String street=cstreet.getText();
    	int streetnumber=Integer.parseInt(cnumber.getText());
    	String branche=cbranche.getValue();
    	String description=cdescription.getText();
    

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cbranche.setValue("Inhalt1"); //Anfangswert
    	cbranche.setItems(Branche); //Name der Liste
    	Verwaltung v= Verwaltung.getInstance();
    	Unternehmensprofil u = (Unternehmensprofil) v.getCurrentProfil();
    	cbranche.setValue(u.getBenefits());//ändern
    	cname.setText(u.getName());
    	cdate.setValue(u.getFounding());
    	cemployees.setText(Integer.toString(u.getEmployees()));
    	ccity.setText(u.getAddress().getCity());
    	cstreet.setText(u.getAddress().getStrasse());
    	cplz.setText(u.getAddress().getPlz());
    	cnumber.setText(u.getAddress().getNumber());
    	cdescription.setText(u.getDescription());
		
	}

}


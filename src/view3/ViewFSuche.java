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
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewFSuche implements Initializable{
	
	//Liste für die Choice Boxen. Aus DB ziehen
		ObservableList<String> TestListe = FXCollections.observableArrayList("Inhalt1","Inhalt2","Inhalt3");

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
    private TextField searchcompanyname;

    @FXML
    private ChoiceBox<String> searchnecessarydegree;

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
    void changeslider(KeyEvent event) {

    	
    	salarySlider.setValue(Double.parseDouble(Salary.getText()));
    }

    @FXML
    void dragdone(MouseEvent event) {
    	Salary.setText(Double.toString(Math.round(salarySlider.getValue())));

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
    void openHome(MouseEvent event) throws IOException {

    	//Abfrage ob es schon Favoriten gibt
   if(true)     changescene("User_Home_Dashboard_nofavs.fxml");
   else      changescene("User_Home_Dashboard_favs.fxml");
       
    }

    @FXML
    void openProfil(MouseEvent event) throws IOException {
    	
    	changescene("User_Profil.fxml");

    }

    @FXML
    void openSearch(MouseEvent event) throws IOException {

    	changescene("User_Suche_Dashboard.fxml");
    }


    @FXML
    void openSettings(MouseEvent event) throws IOException {
    	
    	changescene("User_Settings_Dashboard.fxml");

    }

    @FXML
    void openSignOut(MouseEvent event) throws IOException {

    	changescene("Einloggen.fxml");
    }

    @FXML
    void searchoffers(ActionEvent event) {
    	
    	String cname=searchcompanyname.getText();
    	int min=Integer.parseInt(minimumemployees.getText()); //Inhalt prüfen
    	int max=Integer.parseInt(maximumemployees.getText()); //Inhalt prüfen
    	String branche=searchbranche.getValue();
    	String degree=searchnecessarydegree.getValue();
    	int salary1=Integer.parseInt(Salary.getText());
    	
    	result1.setVisible(true); //Je nachdem sichtbar machen
    	

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
    void paginationSwitch(MouseEvent event) {

    	//searchcompanyname.setText("wawasdawsds");
    }
    @FXML
    void showresult1(MouseEvent event) throws IOException {
    	
    	openoffer();
    	//ID muss noch mitgegeben werden
 

    }

    @FXML
    void showresult2(MouseEvent event) throws IOException {
    	openoffer();
    	//ID muss noch mitgegeben werden

    }

    @FXML
    void showresult3(MouseEvent event) throws IOException {
    	openoffer();
    	//ID muss noch mitgegeben werden

    }

    @FXML
    void showresult4(MouseEvent event) throws IOException {
    	openoffer();
    	//ID muss noch mitgegeben werden

    }

    @FXML
    void showresult5(MouseEvent event) throws IOException {
    	openoffer();
    	//ID muss noch mitgegeben werden

    }

    @FXML
    void showresult6(MouseEvent event) throws IOException {
    	openoffer();
    	//ID muss noch mitgegeben werden

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchnecessarydegree.setValue("Inhalt1"); //Anfangswert
		searchnecessarydegree.setItems(TestListe); //Name der Liste
    	searchbranche.setValue("Inhalt1"); //Anfangswert
    	searchbranche.setItems(TestListe); //Name der Liste
		
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entitie.Paie_Prof;
import entitie.Paiment_eleve;
import entitie.User;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceFinanciere;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Add_paie_profController implements Initializable {
    @FXML
    private TextField cin;
      @FXML
    private TextField nom;
          @FXML
    private TextField prenom;
      @FXML
    private TextField nbre_heure;
          @FXML
    private TextField salaire_heure;
      @FXML
    private ComboBox <String> mode_reglement;
      ServiceFinanciere sf=new ServiceFinanciere();
      
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

   mode_reglement.getItems().add("espece");
   mode_reglement.getItems().add("cheque");
   String value = (String) mode_reglement.getValue();
         mode_reglement.setEditable(true);
    }  
         public void getEmployeeId(ActionEvent e)throws IOException,ParseException{
			String sid=cin.getText();
			int id=Integer.parseInt(sid);
			User emp=MyDB.getEmpoyeeId(id);
                       
		nom.setText(emp.getNom());
	        prenom.setText(emp.getPrenom());
     
			
		}
  public void add(ActionEvent event) throws IOException {
//String mode_reglemen=mode_reglement.getText();
String ci=cin.getText();
//String c=nbre_heure.getText();
String o=salaire_heure.getText();
String oo=nbre_heure.getText();
//String e=
String rr=nom.getText();
String rrr=prenom.getText();
//int cinn=Integer.parseInt(ci);
int r=Integer.parseInt(ci);
int pp=Integer.parseInt(o);
int ppp=Integer.parseInt(oo);
  Paie_Prof p=new Paie_Prof();
 //p.setNum_paiment(cinn);
  p.setCin(r);
  p.setNom(rr);
  p.setPrenom(rrr);
    p.setMode_reg(mode_reglement.getValue());
  p.setNbre_heure(ppp);
  p.setSalaire_heure(pp);

  int status=sf.ajouterPaiement(p);
	
	
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Data Insert");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data Insert");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	
    }
          public void retourVersListe(ActionEvent event) throws IOException {
          Stage secondStage= (Stage)( (Node) (event.getSource())).getScene().getWindow();
  secondStage.close(); 
Stage secondeStage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	secondeStage.setScene(scene);
        secondeStage.show();  
        
    }
}

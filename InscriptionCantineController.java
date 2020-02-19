/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.abonneC;
import Services.servicecantine;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class InscriptionCantineController implements Initializable {

    @FXML
    private Pane mainpane;
    @FXML
    private JFXTextField Nom;
    @FXML
    private JFXTextField Prenom;
    @FXML
    private JFXTextField CIn;
    @FXML
    private JFXComboBox<String> type;
    ObservableList<String> listt=FXCollections.observableArrayList("demi pension","pension complete");
   @FXML
    private JFXComboBox<String> dure;
   ObservableList<String> listd=FXCollections.observableArrayList("trimestre","semestre","anneé");
    @FXML
    private JFXButton inscr;
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         dure.setItems(listd);
        type.setItems(listt);
    }    

    @FXML
    private void signinCNT (ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("test inscription");
        
        abonneC p = new abonneC();
        
        p.setCin(parseInt(CIn.getText()));
        p.setNom(Nom.getText());
        p.setPrenom(Prenom.getText());
        p.setType_abonement((String) type.getValue());
         p.setDureé((String) dure.getValue());
        
       
        servicecantine sp = new servicecantine();
        sp.ajouterAbonné(p);
          System.err.println("insertion effectue");
       
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/ProfilEtudiant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
  
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class ChoixTypeController implements Initializable {

    @FXML
    private JFXButton etudiant;
    @FXML
    private JFXButton enseig;
    @FXML
    private JFXButton Parent;
    @FXML
    private JFXButton retour;
    @FXML
    private AnchorPane paneIE;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
     private void inscriptionET(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("inscrire");
        
        
        Stage stage = (Stage) etudiant.getScene().getWindow();
                System.err.println("*****");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/InscriptionET.fxml"));
      
            System.err.println("s'inscrire");
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }     
    @FXML
     private void inscriptionEn(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("inscrire");
        
        
        Stage stage = (Stage) enseig.getScene().getWindow();
                System.err.println("*****");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/inscriptionEn.fxml"));
      
            System.err.println("s'inscrire");
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }   
    @FXML
     private void inscriptionP(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("inscrire");
        
        
        Stage stage = (Stage) Parent.getScene().getWindow();
                System.err.println("*****");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/inscriptionP.fxml"));
      
            System.err.println("s'inscrire");
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }              

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
         Stage stage = (Stage) paneIE.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Main.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}

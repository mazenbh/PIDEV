/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.User;
import Services.UserService;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class ProfilParentController implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXTextField password;
    @FXML
    private JFXTextField username;
    @FXML
    private Label nom1;
    @FXML
    private Label prenom1;
    @FXML
    private Label mail1;
    @FXML
    private Label cin1;
    @FXML
    private Label username1;
    @FXML
    private Label password1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
     private void modifierprofil(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("modif");
        
        User p = new User();
        
     
        p.setUsername(username.getText());
        p.setPassword(password.getText());
        p.setCin(cin.getText());
        p.setNom(nom.getText());
        p.setPrenom(prenom.getText());
        p.setEmail(adresse.getText());
       
      
        
       
        UserService sp = new UserService();
        sp.modifierUser(p);
          System.err.println("insertion effectue");
       
      
    } 
    
}

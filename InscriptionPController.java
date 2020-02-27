/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.User;
import Services.UserService;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class InscriptionPController implements Initializable {

    private JFXButton ok;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField cin;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXTextField pasword;
    @FXML
    private JFXTextField email;
    private AnchorPane app;
    @FXML
    private JFXComboBox<String> Comboboxsexe;
    ObservableList<String> list=FXCollections.observableArrayList("Homme","Femme");
    @FXML
    private JFXTextField num;
    @FXML
    private Pane paneEp;
    @FXML
    private JFXButton ok1;
    @FXML
    private JFXButton retour1;

    /**
     * Initializes the controller class.
     */
    
    public void initialize(URL url, ResourceBundle rb) {
        Comboboxsexe.setItems(list);
    }  
    
     private void signinP(ActionEvent event) throws SQLException, IOException {
         
    } 

    private void retour(ActionEvent event) throws IOException {
      
        
    } 

    @FXML
    private void signinP1(ActionEvent event) throws IOException, SQLException {
        System.err.println("test inscription");
        
        User p = new User();
        
        
        p.setUsername(username.getText());
        p.setPassword(pasword.getText());
         p.setCin(cin.getText());
        p.setNom(nom.getText());
        p.setPrenom(prenom.getText());
        p.setEmail(email.getText());
        p.setPassword(prenom.getText());
        p.setRoles("Parent");
        p.setNum_tel(parseInt(num.getText()));
        p.setSexe((String) Comboboxsexe.getValue());
       
        UserService sp = new UserService();
        sp.creerUser(p);
          System.err.println("insertion effectue");
       
        Stage stage = (Stage) ok1.getScene().getWindow();
                System.err.println("bbb2");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Main.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();      
    }

    @FXML
    private void retour1(ActionEvent event) throws IOException {
          Stage stage = (Stage) paneEp.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Main.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}

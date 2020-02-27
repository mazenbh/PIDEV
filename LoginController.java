/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.User;
import Services.UserService;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class LoginController implements Initializable {
    
    
   

    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton Login;
    @FXML
    private Hyperlink mdfoublie;
     public static int test ;
    @FXML
    private AnchorPane ap;
    @FXML
    private JFXButton cree;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void authentification(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("Welcome");
        
        User p = new User();
        p.setUsername(username.getText());
        p.setPassword(password.getText());
        
        UserService sp = new UserService();
        test=sp.authentification(p);
          System.err.println(test);
          
        if(test!=0){
        Stage stage = (Stage) ap.getScene().getWindow();
                System.err.println("connection etablie");
         
           FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("Fxml/FXMLDocument.fxml"));
           Parent root =(Parent) loader.load();
            //System.err.println(info);
            FXMLDocumentController  acceuil=loader.getController();
          // acceuil.MyLogin(username.getText());
           
            
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    
}


    @FXML
    private void choix(ActionEvent event) throws IOException {
        Stage stage = (Stage) ap.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/ChoixType.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }
    
}

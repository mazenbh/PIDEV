/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.LoginController.test;
import Entites.User;
import Services.UserService;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class SignInController implements Initializable {

    @FXML
    private JFXButton loginbtn;
    @FXML
    private VBox Singin;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private JFXButton mdp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginbtn(ActionEvent event) throws IOException {
        
        
         System.err.println("Welcome");
      
        User p = new User();
        p.setUsername(username.getText());
        p.setPassword(password.getText());
        
        UserService sp = new UserService();
        test=sp.authentification(p);
       
          System.err.println(test);
          
        if(test!=0){
        Stage stage1 = (Stage) Singin.getScene().getWindow();
                System.err.println("connection etablie");
         
        stage1.close();
        Stage stage = new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("/Fxml/FXMLDocument.fxml"));
         ////////////////////////////////
     
         
         
           
         //////////////++///////////////////
     
         
         
         
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
   /* private void authentification(ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
       
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

*/
   
     
    }

    @FXML
    private void mdp(ActionEvent event) throws IOException {
        
            Stage stage1 = (Stage) Singin.getScene().getWindow();
                System.out.println("redirection to login");
                stage1.close();
          
      
           Stage stage = new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("/Fxml/mdp.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }
    
    
   
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.LoginController.test;
import Entites.Mail;
import Entites.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class MdpController implements Initializable {

    @FXML
    private JFXTextField username;
    @FXML
    private JFXButton valider;
    @FXML
    private Label ahwa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void mdp()
    {
        System.err.println("Welcome");
        
        User p = new User();
        String cin1=null;
        String mail=null;
        String pass=null;
        UserService sp = new UserService();
        
        p = sp.rechercheparcin(username.getText());
       cin1= p.getCin();
       mail=p.getEmail();
       pass=p.getPassword();
         if(cin1!=null){
             System.err.println(cin1+"...."+mail+"......"+pass);
             Mail u=new Mail();
     
        u.sendMail(mail,"votre mdp" ,pass );

        }
         else{
             System.err.println("fail");
         }
        
        
    }   
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.User;
import Services.UserService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class AcceuilController implements Initializable {

    @FXML
    private JFXButton Etudiant;
    @FXML
    private JFXButton Enseignant;
    @FXML
    private JFXButton Parent;
    @FXML
    private Label nom;
    @FXML
    private AnchorPane ap2;
    @FXML
    private JFXButton deconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void MyLogin(String text)
    {  nom.setText(text);
       
    }
    @FXML
   private void logout(ActionEvent event) throws IOException {
       
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Login.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }
   
   @FXML
   private void afficherEt(ActionEvent event) throws IOException{
          int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("admin")) {    
         
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to aff");
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/AfficherEtudiant.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       
   }
        else if (test.equals("etudiant")) {
        
  }
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to aff");
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/profilEtudiant.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
   
   } 
   
  
   @FXML
   private void afficherP(ActionEvent event) throws IOException{
          int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("admin")) {    
         
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to aff");
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/afficherParent.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       
   }
        else if (test.equals("parent")) {
        
  }
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to aff");
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/profilParent.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
   
   } 
   
   
    @FXML
   private void afficherEnn(ActionEvent event) throws IOException{
          int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("admin")) {    
         
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to aff");
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/afficherEnseignant.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
       
   }
        else if (test.equals("parent")) {
        
  }
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to aff");
                Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/profilEnseignant.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
   
   } 
   
   
   
   
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.LoginController.test;
import Entites.User;
import Services.UserService;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author mazen
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Pane mainpane;
    @FXML
    private JFXButton etudbtn;
    @FXML
    private JFXButton deco;
    @FXML
    private JFXButton btnEnseig;
    @FXML
    private JFXButton prnt;
    @FXML
    private AnchorPane ap2;
    @FXML
    private JFXButton cantinee;
    private Label nom;
    @FXML
    private Label nomm;
    @FXML
    private JFXButton eventbtn;
    @FXML
    private JFXButton clubbtn;
    @FXML
    private WebView webView;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         WebEngine engine=webView.getEngine();
   engine.load("https://www.independent.co.uk/topic/education-news");
        
       
    }
    
     public void MyLogin(String text)
    {  nomm.setText(text);
       
    }

    @FXML
    private void etudbtn(ActionEvent event) throws IOException, SQLException {
         int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();

         String test= us.rechercherparrole(id); 
        if (test.equals("admin")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/AfficherEtudiant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("etudiant") || test.equals("abonne")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profilEtudiant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
        
  }

    @FXML
    private void btnEnseig(ActionEvent event) throws IOException {
        
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();

         String test= us.rechercherparrole(id); 
        if (test.equals("admin")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/afficherEnseignant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("enseignant")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profilEnseignant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
        
        
        
    }
    
   /* public void MyLogin(String text)
    {  nom.setText(text);
       
    }*/
    //@FXML
 /*  private void logout(ActionEvent event) throws IOException {
       
        Stage stage = (Stage) ap2.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Login.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }
    */

    @FXML
    private void prnt(ActionEvent event) throws IOException {
        
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("admin")) {    
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/afficherParent.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }
        
          else if (test.equals("Parent")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profilParent.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }
    }

    
    Connection c = DataBase.getInstance().getConnection();
       
    Statement ste;
    
    @FXML
    private void deco(ActionEvent event) throws IOException, SQLException {
        
       Stage stage = (Stage) ap2.getScene().getWindow();
          
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/Main.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
         String query = "update fos_user set demande='not'";
        
                ste = c.createStatement();
                

                ste.executeUpdate(query);
    
    }

    @FXML
    private void gosee(ActionEvent event) throws IOException {
        
        int id=LoginController.test;
          User p = new User();
         UserService us =new UserService();
        
         String test= us.rechercherparrole(id); 
        if (test.equals("admin")) {    
        
           mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/afficherAbonne.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }
         else if (test.equals("abonne")) {
              
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/Menu.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
          }

   
    

    
}

    @FXML
    private void eventbtn(ActionEvent event) {
    }

    @FXML
    private void clubbtn(ActionEvent event) {
    }

}
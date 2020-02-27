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
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class ProfilEtudiantController implements Initializable {
Connection c = DataBase.getInstance().getConnection();
 public ProfilEtudiantController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
       
    Statement ste;
    @FXML
    private JFXButton cnt;
    @FXML
    private Pane mainpane;

   
    

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextField cin;
    
  //  int id= LoginController.test;
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
       cin.setDisable(true);
       String req = "select nom,prenom,email,cin,username,password from fos_user where demande='connected' ";
        System.out.println("zdzdzddz");
       
        try {
           /* String[] args = null;
            Webcamtest.main(args);*/
           
             ste = c.createStatement();
            ResultSet rs1 = ste.executeQuery(req);
            System.out.println("erreeeee");

            while (rs1.next()) //  list.add(new Talentueux(rs.getString("Talent"),rs.getInt("NumTel"), rs.getString("Email"), rs.getString("DateNaissance")); //soit le nom de la colonne soit l'indice
            {
                 System.out.println(rs1.getString("nom"));
                 System.out.println(rs1.getString("prenom"));
                  System.out.println(rs1.getString("email"));
                   System.out.println(rs1.getString("cin"));
                    System.out.println(rs1.getString("username"));
                     System.out.println(rs1.getString("password"));
                System.out.println("yeziiii");

            // attempt to put it in a textfield
           nom.setText(rs1.getString("nom"));
           prenom.setText(rs1.getString("prenom"));
           adresse.setText(rs1.getString("email"));
           cin.setText(rs1.getString("cin"));
           username.setText(rs1.getString("username"));
           password.setText(rs1.getString("password"));
           

           
                }

        } catch (SQLException ex) {
            System.out.println("asadad");
        
        }
           
 
    
    }

     
    
   /* @FXML
    private void manage(ActionEvent event) {
        update.setVisible(true);
        
        manage.setVisible(false);
        
        User u = new User();
        UserService us = new UserService();
        System.err.println("runnnnnnnnnnnn");
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        adresse.setText(u.getEmail());
        cin.setText(u.getCin());
        sexe.setText(u.getSexe());
       
    }
    */
    
    

    
   // @FXML
  /*  private void updatemembre(ActionEvent event) throws SQLException {
    try{ 
                 update.setVisible(false);
                
                 manage.setVisible(true);
            
               
                User u = new User();
                UserService us = new UserService();
                System.err.println("runnnnnnnnnnnn");
                u=us.getUserByid(id);
                
                u.setNom(nom.getText());
                u.setPrenom(prenom.getText());
                u.setEmail(adresse.getText());
                u.setCin(cin.getText());
                u.setSexe(sexe.getText());
             
                
                
                us.modifieruser(id, u);
               // aff();
                
                
            } catch (SQLException ex) {
                Logger.getLogger(ProfilEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

 */

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

    @FXML
    private void goinscricnt(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/InscriptionCantine.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

  
   }

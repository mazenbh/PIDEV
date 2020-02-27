/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.abonneC;
import Services.servicecantine;
import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    
     Connection c = DataBase.getInstance().getConnection();
   
 public InscriptionCantineController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    Statement ste;
    @FXML
    private Pane mainpane;
    //private JFXTextField Nom;
    //private JFXTextField Prenom;
    //private JFXTextField CIn;
     @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField cin;
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
        cin.setDisable(true);
        nom.setDisable(true);
        prenom.setDisable(true);
         dure.setItems(listd);
        type.setItems(listt);
        
        
          String req = "select nom,prenom,email,cin,username,password from fos_user where demande='connected' ";
        System.out.println("zdzdzddz");
       
        try {
           /* String[] args = null;
            Webcamtest.main(args);*/
           
             ste = c.createStatement();
            ResultSet rs1 = ste.executeQuery(req);
            System.out.println("erreeeee");

            while (rs1.next()) //
            {
                 System.out.println(rs1.getString("nom"));
                 System.out.println(rs1.getString("prenom"));
         
                   System.out.println(rs1.getString("cin"));

                System.out.println("yeziiii");

            // attempt to put it in a textfield
           nom.setText(rs1.getString("nom"));
           prenom.setText(rs1.getString("prenom"));
      
           cin.setText(rs1.getString("cin"));
        
           

           
                }

        } catch (SQLException ex) {
            System.out.println("asadad");
        
        }
           
    }    

    @FXML
    private void signinCNT (ActionEvent event) throws SQLException, IOException {
        //Preferences userPreferences = Preferences.userRoot();
              
        System.err.println("test inscription");
        
        abonneC p = new abonneC();
        
        p.setCin(parseInt(cin.getText()));
        p.setNom(nom.getText());
        p.setPrenom(prenom.getText());
        p.setType_abonement((String) type.getValue());
         p.setDureé((String) dure.getValue());
         int aman;
         aman=(parseInt(cin.getText()));
        
       
        servicecantine sp = new servicecantine();
        sp.ajouterAbonné(p);
       String query = "update fos_user set roles='abonne' where cin='"+aman+"'";
        ste.executeUpdate(query);
          System.err.println("insertion effectue");
       
         /*  mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/profiletudiant.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();*/
    }
  
    
}

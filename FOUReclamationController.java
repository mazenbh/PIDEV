/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ReclamationServices;
import entities.Reclamation;
import entities.ReclamationType;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FOUReclamationController implements Initializable {

   
   
   
    @FXML
    private TextField ressujet;
    @FXML
    private TextArea rescontenu;
    @FXML
    private ChoiceBox<ReclamationType> restype;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
   
    @FXML
    private Button valider;
    
    private ObservableList<ReclamationType> types;
    private Reclamation r = new Reclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
       
    }    

  

    public void setParametres(Reclamation r,ObservableList ob) {
        
        this.r = r;
        this.types = ob ;
        
        ObservableList<ReclamationType> typeSelected = FXCollections.observableArrayList(r.getType()) ;
        this.restype.setItems(typeSelected );
        this.restype.setValue(r.getType());
        this.ressujet.setText(r.getSujet());
        this.rescontenu.setText(r.getContenu());
    }

    @FXML
    private void valider(ActionEvent event) {
        
        try {
            ReclamationServices rServices = new ReclamationServices();
            rServices.insert(r);
            
            new Alert(Alert.AlertType.INFORMATION, "sucess").show();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FCReclamation.fxml"));
            valider.getScene().setRoot(loader.load());
            
        } catch (IOException ex) {
            Logger.getLogger(FOUReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void supprimer(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FCReclamation.fxml"));
            supprimer.getScene().setRoot(loader.load());
            
        } catch (IOException ex) {
            Logger.getLogger(FOUReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        restype.setItems(types);
        restype.setValue(r.getType());
        ressujet.setEditable(true);
        rescontenu.setEditable(true);
        
        
    }

   
    
    
    
}

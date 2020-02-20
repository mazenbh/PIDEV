/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author pubkhalil
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private JFXButton rec;
    @FXML
    private Pane Mainpane;
    @FXML
    private JFXButton test;
    @FXML
    private JFXButton foyer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void bel(ActionEvent event) throws IOException {
        
     
        Mainpane.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("BReclamation.fxml"));
        Mainpane.getChildren().add(parent);
        Mainpane.toFront();
            
    }

    @FXML
    private void foyer(ActionEvent event) throws IOException {
        
         
        Mainpane.getChildren().clear();
        Parent parent=FXMLLoader.load(getClass().getResource("chambre.fxml"));
        Mainpane.getChildren().add(parent);
        Mainpane.toFront();
        
        
    }

   
        
        
        
        
        
        
        
        
        
    }

   

    
    

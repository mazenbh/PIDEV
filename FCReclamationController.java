

  

    

    
  
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ReclamationServices;
import Services.ReclamationTypeServices;
import entities.Reclamation;
import entities.ReclamationType;
import entities.UserC;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class FCReclamationController implements Initializable {

    @FXML
    private TextField sujet;
    @FXML
    private TextArea contenu;
    @FXML
    private Button ajouter;
    @FXML
    private ChoiceBox<ReclamationType> type;

    private ObservableList<ReclamationType> observableListTypes;
    @FXML
    private Label organisateurLbl;
    @FXML
    private ChoiceBox<UserC> choiceBox;
    @FXML
    private Label eventLbl;
    @FXML
    private Pane mainpane;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ReclamationTypeServices typeServices = new ReclamationTypeServices();
        ArrayList types = (ArrayList) typeServices.selectAll();
        observableListTypes = FXCollections.observableArrayList(types);
        type.setItems(observableListTypes);

        choiceBox.setVisible(false);

    }


        
    
    
    
    

    @FXML
    private void typeChoisieAction(MouseEvent event) {

        ReclamationType ty = type.getSelectionModel().getSelectedItem();
        ReclamationServices rS = new ReclamationServices();

       

                ArrayList orgs = (ArrayList) rS.selectListOrg(7);
                ObservableList<UserC> observableListChoice = FXCollections.observableArrayList(orgs);
                choiceBox.setItems(observableListChoice);


    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
        
        
        
      
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("BReclamation.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
    }

    @FXML
    private void ajouter(ActionEvent event) {
        
        
        try {

            ReclamationType vtype = type.getSelectionModel().getSelectedItem();
            String vsujet = sujet.getText();
            String vcontenu = contenu.getText();
            Reclamation r = new Reclamation(vtype, vsujet, vcontenu);

            r.setUser_id(4);
            r.setEvent_id(1);
            r.setOrganisateur_id(5);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FOUReclamation.fxml"));
            Parent root = loader.load();
            FOUReclamationController Rc = loader.getController();
            Rc.setParametres(r, observableListTypes);
            ajouter.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(FCReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    
        
        
        
        
        
        
        
        
        
        
        
        
    }
        
}


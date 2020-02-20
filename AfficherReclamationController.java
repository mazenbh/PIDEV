/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ReclamationServices;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AfficherReclamationController implements Initializable {

    @FXML
    private TextField userName;
    @FXML
    private TextField type;
    @FXML
    private TextField sujet;
    @FXML
    private TextArea contenu;
    private TextField organisateur;
    private TextField event;

    private Reclamation r;
    @FXML
    private TextField dateC;
    @FXML
    private Button backBtn;
    @FXML
    private Button supprimerBtn;
    @FXML
    private Button archiverBtn;
    @FXML
    private Button restaurerBtn;
    @FXML
    private Label traiteLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setParametres(Reclamation r) {

        this.r = r;

        this.dateC.setText("" + r.getDateR());
        this.userName.setText(r.getUser().getName());
        this.organisateur.setText(r.getOrganisateur().getName());
        this.event.setText("" + r.getEvent_id());
        this.type.setText(r.getType().getNom());
        this.sujet.setText(r.getSujet());
        this.contenu.setText(r.getContenu());

        if (r.getArchive() == 1) {
            archiverBtn.setDisable(true);
        }
        if (r.getCorbeille() == 1 || r.getArchive() == 1) {
            restaurerBtn.setDisable(false);
        }

        if (r.getTraite() == 1) {
            traiteLbl.setText("Cette Reclamation est déjà traité");
        }

    }

    @FXML
    private void backAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("BReclamation.fxml"));
            Parent root = loader.load();
            backBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void supprimerAction(ActionEvent event) {
        ReclamationServices rS = new ReclamationServices();

        if (r.getCorbeille() == 0) {
            rS.toCorbeille(r);
        } else {
            rS.delete(r);
        }

        backAction(event);
    }

    @FXML
    private void archiverAction(ActionEvent event) {
        ReclamationServices rS = new ReclamationServices();
        rS.toArchive(r);
        backAction(event);
    }

    @FXML
    private void restaurerAction(ActionEvent event) {

        ReclamationServices rS = new ReclamationServices();
        rS.restaurer(r);
        backAction(event);

    }

   
        
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Club;
import Entites.Evenement;
import Entites.Notifications;
import java.awt.AWTException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import Services.ServiceClub;
import Services.ServiceEvenement;
import Utils.Database2;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class EvenementController implements Initializable {

    @FXML
    private TableView<Evenement> evenementTable;
    @FXML
    private TableColumn<Evenement, Integer> colcl;
    @FXML
    private TableColumn<Evenement, String> coldes;
    @FXML
    private TableColumn<Evenement, Integer> colnb;
    @FXML
    private TableColumn<Evenement, String> coleta;
    @FXML
    private TableColumn<Evenement, String> coldb;
    @FXML
    private TableColumn<Evenement, String> coldf;
    @FXML
    private TableColumn<Evenement, String> coldesc;
    @FXML
    private TableColumn<Evenement, String> coltyp;
    @FXML
    private TextField description;
    @FXML
    private TextField typeEvenement;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField etat;
    @FXML
    private TextField nbPlaces;
    @FXML
    private TextField designation;
    @FXML
    private Label dd;
    @FXML
    private Label df;
    @FXML
    private TextField idClub;
    ObservableList<Evenement> oblist = FXCollections.observableArrayList();
    @FXML
    private Button ajouter;
    @FXML
    private Pane mainpane;
    @FXML
    private Button stat;
    @FXML
    private Button filtrebtn;
    @FXML
    private DatePicker dtpdeb;
    @FXML
    private DatePicker dtpfi;
    @FXML
    private TableColumn<?, ?> colev;
    @FXML
    private TextField idEvenement;
    @FXML
    private Label dd1;
    @FXML
    private Label dd2;
    @FXML
    private Label dd3;
    @FXML
    private Label dd4;
    @FXML
    private Label dd5;
    @FXML
    private Label dd31;
    @FXML
    private Label dd32;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        idEvenement.setDisable(true);
        idClub.setDisable(true);
        
        clearFields();
        populate();

        colev.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
        colcl.setCellValueFactory(new PropertyValueFactory<>("idClub"));
        coldes.setCellValueFactory(new PropertyValueFactory<>("designation"));
        colnb.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));
        coleta.setCellValueFactory(new PropertyValueFactory<>("etat"));
        coldb.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        coldf.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        coltyp.setCellValueFactory(new PropertyValueFactory<>("typeEvenement"));
        evenementTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idEvenement.setText(Integer.toString(evenementTable.getSelectionModel().getSelectedItem().getIdEvenement()));
                idClub.setText(Integer.toString(evenementTable.getSelectionModel().getSelectedItem().getIdClub()));
                designation.setText(evenementTable.getSelectionModel().getSelectedItem().getDesignation());
                nbPlaces.setText(Integer.toString(evenementTable.getSelectionModel().getSelectedItem().getNbPlaces()));
                etat.setText(evenementTable.getSelectionModel().getSelectedItem().getEtat());
                dtpdeb.setValue(evenementTable.getSelectionModel().getSelectedItem().getDateDebut().toLocalDate());
                dtpfi.setValue(evenementTable.getSelectionModel().getSelectedItem().getDateFin().toLocalDate());
                description.setText(evenementTable.getSelectionModel().getSelectedItem().getDescription());
                typeEvenement.setText(evenementTable.getSelectionModel().getSelectedItem().getTypeEvenement());

            }

        });

    }

    private void clearFields() {
        idEvenement.clear();
        idClub.clear();
        designation.clear();
        nbPlaces.clear();
        etat.clear();
        dtpdeb.setValue(LocalDate.now());
        dtpfi.setValue(LocalDate.now());
        description.clear();
        typeEvenement.clear();
    }

    public void populate() {
        String sql = "select * from evenement";
        Connection con = Database2.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Evenement(rs.getInt("idEvenement"), rs.getInt("idClub"), rs.getString("designation"), rs.getInt("nbPlaces"), rs.getString("etat"), rs.getDate("dateDebut"), rs.getDate("dateFin"),
                        rs.getString("description"), rs.getString("typeEvenement")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        evenementTable.setItems(oblist);
    }

    @FXML
    private void SuppEvent(ActionEvent event) throws SQLException, AWTException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            ServiceEvenement.SuppEvenement(Integer.parseInt(idEvenement.getText()));
            populate();
            clearFields();
            Notifications n = new Notifications();
            n.displayTray("Evenement", "bien supprimé");
            populate();

        }
    }

    @FXML
    private void ModifEvent(ActionEvent event) throws SQLException, AWTException {
        if (isEmpty()) {
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de Modifier?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                ServiceEvenement.ModifEvenement(Integer.parseInt(idEvenement.getText()), Integer.parseInt(idClub.getText()), designation.getText(), Integer.parseInt(nbPlaces.getText()), etat.getText(), java.sql.Date.valueOf(dtpdeb.getValue()).toString(), java.sql.Date.valueOf(dtpfi.getValue()).toString(), description.getText(), typeEvenement.getText());
                populate();
                clearFields();
                Notifications n = new Notifications();
                n.displayTray("Evenement", "bien modifié");
                populate();
            }
        }

    }

    @FXML
    private void ajouterbtn(ActionEvent event) throws IOException {

        mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/ajoutEvent.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void statbtn(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/eventChart.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void filtrebtn(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/filtreEtat.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();

    }
    
    
    
    
  
    
    
    
    
    
       private boolean validatorInt2(String s) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("ID CLUB NON VALIDE !");
            alert.showAndWait();
            return false;
        }

    }

   private boolean validatorInt3(String s) {
        Pattern p = Pattern.compile("[1-9]+[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("NOMBRES PLACES NON VALIDE !");
            alert.showAndWait();
            return false;
        }

    }
   private boolean validatorString(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("designation invalide !");
            alert.showAndWait();
            return false;
        }

    }

      private boolean validatorString2(String s) {
        Pattern p = Pattern.compile("[a-zA-Z é]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("etat invalide !");
            alert.showAndWait();
            return false;
        }

    }
   
         private boolean validatorString3(String s) {
        Pattern p = Pattern.compile("[a-zA-Z. _-]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("description invalide!");
            alert.showAndWait();
            return false;
        }

    }
              private boolean validatorString4(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("type invalide!");
            alert.showAndWait();
            return false;
        }

    }
               private boolean validatorInt(String s) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("ID EVENT NON VALIDE !");
            alert.showAndWait();
            return false;
        }

    }
   
    private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }
   
    private boolean isEmpty() {
        if (validatorInt(idEvenement.getText()) ==false) {
       return true ; 
        }
        if (validatorInt2(idClub.getText())  ==false) {
            return true;
        }
        if (validatorString(designation.getText()) == false  ) {
            return true;
        }
        if (validatorInt3(nbPlaces.getText())  ==false ) {
            
        return true ;
        }
          if (validatorString2(etat.getText()) == false ) {
            return true;
        }
//          || dtpdeb.getValue().isBefore(LocalDate.now()
        if (dtpdeb.getValue() == null ) {
            warning("Veuillez saisir une date de début valide  pour l'evenement!");
            return true;
        }
        if (dtpfi.getValue() == null || dtpfi.getValue().isBefore(dtpdeb.getValue())) {
            warning("Veuillez saisir une date de fin valide  pour l'evenement!");
            return true;
        }
        
        if (validatorString3(description.getText()) == false) {
            return true;
        }
        if (validatorString4(typeEvenement.getText()) == false ) {
            return true;
        }

        return false;
    }

//    private void partage(ActionEvent event) throws IOException {
//           mainpane.getChildren().clear();
//        Parent parent = FXMLLoader.load(getClass().getResource("/views/partage.fxml"));
//        mainpane.getChildren().add(parent);
//        mainpane.toFront();
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Evenement;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Utils.Database2;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class FiltreEtatController implements Initializable {

    @FXML
    private TableView<Evenement> table;
    @FXML
    private TableColumn<Evenement, String> designation;
    @FXML
    private TableColumn<Evenement, String> etat;
    @FXML
    private TableColumn<Evenement, String> dateDeb;
    @FXML
    private TableColumn<Evenement, String> dateFin;
    @FXML
    private TableColumn<Evenement, String> typeEv;
    @FXML
    private ComboBox<String> evenement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        
        designation.setCellValueFactory(new PropertyValueFactory<>("designation"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
        dateDeb.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
        dateFin.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
        typeEv.setCellValueFactory(new PropertyValueFactory<>("typeEvenement"));
        try {
            List<String> events = new ArrayList<String>();
            
            Connection con = Database2.connect();
            
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM evenement  ");

            while (rs.next()) {
                if (!events.contains(rs.getString("etat"))) {
                    events.add(rs.getString("etat"));
                    
                }

            }
            evenement.getItems().addAll(events);
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO
    }    

    @FXML
    private void test(ActionEvent event) {
        
       
        populate(evenement.getValue()); 
    }
    
    
    
      ObservableList<Evenement> oblist = FXCollections.observableArrayList();

    public void populate(String etat) {
        String sql = "select * from evenement where etat = '" + etat + "' ";
        Connection con = Database2.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Evenement(rs.getString("designation"),rs.getString("etat"), rs.getDate("dateDebut"),
                        rs.getDate("dateFin"), rs.getString("typeEvenement")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        table.setItems(oblist);
    }
}

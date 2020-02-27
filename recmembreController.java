package Controller;


import com.jfoenix.controls.JFXButton;


import Services.Reclamation2;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import Utils.MyConnection;

import java.sql.Statement;
import Utils.connection;

import java.sql.Date;
import javax.naming.spi.DirStateFactory.Result;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class recmembreController implements Initializable {

    @FXML
    private TableView<Reclamation2> clubTable;
    @FXML
    private TextField id;
    @FXML
    private TextField sujet;
    @FXML
    private TextField contenu;
    @FXML
    private DatePicker dateR;
    @FXML
    private TextField email;
    @FXML
    private TextField etat;
    @FXML
    private TextField typerec;
 
    
    @FXML
    private Button ajouter;

    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    
  @FXML
    private Pane mainpane;
    @FXML
    private Label msg;
    @FXML
    private TableColumn<Reclamation2, Integer> idcol;
    @FXML
    private TableColumn<Reclamation2, String> sujetcol;
    @FXML
    private TableColumn<Reclamation2, String> contenucol;
    @FXML
    private TableColumn<Reclamation2, Date> dateRcol;
    @FXML
    private TableColumn<Reclamation2, String> emailcol;
    @FXML
    private TableColumn<Reclamation2, String> etatcol;
    @FXML
    private TableColumn<Reclamation2, String> typereccol;
   
    
    
       ObservableList<Reclamation2> oblist = FXCollections.observableArrayList();
    public void populate() {
        String sql = "select * from reclamation";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Reclamation2(rs.getInt("id"), rs.getString("sujet"), rs.getString("contenu"),
                        rs.getDate("dateR"),rs.getString("etat"),rs.getString("email"), rs.getString("typerec")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clubTable.setItems(oblist);
    }

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        
        populate();
        idcol.setCellValueFactory(new PropertyValueFactory<>("idReclamation"));
        sujetcol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        contenucol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
     dateRcol.setCellValueFactory(new PropertyValueFactory<>("dateR"));
 etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        typereccol.setCellValueFactory(new PropertyValueFactory<>("typerec"));


        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
        id.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getIdReclamation()));
         sujet.setText(clubTable.getSelectionModel().getSelectedItem().getSujet());
        contenu.setText(clubTable.getSelectionModel().getSelectedItem().getContenu());
         dateR.setValue(clubTable.getSelectionModel().getSelectedItem().getDate().toLocalDate());
                         etat.setText(clubTable.getSelectionModel().getSelectedItem().getEtat());

              email.setText(clubTable.getSelectionModel().getSelectedItem().getEmail());
                typerec.setText(clubTable.getSelectionModel().getSelectedItem().getType());

                

              }

        });

    }
        
  
    
    
    
    @FXML
    private void ajouterbtn(ActionEvent event) throws SQLException {
        
        Reclamation2.InsertReclamation(sujet.getText(), contenu.getText(), java.sql.Date.valueOf(dateR.getValue()).toString(), etat.getText(), email.getText(), typerec.getText());
        populate();
        msg.setText("Réclamation ajoutée");
    
    
    }
    
    
    
    

    @FXML
    private void Modifrec(ActionEvent event) throws SQLException {
        
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de Modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Reclamation2.ModifReclamation(Integer.parseInt(id.getText()),sujet.getText(),contenu.getText(),java.sql.Date.valueOf(dateR.getValue()).toString(), etat.getText(),email.getText(),typerec.getText());
            populate();
            msg.setText("Réclamation Modifiée");
        }
        
        
        
        
    }

    @FXML
    private void Supprec(ActionEvent event) throws SQLException {
        
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            Reclamation2.SuppReclamation(Integer.parseInt(id.getText()));

            populate();
            msg.setText("Réclamation  Supprimée");
        }
        
        
        
        
    }


    
    
        
        
        
        
        
        
        
        
        
        
   
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;



import DataBase.connection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Services.Chambre;
import javafx.scene.layout.Pane;
public class ChambreController implements Initializable {

    @FXML
    private TableView<Chambre> chambreTable;

    @FXML
    private TableColumn<Chambre, Integer> idcol;

    @FXML
    private TableColumn<Chambre, String> stylecol;

    @FXML
    private TableColumn<Chambre, String> descriptioncol;

    @FXML
    private TableColumn<Chambre, Float> numerocol;

    @FXML
    private TableColumn<Chambre, String> nbpersonnecol;

    @FXML
    private TableColumn<Chambre, Float> prixcol;

    @FXML
    private TableColumn<Chambre, String> etatcol;

    @FXML
    private TextField id;

    @FXML
    private TextField style;

    @FXML
    private TextField description;

    @FXML
    private TextField numero;

    @FXML
    private TextField nbpersonne;

    @FXML
    private TextField prix;

    @FXML
    private TextField etat;

    private Button Retour;

    @FXML
    private Label msg;
    ObservableList<Chambre> oblist = FXCollections.observableArrayList();
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Pane Minepane;

    public void populate() {
        String sql = "select * from foyer";
        Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Chambre(rs.getInt("id"), rs.getString("style"), rs.getString("description"),
                        rs.getInt("numero"), rs.getString("nbpersonne"), rs.getFloat("prix"), rs.getString("etat")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChambreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        chambreTable.setItems(oblist);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populate();
        idcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        stylecol.setCellValueFactory(new PropertyValueFactory<>("style"));
        descriptioncol.setCellValueFactory(new PropertyValueFactory<>("description"));
        numerocol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        nbpersonnecol.setCellValueFactory(new PropertyValueFactory<>("nbpersonne"));
        prixcol.setCellValueFactory(new PropertyValueFactory<>("prix"));
        etatcol.setCellValueFactory(new PropertyValueFactory<>("etat"));

        chambreTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                id.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getId()));
                style.setText(chambreTable.getSelectionModel().getSelectedItem().getStyle());
                description.setText(chambreTable.getSelectionModel().getSelectedItem().getDescription());
                numero.setText(Integer.toString(chambreTable.getSelectionModel().getSelectedItem().getnumero()));
                nbpersonne.setText(chambreTable.getSelectionModel().getSelectedItem().getNbpersonne());
                prix.setText(Float.toString(chambreTable.getSelectionModel().getSelectedItem().getPrix()));
                etat.setText(chambreTable.getSelectionModel().getSelectedItem().getEtat());
            }

        });

    }

   
   
   

  

    @FXML
    private void InsertVoiture(ActionEvent event) throws SQLException {
        
        Chambre.InsertChambre(style.getText(), description.getText(), Integer.parseInt(numero.getText()), nbpersonne.getText(), Float.parseFloat(prix.getText()), etat.getText());
        populate();
        msg.setText("Chambre ajoutée");
        
        
        
    }

    @FXML
    private void ModifVoiture(ActionEvent event) throws SQLException {
        
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de Modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            Chambre.ModifChambre(Integer.parseInt(id.getText()), style.getText(), description.getText(), Integer.parseInt(numero.getText()), nbpersonne.getText(), Float.parseFloat(prix.getText()), etat.getText());
            populate();
            msg.setText("Chambre Modifiée");
        }
        
        
        
        
    }

    @FXML
    private void SuppVoiture(ActionEvent event) throws SQLException {
        
         Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            Chambre.SuppChambre(Integer.parseInt(id.getText()));

            populate();
            msg.setText("Chambre Supprimée");
        }
        
        
        
        
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTextField;
import Entites.Club;
import Entites.Evenement;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import Services.ServiceClubEleve;
import Services.ServiceEvenementParticipant;
import Utils.DataBase;
import Utils.Database2;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class participerController implements Initializable {
Connection c = DataBase.getInstance().getConnection();
 public participerController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
          Statement ste;
    @FXML
    private TableView<Evenement> clubTable;
    @FXML
    private TableColumn<Evenement, Integer> idcl;
    @FXML
    private TableColumn<Evenement, String> idnom;
  
    @FXML
    private Button sincrire;
    ObservableList<Evenement> oblist = FXCollections.observableArrayList();
    @FXML
    private JFXTextField idEvenement;
    @FXML
    private JFXTextField idUser;
    @FXML
    private TableColumn<Evenement, Integer> nbrPlaces;
    @FXML
    private JFXTextField nbPlaces;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            populate();
            idUser.setDisable(true);
        
           String req = "select id,nom,prenom,email,cin,username,password from fos_user where demande='connected' ";
       
        try {
           /* String[] args = null;
            Webcamtest.main(args);*/
           
             ste = c.createStatement();
            ResultSet rs1 = ste.executeQuery(req);

            while (rs1.next())
            {
                
                      idUser.setText(rs1.getString("id"));


           
                }

        } catch (SQLException ex) {
            System.out.println("asadad");
        
        }
        // TODO
    idcl.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
        idnom.setCellValueFactory(new PropertyValueFactory<>("designation"));
                nbrPlaces.setCellValueFactory(new PropertyValueFactory<>("nbPlaces"));

         clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                   nbPlaces.setDisable(true);
                idEvenement.setDisable(true);
                idEvenement.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getIdEvenement()));
                               nbPlaces.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getNbPlaces()));
                
             
          

            }

        });
    }    
    
    
           private void warning(String txt){
        Alert alert= new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional <ButtonType> action = alert.showAndWait();
    }
    
private boolean isEmpty() {
        if (idUser.getText()== null  || idUser.getText().trim().isEmpty()) {
            warning("Veuillez entrez votre identifiant");
            return true;
        }
return false ; 
}
    
    
      public void populate() {
        String sql = "select  * from evenement where nbPlaces>=1 and etat='accepté'";
        Connection con = Database2.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Evenement(rs.getInt("idEvenement"), rs.getString("designation"),rs.getInt("nbPlaces")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(EvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }
       clubTable.setItems(oblist);
    }

    @FXML
    private void participerEvent(ActionEvent event) throws SQLException {
        
        
        
               ServiceEvenementParticipant srv = new ServiceEvenementParticipant();
               srv.UpvoteCategorie(Integer.parseInt(idEvenement.getText()),Integer.parseInt(nbPlaces.getText())-1);
               srv.InsertEvenementParticipant(Integer.parseInt(idUser.getText()), Integer.parseInt(idEvenement.getText()));
                clearFields();
        JOptionPane.showMessageDialog(null,"Bien Inscrit");
        populate();
    }
       public void clearFields(){
          idUser.clear();
          idEvenement.clear();
          nbPlaces.clear();
          
      }
    
}

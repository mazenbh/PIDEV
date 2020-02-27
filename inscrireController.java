/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXTextField;
import Entites.Club;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import Services.ServiceClubEleve;
import Utils.DataBase;
import Utils.Database2;
import java.sql.Statement;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class inscrireController implements Initializable {
Connection c = DataBase.getInstance().getConnection();
 public inscrireController() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
          Statement ste;

    @FXML
    private TableColumn<Club, Integer> idcl;
    @FXML
    private TableColumn<Club, String> idnom;
    @FXML
    private JFXTextField idClub;
    @FXML
    private JFXTextField idMembre;
    @FXML
    private Button sincrire;
    ObservableList<Club> oblist = FXCollections.observableArrayList();
    @FXML
    private TableView<Club> clubTable;
    @FXML
    private AnchorPane lbl;

    /**
     * Initializes the controller class.
     */
    public void populate() {
        String sql = "select * from club";
        Connection con = Database2.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Club(rs.getInt("idClub"), rs.getString("nomClub"), rs.getDate("dateCreation"),
                        rs.getString("emailClub"), rs.getInt("idPresident"), rs.getString("imageClub")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        clubTable.setItems(oblist);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       idClub.setDisable(true);
       idMembre.setDisable(true);
          String req = "select id,nom,prenom,email,cin,username,password from fos_user where demande='connected' ";
       
        try {
           /* String[] args = null;
            Webcamtest.main(args);*/
           
             ste = c.createStatement();
            ResultSet rs1 = ste.executeQuery(req);

            while (rs1.next())
            {
                
                      idMembre.setText(rs1.getString("id"));


           
                }

        } catch (SQLException ex) {
            System.out.println("asadad");
        
        }
        populate();
        idcl.setCellValueFactory(new PropertyValueFactory<>("idClub"));
        idnom.setCellValueFactory(new PropertyValueFactory<>("nomClub"));
        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idClub.setDisable(true);
                idClub.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getIdClub()));
                idMembre.setDisable(true);

            }

        });
    }

    @FXML
    public void InsertClubEleve(ActionEvent event) throws ClassNotFoundException, SQLException {

        ServiceClubEleve srv = new ServiceClubEleve();
        srv.InsertClubEleve(Integer.parseInt(idClub.getText()), Integer.parseInt(idMembre.getText()), "");
        clearFields();
        JOptionPane.showMessageDialog(null, "Bien Inscrit");

    }

    public void clearFields() {
        idClub.clear();
        idMembre.clear();

    }

}

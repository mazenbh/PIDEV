/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Menu;
import Entites.abonneC;
import Services.servicecantine;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class MenuController implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private TableView<Menu> tabm;
    @FXML
    private TableColumn<Menu, String> j;
    @FXML
    private TableColumn<Menu, String> pd;
    @FXML
    private TableColumn<Menu, String> dj;
    @FXML
    private TableColumn<Menu, String> dn;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
         servicecantine  es = new servicecantine();
        System.out.println("fxml.AfficherAbonneController.initialize()");
        ArrayList<Menu> arrayList = null;
        try {
            arrayList = (ArrayList<Menu>) es.getmenu();
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("zzzzzz");
         ObservableList obs = FXCollections.observableArrayList(arrayList);
        tabm.setItems(obs);
        
        

       
         j.setCellValueFactory(new PropertyValueFactory<Menu,String>("jour"));
         pd.setCellValueFactory(new PropertyValueFactory<Menu,String>("petitdej"));
          dj.setCellValueFactory(new PropertyValueFactory<Menu,String>("dej"));
        dn.setCellValueFactory(new PropertyValueFactory<Menu,String>("dinner"));
       
      
           
    }    
    
}

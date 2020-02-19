/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.User;
import Services.UserService;
import Utils.DataBase;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class ArchiveController implements Initializable {

    @FXML
    private TableView<User> tabE;
   @FXML
    private TableColumn<User, Integer> id;
    @FXML
    private TableColumn<User, String> nom;
     @FXML
    private TableColumn<User, String> prenom;
      @FXML
    private TableColumn<User, String> email;
      @FXML
    private TableColumn<User, String> cin;
     
        @FXML
    private TableColumn<User, String> sexe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UserService  es = new UserService();
        System.out.println("fxml.ArchiveController.initialize()");
        ArrayList<User> arrayList = null;
        arrayList = (ArrayList<User>) es.selectArchive();
        System.out.println("zzzzzz");
         ObservableList obs = FXCollections.observableArrayList(arrayList);
        tabE.setItems(obs);


        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
       prenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
         cin.setCellValueFactory(new PropertyValueFactory<User,String>("cin"));
         
           sexe.setCellValueFactory(new PropertyValueFactory<User,String>("sexe"));
        
        
        // TODO
    }    
    
    
    
   


}

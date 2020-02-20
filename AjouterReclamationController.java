package GUI;

import com.jfoenix.controls.JFXButton;


import entities.Reclamation2;
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
import DataBase.MyConnection;
import Services.ServiceReclamation2;
import java.sql.Statement;
import DataBase.connection;
import java.sql.Date;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation2> clubTable;
    @FXML
    private TableColumn<Reclamation2, Integer> idclubcol;
    @FXML
    private TableColumn<Reclamation2, String> nomclubcol;
    @FXML
    private TableColumn<Reclamation2, Date> datecol;
    @FXML
    private TableColumn<Reclamation2, String> emailcol;
    @FXML
    private TableColumn<Reclamation2, String> idpcol;
    @FXML
    private TableColumn<Reclamation2, String> imgcol;
     @FXML
        private TableColumn<Reclamation2, String> imgcol1;

    @FXML
    private TextField idClub;
    @FXML
    private TextField nomClub;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private TextField emailClub;

    @FXML
    private TextField imageClub;
     @FXML
    private TextField imageClub1;
    @FXML
    private Button ajouter;

    ObservableList<Reclamation2> oblist = FXCollections.observableArrayList();
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField idPresident;
  @FXML
    private Pane mainpane;
    @FXML
    private Label msg;
    @FXML
    private JFXButton choose;
   
  
    /**
     * Initializes the controller class.
     */
    
    
      public void populate() throws SQLException {
        String sql = "Select* from reclamation where corbeille=0 and archive=0";
        
         Connection con = connection.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                oblist.add(new Reclamation2(rs.getInt("id"), rs.getString("sujet"), rs.getString("contenu"),
                       rs.getDate("dateR"), rs.getString("email"), rs.getString("etat"), rs.getString("typerec")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
       clubTable.setItems(oblist);
    }
      public void clearFields(){
          idClub.clear();
          idPresident.clear();
          dateCreation.setValue(LocalDate.now());
          emailClub.clear();
          imageClub.clear();
       imageClub1.clear();

          
          nomClub.clear();
          
      }

      
         @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            populate();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        idclubcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        nomclubcol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("email"));
        idpcol.setCellValueFactory(new PropertyValueFactory<>("etat"));
        imgcol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
    imgcol1.setCellValueFactory(new PropertyValueFactory<>("typerec"));

        
        
        
       
//        fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", ""));
//                        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "'.jpg'"));

        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idClub.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getIdReclamation()));
                nomClub.setText(clubTable.getSelectionModel().getSelectedItem().getSujet());
                dateCreation.setValue(clubTable.getSelectionModel().getSelectedItem().getDate().toLocalDate());
                emailClub.setText(clubTable.getSelectionModel().getSelectedItem().getEmail());
                idPresident.setText(clubTable.getSelectionModel().getSelectedItem().getEtat());
                imageClub.setText(clubTable.getSelectionModel().getSelectedItem().getContenu());
                imageClub1.setText(clubTable.getSelectionModel().getSelectedItem().getType());

                 

            }

        });

    }
    
    
   
    // TODO
   
//private void handleBrowser(ActionEvent event){
//    stage= (Stage) AnchorPane.ge.getWindow();
//file =fileChooser.showOpenDialog(stage);
//if (file!=null){
//    System.out.println(""+file.getAbsolutePath());
//    imageClub = new Image (file.getAbsoluteFile().toURI().toString());
//    imgview.setImage(imageClub);
//}
//}





    @FXML
      public void ModifClub(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de Modifier?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            ServiceReclamation2.ModifClub(Integer.parseInt(idClub.getText()), nomClub.getText(), java.sql.Date.valueOf(dateCreation.getValue()).toString(), emailClub.getText(),idPresident.getText(), imageClub.getText(),imageClub1.getText());
            populate();
            clearFields();
            populate();
        }

    }

      
      
    @FXML
    public void SuppClub(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Comfirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            ServiceReclamation2.SuppClub(Integer.parseInt(idClub.getText()));

            populate();
            clearFields();
       
            populate();
        }

    }

    @FXML
    private void ajouterbtn(ActionEvent event) throws IOException {
           mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("ajouterReclamation.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void importerimage(ActionEvent event) {
    }

}
    


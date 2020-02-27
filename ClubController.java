/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import Entites.Club;
import Entites.Notifications;
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
import Services.ServiceClub;
import Utils.Database2;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class ClubController implements Initializable {

    @FXML
    private TableView<Club> clubTable;
    @FXML
    private TableColumn<Club, Integer> idclubcol;
    @FXML
    private TableColumn<Club, String> nomclubcol;
    @FXML
    private TableColumn<Club, String> datecol;
    @FXML
    private TableColumn<Club, String> emailcol;
    @FXML
    private TableColumn<Club, Integer> idpcol;
    @FXML
    private TableColumn<Club, String> imgcol;
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
    private Button ajouter;

    @FXML
    private Label msg;
    private FileChooser fileChooser;
    private File file;
    private Stage stage;
    private Image image;
    private AnchorPane anchorPane;
    private Desktop desktop = Desktop.getDesktop();
    private FileInputStream fis;
    ObservableList<Club> oblist = FXCollections.observableArrayList();
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField idPresident;
    @FXML
    private Pane mainpane;
    @FXML
    private ImageView imgview;
    @FXML
    private JFXButton choose;

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

    public void clearFields() {
        idClub.clear();
        idPresident.clear();
        dateCreation.setValue(LocalDate.now());
        emailClub.clear();
        imageClub.clear();
        imgview.setImage(null);
        nomClub.clear();

    }
//      private void Browse(){
//          file = fileChooser.showOpenDialog(primaryStage);
//          if (file !=null ){
//              imageClub.setText(file.getAbsolutePath());
//              image= new Image(file.toURI().toString(),100,150,true,true);
//      
//          }
//      }

//      public void fillComboBox(){
//           String sql = "select username from fos_user";
//           Connection con = Database.connect();
//           
//            try {
//            ResultSet rs = con.createStatement().executeQuery(sql);
//            while (rs.next()) {
//                oblist.add(new User(rs.getInt("id"), rs.getString("username")));
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            idClub.setDisable(true);
        populate();
        idclubcol.setCellValueFactory(new PropertyValueFactory<>("idClub"));
        nomclubcol.setCellValueFactory(new PropertyValueFactory<>("nomClub"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("dateCreation"));
        emailcol.setCellValueFactory(new PropertyValueFactory<>("emailClub"));
        idpcol.setCellValueFactory(new PropertyValueFactory<>("idPresident"));
        imgcol.setCellValueFactory(new PropertyValueFactory<>("imageClub"));

//        fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", ""));
//                        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images", "'.jpg'"));
        clubTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                idClub.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getIdClub()));
                nomClub.setText(clubTable.getSelectionModel().getSelectedItem().getNomClub());
                dateCreation.setValue(clubTable.getSelectionModel().getSelectedItem().getDateCreation().toLocalDate());
                emailClub.setText(clubTable.getSelectionModel().getSelectedItem().getEmailClub());
                idPresident.setText(Integer.toString(clubTable.getSelectionModel().getSelectedItem().getIdPresident()));
                imageClub.setText(clubTable.getSelectionModel().getSelectedItem().getImageClub());
                Image image = new Image(imageClub.getText());
                imgview.setImage(image);

            }

        });

    }

    private void handleBrowser(ActionEvent event) {
        stage = (Stage) anchorPane.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }

    @FXML
    public void ModifClub(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        if (isEmpty()) {
            return;
        } else { 
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de Modifier?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK ) try {
                ServiceClub.ModifClub(Integer.parseInt(idClub.getText()), nomClub.getText(), java.sql.Date.valueOf(dateCreation.getValue()).toString(), emailClub.getText(), Integer.parseInt(idPresident.getText()), imageClub.getText());
                populate();
                clearFields();
                Notifications n = new Notifications();
                n.displayTray("Club", "bien modifié");
                populate();
            }     catch (Exception e){    JOptionPane.showMessageDialog(null,"Veuillez sélectionner une ligne du table");}
 
            
        
    }}
    @FXML
    public void SuppClub(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr de supprimer?");
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {

            ServiceClub.SuppClub(Integer.parseInt(idClub.getText()));

            populate();
            clearFields();
            Notifications n = new Notifications();
            n.displayTray("Club", "bien supprimé");
            populate();
        }

    }

    @FXML
    private void ajouterbtn(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("/Fxml/ajoutClub.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void importerimage(ActionEvent event) {

        FileChooser fc = new FileChooser();
        File selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {

            imageClub.setText(selectedfile.toURI().toString());
            Image image = new Image(imageClub.getText());
            imgview.setImage(image);

        }

    }

    private boolean validatorMail(String s) {
        Pattern p = Pattern.compile("[a-zA-Z]+[a-zA-Z0-9._-]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("Le champ Mail n'est pas valide !");
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
            alert.setContentText("Le champ idClub n'est pas valide !");
            alert.showAndWait();
            return false;
        }

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
            alert.setContentText("id president invalide !");
            alert.showAndWait();
            return false;
        }

    }
     
     
    
          private boolean validatorString(String s) {
        Pattern p = Pattern.compile("[a-zA-Z é]+");
        Matcher controler = p.matcher(s);
        if (controler.matches()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validation Champs");
            alert.setHeaderText(null);
            alert.setContentText("nom club invalide!");
            alert.showAndWait();
            return false;
        }

    }
          

    private boolean isEmpty() {
        if (validatorInt(idClub.getText()) == false) {
            warning("Veuillez donner un id du club valide !");
            return true;
        }
        if (validatorString(nomClub.getText())==false) {
            return true;
        }

//        if (dateCreation.getValue() == null || dateCreation.getValue().isBefore(LocalDate.now())) {
//            warning("Veuillez saisir une date de création valide  pour l'evenement!");
//            return true;
//        }
        if (validatorMail(emailClub.getText()) == false) {
            return true;
        }
        if (validatorInt2(idPresident.getText())==false ) {
            
            return true;
        }

        if (imageClub.getText() == null || imageClub.getText().trim().isEmpty()) {
            warning("Veuillez choisir une image pour le club !");
            return true;
        }

        return false;
    }

}

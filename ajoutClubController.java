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
import static java.awt.Image.SCALE_SMOOTH;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import Services.ServiceClub;
import Utils.Database2;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class ajoutClubController implements Initializable {

    @FXML
    private TextField nomClub;
    @FXML
    private TextField emailClub;
    @FXML
    private ComboBox idPresident;
    @FXML
    private DatePicker dateCreation;
    @FXML
    private Button comfirmer;
    @FXML
    private TextField imageClub;
    Button open;
    Button load;
    private PreparedStatement store, retrieve;
    private String storeStatement = "INSERT INTO club (imageClub) values (?)";
    private String retrieveStatement = "SELECT imageClub FROM club WHERE_idClub=?";

    ObservableList<Club> oblist = FXCollections.observableArrayList();

    String filename = null;
    byte[] person_image = null;
    private File file;
    FileChooser filechooser;
    Image image;
    @FXML
    private JFXButton choose;
    @FXML
    private ImageView imgview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//         TODO
        String sql = "select  id,username from fos_user ";
        Connection con = Database2.connect();
        oblist.clear();
        try {
            ResultSet rs = con.createStatement().executeQuery(sql);
            while (rs.next()) {
                idPresident.getItems().add(rs.getString("username"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

        dateCreation.setValue(LocalDate.now());
    }

    private void warning(String txt) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention");
        alert.setHeaderText(null);
        alert.setContentText(txt);
        Optional<ButtonType> action = alert.showAndWait();
    }

//private void btnImageActionPerformed(java.awt.event.ActionEvent evt){
//    JFileChooser chooser = new JFileChooser();
//    chooser.showOpenDialog(null);
//    File f = chooser.getSelectedFile();
//    filename = f.getAbsolutePath();
//    ImageIcon imageIcon = new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lbl_img.getW, lbl_img.getHeight(), SCALE_SMOOTH));
//    lbl_img.setIcon(imageIcon);
//    try {
//        File image = new File(filename);
//        FileInputStream fis = new FileInputStream(image);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buf = new byte[1024];
//        for (int readNum;(readNum=fis.read(buf))!=-1;){
//            bos.write(buf,0,readNum);
//        }
//        person_image=bos.toByteArray() ; 
//    }
//    catch(Exception e){
//        JOptionPane.showMessageDialog(null, e);
//    }
//}
//        open.setOnAction(e->{
//            FileChooser fileChooser = new FileChooser();
//            File file = fileChooser.showOpenDialog(open.getScene().getWindow());
//            if(file != null){
//                imageClub.setText(file.getAbsolutePath());
//                image = new Image(file.toURI().toString(),100,150,true,true);
//            }
//            
//        };
//        load.setOnAction(e->loadImage());
//    public void openAndSave(){
//        FileChooser fileChooser = new FileChooser();
//        File file = fileChooser.showOpenDialog(open.getScene().getWindow());
//        try {
//            FileInputStream fis = new FileInputStream(file);
//            store.setBinaryStream(11, (InputStream)fis,(int)file.length());
//            store.execute();
//            Image image = new Image(fileInputStream);
//        } catch (IOException | SQLException  e) {
//            System.out.println(e.getMessage());
//        }
//        
//    }
//public void loadImage(){
////        try {
////            retrieve.setInt(1, 1);
////            ResultSet resultSet = retrieve.executeQuery();
////            InputStream inputStream =blob.getBinaryStream();
////            Image image = new Image(inputStream);
////            imageView.setImage(image);
////        } catch (SQLException ex) {
////            System.out.println(ex.getMessage());
////        }
//btnImage.setFont(Font.font("SanSerif",15));
//btnImage.setOnAction(e-> {
//    file = filechooser.showOpenDialog(null);
//    if (file != null){
//        imageClub.setText(file.getAbsolutePath());
//        image = new Image(file.toURI().toString(),100,150,true,true);
//        imageview = new ImageView(image);
//        imageview.setFitWidth(100);
//        imageview.setFitHeight(150);
//        imageview.setPreserveRatio(true);
//        
//        
//        
//    }
//
//    });
//}    
//    
//         public void fillComboBox(){
//          String sql = "select username from club";
//           Connection con = Database.connect();
//           idPresident.setItems(oblist);
//            try {
//            ResultSet rs = con.createStatement().executeQuery(sql);
//            while (rs.next()) {
//                oblist.add(new Club(rs.getInt("idClub"), rs.getString("nomClub")));
//            }
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(ClubController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//      }
    @FXML
    public void InsertClub(ActionEvent event) throws ClassNotFoundException, SQLException, AWTException {
        if (isEmpty()){
        return;}
        else{
        ServiceClub.InsertClub(nomClub.getText(), java.sql.Date.valueOf(dateCreation.getValue()).toString(), emailClub.getText(), id_president(), imageClub.getText());
        clearFields();
        Notifications n = new Notifications();
        n.displayTray("Club", "bien insérér");}
    }

    public void clearFields() {
        emailClub.clear();
        imageClub.clear();
        nomClub.clear();
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
            alert.setContentText("nom invalide!");
            alert.showAndWait();
            return false;
        }

    }
    private boolean isEmpty() {
      if (validatorString(nomClub.getText()) == false) {
            return true;
        }
      if (validatorMail(emailClub.getText()) == false){
          return true ;
      }
      if (idPresident.getValue() == null ) {
            warning("Veuillez selectionner un président du club!");
            return true;
        }
      if (dateCreation.getValue() == null || dateCreation.getValue().isBefore(LocalDate.now())) {
            warning("Veuillez saisir une date de création valide  pour l'evenement!");
            return true;
        }
            if (imageClub.getText() == null || imageClub.getText().trim().isEmpty()) {
            warning("Veuillez choisir une image pour le club !");
            return true;
        }
    

        return false;
    }
   
    private int id_president() {
        Connection con = Database2.connect();

        int k = 0;

        try {
            PreparedStatement pt = con.prepareStatement("select id from fos_user where username=?");
            pt.setString(1, (String) idPresident.getValue());

            ResultSet rs = pt.executeQuery();

            while (rs.next()) {
                k = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceClub.class.getName()).log(Level.SEVERE, null, ex);
        }
        return k;
    }
    
    @FXML
    private void importerimage(ActionEvent event) throws FileNotFoundException {

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

}

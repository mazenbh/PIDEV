/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Mail;
import Entites.User;
import Services.UserService;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.awt.AWTException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class AfficherEtudiantController implements Initializable {
 
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
    private JFXTextField search;
    private AnchorPane apaffet;
    @FXML
    private JFXButton supp;
    @FXML
    private JFXButton imp;
    @FXML
    private JFXButton stat;
    @FXML
    private Pane mainpane;
    @FXML
    private JFXTextField emailsend;
    @FXML
    private JFXTextArea message;
    @FXML
    private JFXTextField sujet;
    @FXML
    private JFXButton contactermail;
    @FXML
    private JFXTextField lal;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        UserService  es = new UserService();
        System.out.println("fxml.AfficherEtudiantController.initialize()");
        ArrayList<User> arrayList = null;
        try {
            arrayList = (ArrayList<User>) es.getAllPE();
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("zzzzzz");
         ObservableList obs = FXCollections.observableArrayList(arrayList);
        tabE.setItems(obs);

        ///////////////////////////////////////////////////
        
        ////////////////////////////////////////////////

        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
       prenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
         cin.setCellValueFactory(new PropertyValueFactory<User,String>("cin"));
         
           sexe.setCellValueFactory(new PropertyValueFactory<User,String>("sexe"));
        
           
           /////////////////////////////////////////////////////////
           
             
           
           //////////////////////////////////////////////////////////
        
         
    }
    Mail u=new Mail();
    @FXML
     void sendaction(ActionEvent event) {
        u.sendMail(emailsend.getText(), sujet.getText(), message.getText());

    }  
     
     
    

  /*  private void recherchefunction(ActionEvent event) {
        UserService fs = new UserService();
        ArrayList<User> formations = new ArrayList<>();
        try {
            formations = (ArrayList<User>) fs.rechercherUserrr(
                    search.getText());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<User> obsl = FXCollections.observableArrayList(formations);
        tabE.setItems(obsl);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        
        cin.setCellValueFactory(new PropertyValueFactory<User,String>("cin"));
      
           
           sexe.setCellValueFactory(new PropertyValueFactory<User,String>("sexe"));
    }
   
    */
    
    /*private void rechercherNom(javafx.scene.input.KeyEvent event) {

        UserService fs = new UserService();
        ArrayList<User> formations = new ArrayList<>();
        try {
            formations = (ArrayList<User>) fs.rechercherNom(
                    cherche.getText());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<User> obsl = FXCollections.observableArrayList(formations);
        tabE.setItems(obsl);
        id.setCellValueFactory(new PropertyValueFactory<User, Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User, String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User, String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        cin.setCellValueFactory(new PropertyValueFactory<User, String>("cin"));
        sexe.setCellValueFactory(new PropertyValueFactory<User, String>("sexe"));

        // prixtotale.setCellValueFactory(new PropertyValueFactory<>("5"));
    }

    @FXML
    private void rechercherNom(ActionEvent event) {
    }
*/
    
 /*   private void recherche_function(ActionEvent event) {
        UserService fs = new UserService();
        ArrayList<User> formations = new ArrayList<>();
        try {
            formations = (ArrayList<User>) fs.rechercheEvennement(
                    cherche.getText());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<User> obsl = FXCollections.observableArrayList(formations);
        tabE.setItems(obsl);
        id.setCellValueFactory(new PropertyValueFactory<User,Integer>("id"));
        nom.setCellValueFactory(new PropertyValueFactory<User,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<User,String>("prenom"));
        email.setCellValueFactory(new PropertyValueFactory<User,String>("email"));
        cin.setCellValueFactory(new PropertyValueFactory<User,String>("cin"));
        sexe.setCellValueFactory(new PropertyValueFactory<User,String>("sexe"));
        
        
    }
*/
   
    @FXML
          public void SupprimerUser(ActionEvent event) throws IOException, AWTException
    {
        Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirmation Dialog");
	alert.setHeaderText("Do you want to save your current changes?");
	alert.setContentText("");
	
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){ 
        UserService cs = new UserService();
        User c = tabE.getSelectionModel().getSelectedItem();
        tabE.getItems().removeAll(tabE.getSelectionModel().getSelectedItem());
        cs.delete(c);
        }
    }

  /*  @FXML
    private void rechercheEvennement(ActionEvent event) {
    }*/

        
        @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\mazen\\Documents\\NetBeansProjects\\123456\\pppp.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(6);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("Id");
       table.addCell("nom");
       table.addCell("prenom");
       table.addCell("email");
       table.addCell("cin");
       table.addCell("sexe");
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Liste Des Etudiants");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:/Users/mazen/Documents/NetBeansProjects/123456/download.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symfony", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` FROM `fos_user`where roles='etudiant' or roles='abonne'");
       while(rs.next()){
       table.addCell(rs.getString("id"));
            table.addCell(rs.getString("nom"));

                   table.addCell(rs.getString("prenom"));

                          table.addCell(rs.getString("email")); 
                          
                              table.addCell(rs.getString("cin"));
                              
                              table.addCell(rs.getString("sexe"));
       }
       document.add(table);
        JOptionPane.showMessageDialog(
                null, " données exportées en pdf avec succés ");
               document.close();
           
            

        } catch (Exception e) {

            System.out.println("Error PDF");
            System.out.println(e.getStackTrace());
            System.out.println(e.getMessage());
        }
   
    }
     
   ObservableList<User> oobservableList;
   
   /* private void archiverSelectedRow(ActionEvent event) {

        ObservableList<User> dataListRemove = FXCollections.observableArrayList();
        UserService rS = new UserService();

        User rec = tabE.getSelectionModel().getSelectedItem();
        rS.toArchive(rec);
        dataListRemove.add(rec);

      
               
            
        
        observableList.removeAll(dataListRemove);
        tabE.refresh();

    }*/
        
   private void go(ActionEvent event) throws IOException {
        Stage stage = (Stage) apaffet.getScene().getWindow();
                System.out.println("redirection to login");
            Parent root=FXMLLoader.load(getClass().getClassLoader().getResource("Fxml/archive.fxml"));
      
            //System.err.println(info);
          
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        
    }

    @FXML
    private void aya(ActionEvent event) throws IOException {
        
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/stat.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
        
    }

   @FXML
    private void Rechercher() {

        lal.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                UserService rs = new UserService();
                ObservableList obeListe = FXCollections.observableList(rs.rechercherNomEt(newValue));
                tabE.setItems(obeListe);
            } catch (SQLException ex) {
                Logger.getLogger(AfficherEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
       

        });
    }
   

  
    }    
    


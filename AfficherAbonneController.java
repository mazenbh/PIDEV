/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entites.Mail;
import Entites.User;
import Entites.abonneC;
import Services.UserService;
import Services.servicecantine;
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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class AfficherAbonneController implements Initializable {

    @FXML
    private TableColumn<abonneC, Integer> id;
    @FXML
    private TableColumn<abonneC, Integer> cin;
    @FXML
    private TableColumn<abonneC, String> type;
    @FXML
    private TableColumn<abonneC, String> dure;
    @FXML
    private TableColumn<abonneC, String> nom;
    @FXML
    private TableColumn<abonneC, String> prenom;
    @FXML
    private AnchorPane mainpane;
    @FXML
    private TableView<abonneC> tabaa;
    @FXML
    private JFXButton supprimer;
    @FXML
    private JFXButton statistique;
    @FXML
    private JFXButton conatacter;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField sujet;
    @FXML
    private JFXButton imprimer;
    @FXML
    private JFXTextArea message;
    @FXML
    private JFXTextField lal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         servicecantine  es = new servicecantine();
        System.out.println("fxml.AfficherAbonneController.initialize()");
        ArrayList<abonneC> arrayList = null;
        try {
            arrayList = (ArrayList<abonneC>) es.getAllPAC();
            
        } catch (SQLException ex) {
            Logger.getLogger(AfficherEtudiantController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("zzzzzz");
         ObservableList obs = FXCollections.observableArrayList(arrayList);
        tabaa.setItems(obs);
        
        

        id.setCellValueFactory(new PropertyValueFactory<abonneC,Integer>("id_c"));
         cin.setCellValueFactory(new PropertyValueFactory<abonneC,Integer>("cin"));
         type.setCellValueFactory(new PropertyValueFactory<abonneC,String>("type_abonement"));
          dure.setCellValueFactory(new PropertyValueFactory<abonneC,String>("dureé"));
        nom.setCellValueFactory(new PropertyValueFactory<abonneC,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<abonneC,String>("prenom"));
      
           

    }  
    
    
        @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:\\Users\\mazen\\Documents\\NetBeansProjects\\123456\\abone.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(6);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("id_C");
       table.addCell("cin");
       table.addCell("type_abonement");
       table.addCell("durée");
       table.addCell("nom");
       table.addCell("prenom");
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("Liste Des abonnes");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:/Users/mazen/Documents/NetBeansProjects/123456/download.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symfony", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT `id_c`, `cin`, `type_abonement`, `dureé`, `nom`, `prenom` FROM `cantine`");
       while(rs.next()){
       table.addCell(rs.getString("id_c"));
            table.addCell(rs.getString("cin"));

                   table.addCell(rs.getString("type_abonement"));

                          table.addCell(rs.getString("dureé")); 
                          
                              table.addCell(rs.getString("nom"));
                              
                              table.addCell(rs.getString("prenom"));
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
    
    
    @FXML
       public void Supprimerabonne(ActionEvent event) throws IOException, AWTException
    {
        
        Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("Confirmation Dialog");
	alert.setHeaderText("Do you want to save your current changes?");
	alert.setContentText("");
	
	Optional<ButtonType> result = alert.showAndWait();
	if (result.get() == ButtonType.OK){ 
        servicecantine cs = new servicecantine();
        abonneC c = tabaa.getSelectionModel().getSelectedItem();
        tabaa.getItems().removeAll(tabaa.getSelectionModel().getSelectedItem());
        cs.supprimerAbonné(c);
        }
        
    }
    
       
        Mail u=new Mail();
    @FXML
     void sendaction(ActionEvent event) {
        u.sendMail(email.getText(), sujet.getText(), message.getText());

    }  

    
     
     @FXML
    private void aya(ActionEvent event) throws IOException {
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/statcantine.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
    }
    
     @FXML
    private void Rechercher() {

        lal.textProperty().addListener((observable, oldValue, newValue) -> {
            servicecantine rs = new servicecantine();
            ObservableList obeListe = null;
            try {
                obeListe = FXCollections.observableList(rs.rechercherNomab(newValue));
            } catch (SQLException ex) {
                Logger.getLogger(AfficherAbonneController.class.getName()).log(Level.SEVERE, null, ex);
            }
            tabaa.setItems(obeListe);
            
       

        });
    }
}

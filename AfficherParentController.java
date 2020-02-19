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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class AfficherParentController implements Initializable {

      ObservableList<User> observableList;
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
    @FXML
    private JFXButton supp;
    @FXML
    private JFXButton imp;
    @FXML
    private JFXButton contacter;
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
    private JFXTextField search;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         UserService  es = new UserService();
        System.out.println("fxml.AfficherParentController.initialize()");
        ArrayList<User> arrayList = null;
        try {
            arrayList = (ArrayList<User>) es.getAllPP();
            
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
           
             //////////////////////////////////////////////
    }  
    
    @FXML
       public void SupprimerUser(ActionEvent event) throws IOException, AWTException
    {
        UserService cs = new UserService();
        User c = tabE.getSelectionModel().getSelectedItem();
        tabE.getItems().removeAll(tabE.getSelectionModel().getSelectedItem());
        cs.delete(c);
        
    }
       Mail u=new Mail();
    @FXML
     void sendaction(ActionEvent event) {
        u.sendMail(emailsend.getText(), sujet.getText(), message.getText());

    }  

    
    
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
            p.add("Liste Des Parents");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:/Users/mazen/Documents/NetBeansProjects/123456/download.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/symfony", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` FROM `fos_user`where roles='parent'");
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

    @FXML
    private void aya(ActionEvent event) throws IOException {
               mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/Fxml/stat.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
        
    }
    
}

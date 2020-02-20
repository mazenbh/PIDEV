/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;

import javafx.scene.control.TableView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import DataBase.MyConnection;



import com.jfoenix.controls.JFXButton;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.control.cell.PropertyValueFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDatePicker;

import javafx.scene.input.MouseEvent;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import com.jfoenix.controls.JFXListView;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.objects.NativeMath.round;
import Services.ReclamationServices;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfPTable;
import com.jfoenix.controls.JFXTextField;
import entities.Reclamation;


import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javax.swing.Action;



import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class BReclamationController implements Initializable {
    private Reclamation selectedReservation;
    @FXML
    private TableView<Reclamation> Table;
    @FXML
    private TableColumn<Reclamation, String> typeCol;
    @FXML
    private TableColumn<Reclamation, String> sujetCol;
    @FXML
    private TableColumn<Reclamation, String> userCol;
    @FXML
    private TableColumn<Reclamation, String> dateCol;

    ObservableList<Reclamation> observableList;
    private Pane s;
    @FXML
    private TableColumn<Reclamation, String> check;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button archiverBtn;
    @FXML
    private Button restaurerBtn;
    @FXML
    private Label newLbl;
    @FXML
    private Label listeLbl;
    @FXML
    private Label archiveLbl;
    @FXML
    private Label corbeilleLbl;
    @FXML
    private Button afficherBtn;
    @FXML
    private Button bar;
    
    private Button para;
    @FXML
    private Button pdfbutt;
    @FXML
    private Button Stats;
    @FXML private TextField filterField;
    @FXML
    private Pane mainpane;
    @FXML
    private Button mail;
    @FXML
    private Button type_rec;
    @FXML
    private Button ajouter2;
 
   
       

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ReclamationServices rService = new ReclamationServices();
        ArrayList List = (ArrayList) rService.selectAll();
        observableList = FXCollections.observableArrayList(List);
        Table.setItems(observableList);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        sujetCol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        dateCol.setText("Date d'envoi");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        check.setCellValueFactory(new PropertyValueFactory<>("check"));

        archiverBtn.setDisable(false);
        restaurerBtn.setDisable(true);

        newLbl.setUnderline(true);
 

        
        
        FilteredList<Reclamation> filteredData = new FilteredList<>(observableList,b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(reclamation -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (reclamation .getSujet().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
                                }
				
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Reclamation> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(Table.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		Table.setItems(sortedData);
               
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    }    
    



    @FXML
    private void newReclamation(MouseEvent event) throws IOException {

        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("FCReclamation.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront(); 
         
         
         
         
         
         
         

    }
    
    
    private void BarChart(MouseEvent event) {

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("stat.fxml"));
            Parent root = loader.load();
            
            bar.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(GUI.StatController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    private void ParametreReclamation(MouseEvent event) {

         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation2.fxml"));
            Parent root = loader.load();
            
            para.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(BReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
   

    

    @FXML
    private void liste(MouseEvent event) {

        ReclamationServices rService = new ReclamationServices();
        ArrayList List = (ArrayList) rService.selectAll();
        observableList = FXCollections.observableArrayList(List);
        Table.setItems(observableList);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        sujetCol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        dateCol.setText("Date d'envoi");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateR"));
        check.setCellValueFactory(new PropertyValueFactory<>("check"));

        archiverBtn.setDisable(false);
        restaurerBtn.setDisable(true);

        newLbl.setUnderline(false);
        listeLbl.setUnderline(true);
        archiveLbl.setUnderline(false);
        corbeilleLbl.setUnderline(false);

    }

    @FXML
    private void archive(MouseEvent event) {

        ReclamationServices rService = new ReclamationServices();
        ArrayList List = (ArrayList) rService.selectArchive();
        observableList = FXCollections.observableArrayList(List);
        Table.setItems(observableList);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        sujetCol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        dateCol.setText("Date d'archivage");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateArchive"));
        check.setCellValueFactory(new PropertyValueFactory<>("check"));

        archiverBtn.setDisable(true);
        restaurerBtn.setDisable(false);

        newLbl.setUnderline(false);
        listeLbl.setUnderline(false);
        archiveLbl.setUnderline(true);
        corbeilleLbl.setUnderline(false);

    }

    @FXML
    private void corbeille(MouseEvent event) {

        System.out.println("GUI.BReclamationController.corbeille()");

        ReclamationServices rService = new ReclamationServices();
        ArrayList ListCorbeille = (ArrayList) rService.selectCorbeille();
        observableList = FXCollections.observableArrayList(ListCorbeille);
        Table.setItems(observableList);
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        sujetCol.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("user"));
        dateCol.setText("Date de suppression ");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateCorbeille"));
        check.setCellValueFactory(new PropertyValueFactory<>("check"));

        archiverBtn.setDisable(false);
        restaurerBtn.setDisable(false);

        newLbl.setUnderline(false);
        listeLbl.setUnderline(false);
        archiveLbl.setUnderline(false);
        corbeilleLbl.setUnderline(true);

    }

    @FXML
    private void deleteSelectedRow(ActionEvent event) {

        ObservableList<Reclamation> dataListRemove = FXCollections.observableArrayList();
        ReclamationServices rS = new ReclamationServices();

        Reclamation rec = Table.getSelectionModel().getSelectedItem();

        if (rec.getCorbeille() == 0) {

            rS.toCorbeille(rec);
        } else {
            rS.delete(rec);
        }
        dataListRemove.add(rec);

        for (Reclamation r : observableList) {

            if (r.getCheck().isSelected()) {
                if (r.getCorbeille() == 0) {

                    rS.toCorbeille(r);
                } else {
                    rS.delete(r);
                }

                dataListRemove.add(r);

            }
        }
        observableList.removeAll(dataListRemove);
        Table.refresh();

    }

    @FXML
    private void archiverSelectedRow(ActionEvent event) {

        ObservableList<Reclamation> dataListRemove = FXCollections.observableArrayList();
        ReclamationServices rS = new ReclamationServices();

        Reclamation rec = Table.getSelectionModel().getSelectedItem();
        rS.toArchive(rec);
        dataListRemove.add(rec);

        for (Reclamation r : observableList) {

            if (r.getCheck().isSelected()) {
                rS.toArchive(r);
                dataListRemove.add(r);

            }
        }
        observableList.removeAll(dataListRemove);
        Table.refresh();

    }

    @FXML
    private void restaurerSelectedRow(ActionEvent event) {

        ObservableList<Reclamation> dataListRemove = FXCollections.observableArrayList();
        ReclamationServices rS = new ReclamationServices();

        Reclamation rec = Table.getSelectionModel().getSelectedItem();
        rS.restaurer(rec);
        dataListRemove.add(rec);

        for (Reclamation r : observableList) {

            if (r.getCheck().isSelected()) {
                rS.restaurer(r);
                dataListRemove.add(r);

            }
        }
        observableList.removeAll(dataListRemove);
        Table.refresh();

    }

    @FXML
    private void afficherReclamation(ActionEvent event) {

        try {

            Reclamation r = Table.getSelectionModel().getSelectedItem();

            if (r == null) {
                new Alert(Alert.AlertType.INFORMATION, " Vous devez selectionner une reclamation ").show();
            } else {
                
                //r.setUser(CurrentUser.getCurrent_User());
                r.setUser_id(4);
                r.setEvent_id(1);
                r.setOrganisateur_id(5);
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamation.fxml"));
                Parent root = loader.load();
                AfficherReclamationController aRC = loader.getController();
                aRC.setParametres(r);
                afficherBtn.getScene().setRoot(root);
            }

        } catch (IOException ex) {

            Logger.getLogger(FCReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @FXML
    private void typeAction(ActionEvent event) throws IOException {

        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/GUI/BTypeReclamation.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();  
            
            
    }
            
            
            
            
            
            
            
    
    
    @FXML
    private void CreatePdf(ActionEvent event) throws Exception {
        try {
         Document document = new Document();
       PdfWriter.getInstance(document,new FileOutputStream("C:/Users\\pubkhalil\\Desktop\\PIDEV\\rec1.pdf"));
       document.open();
       PdfPTable table=new PdfPTable(4);
       table.setWidthPercentage(100);
		table.getDefaultCell().setBorder(2);
       table.addCell("Id");
       table.addCell("Sujet");
       table.addCell("Contenu");
       table.addCell("Date_réclamation");
      
        com.itextpdf.text.Paragraph p = new com.itextpdf.text.Paragraph();
            p.add("ISchool");
            p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            document.add(p);
                   document.add(com.itextpdf.text.Image.getInstance("C:/Users/pubkhalil/Desktop/crud/ecole.png"));

       Class.forName("com.mysql.jdbc.Driver");
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidevteamone", "root", "");
       Statement st=con.createStatement();
       ResultSet rs=st.executeQuery("Select * from reclamation");
       while(rs.next()){
       table.addCell(rs.getString("id"));
            table.addCell(rs.getString("sujet"));

                   table.addCell(rs.getString("contenu"));

                          table.addCell(rs.getString("dateR"));

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
    private void Stats(ActionEvent event) throws IOException {
      
        
        
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/GUI/PieChart1.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    
    }
    
     @FXML
    private void stats (ActionEvent event) throws IOException {
       mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/GUI/stat.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void send(ActionEvent event) throws IOException {
        
         mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/GUI/Mail.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
    }

    @FXML
    private void ajouter2(ActionEvent event) throws IOException {
        mainpane.getChildren().clear();
        Parent parent =  FXMLLoader.load(getClass().getResource("/GUI/ajouterReclamation.fxml"));
        mainpane.getChildren().add(parent);
        mainpane.toFront();
       
    }

     
        
        
        
        
        
        
    }
    
    
    
    
    
    
    


    
    
    
    
    
    
    
    
    
    
    
    
    
     
        
    
    
    



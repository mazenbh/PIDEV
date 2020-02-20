/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entitie.Paie_Prof;
import entitie.Paiment_eleve;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import service.FAQ1Crud;
import service.GenererPDF;
import service.PieChart_DB;
import service.ServicePaiment_eleve;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Liste_paimentController implements Initializable {
@FXML
private TableView<Paiment_eleve>table;
@FXML
private TableColumn<Paiment_eleve,Integer>num_recu;
@FXML
private TableColumn<Paiment_eleve,String>nom;
@FXML
private TableColumn<Paiment_eleve,String>prenom;
@FXML
private TableColumn<Paiment_eleve,Float>remise;
@FXML
private TableColumn<Paiment_eleve,String>mode_reglement;
@FXML
private TableColumn<Paiment_eleve,String>frais;
@FXML
private TableColumn<Paiment_eleve,Double>montant;
@FXML
private TableColumn<Paiment_eleve,Integer>reste;
@FXML
private TableColumn<Paiment_eleve,Integer>cin;
@FXML
private TableColumn<Paiment_eleve,String>date;
@FXML
private TableColumn<Paiment_eleve,String>mois;
  @FXML
    private Button ajouter;
    @FXML
    private TextField recherche;
     @FXML
    private TextField tnom;
 private ServicePaiment_eleve paimentService=new ServicePaiment_eleve();
public ObservableList<Paiment_eleve>data;
    /**
     * Initializes the controller class.
     */
    @Override
  public void initialize(java.net.URL location, ResourceBundle resources) {
	
         num_recu.setCellValueFactory(new PropertyValueFactory<>("num_recu"));
	//num_recu.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Integer>("Num_Reçu"));
	mode_reglement.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("mode_reglement"));
	frais.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("type_frais"));
	remise.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Float>("remise"));
	montant.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Double>("montant"));
	cin.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Integer>("cin"));
	
	mois.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("mois"));
	reste.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,Integer>("reste"));
	date.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("Date"));
	nom.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory <Paiment_eleve,String>("prenom"));
    
  data=paimentService.getListePaiements();
       FilteredList<Paiment_eleve> filteredData = new FilteredList<>(data, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        recherche.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (person.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }if (person.getPrenom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if(person.getType_frais().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                        }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Paiment_eleve> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
       //  mainpane.getChildren().add(table);
        table.setItems(sortedData);
//table.setItems(data);

	
}

       
    public void refresh() {
        table.setItems(paimentService.getListePaiements());
        System.out.println("Liste des paiments rafraichie.");
    }

     
          public void afficherResultatRecherche(KeyEvent event) {
        if (recherche.getText() != "") {
            table.setItems(paimentService.recherchePaiment(recherche.getText()));
        }
    }
              public void actionSearch(KeyEvent event) {
        data = paimentService.recherchePaiment(recherche.getText());
        table.setItems(data);
    }
          public void insertPanel(ActionEvent e) throws IOException {
		Stage secondeStage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("Paiment_eleve.fxml"));
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	secondeStage.setScene(scene);
        secondeStage.show();
         Stage secondStage= (Stage)( (Node) (e.getSource())).getScene().getWindow();
  secondStage.close();
	
}
              public void retourVersListe(ActionEvent event) throws IOException {
          Stage secondStage= (Stage)( (Node) (event.getSource())).getScene().getWindow();
  secondStage.close(); 
Stage secondeStage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	secondeStage.setScene(scene);
        secondeStage.show();  
        
    }
                          public void generatePDF(ActionEvent event) {
                              Paiment_eleve p=new Paiment_eleve();
                             GenererPDF s=new GenererPDF(p);
        
    }
                           public void actionRefresh(ActionEvent event) {
      /*  clear();
        showData();
        autoId();
*/
    }
       public void u(ActionEvent e) throws IOException {
		Stage secondeStage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("Update.fxml"));
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	secondeStage.setScene(scene);
        secondeStage.show();
         Stage secondStage= (Stage)( (Node) (e.getSource())).getScene().getWindow();
  secondStage.close();
	
}
                                    public void stat(ActionEvent e) throws Exception{
                                     	Stage secondeStage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("PieChart1.fxml"));
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	secondeStage.setScene(scene);
        secondeStage.show();
         Stage secondStage= (Stage)( (Node) (e.getSource())).getScene().getWindow();
  secondStage.close();       
                                       /*  Class.forName( "com.mysql.jdbc.Driver" );
      Connection connect = DriverManager.getConnection( 
         "jdbc:mysql://localhost:3306/symfony" ,     
         "root",     
         "");
      
      Statement statement = connect.createStatement( );
      ResultSet resultSet = statement.executeQuery("SELECT * FROM `finance`" );
      DefaultPieDataset dataset = new DefaultPieDataset( );
      
      while( resultSet.next( ) ) {
         dataset.setValue( "Nbre de Paiment",
         Integer.parseInt( resultSet.getString( "num_recu" )));
         dataset.setValue("montant", Double.parseDouble(resultSet.getString("montant")));
      }
      
      JFreeChart chart = ChartFactory.createPieChart(
         "Statistiques",   // chart title           
         dataset,          // data           
         true,             // include legend          
         true,           
         false );

      int width = 560;    /* Width of the image */
     // int height = 370;   /* Height of the image */ 
      //File pieChart = new File( "noppe.jpeg" );
      //ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
                                    }
    
}

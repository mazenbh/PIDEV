/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jfoenix.controls.JFXTextField;
import entitie.Frais;
import entitie.Paie_Prof;
import entitie.Paiment_eleve;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Panel;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.ServiceFinanciere;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Paiment_profController implements Initializable {
 @FXML
 private Pane mainpane;
    @FXML
    private TextField cin;
    @FXML
    private TextField montant;
     
    @FXML
    private TextField nbre_heure;
     @FXML
    private TextField salaire_heure;
    @FXML
    private Button save;
    @FXML
    private TableView<Paie_Prof> table;
    @FXML
    private TableColumn<Paie_Prof, Integer> tid;
    @FXML
    private TableColumn<Paie_Prof, String> tcin;
    @FXML
    private TableColumn<Paie_Prof, String> nom;
    @FXML
    private TableColumn<Paie_Prof, String> prenom;
     @FXML
    private TableColumn<Paie_Prof, Float> tnbre_heure;
      @FXML
    private TableColumn<Paie_Prof, Float> tsalaire_heure;
       @FXML
    private TableColumn<Paie_Prof, Double> tmontant;
           @FXML
    private TableColumn<Paie_Prof, String> date;
    @FXML
    private TableColumn action;
    @FXML
    private TextField serch;
    @FXML
    private Button refresh;
    //@FXML
    //private AnchorPane an;
    ServiceFinanciere sf=new ServiceFinanciere();
    ObservableList<Paie_Prof> listData= FXCollections.observableArrayList();
    private String statusCode,statusClick;
    ObservableList<Paie_Prof> listDelete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         tid.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Integer>("num_paiment"));
	tcin.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("Cin"));
	nom.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("Nom"));
	prenom.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("Prenom"));
	tnbre_heure.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Float>("nbre_heure"));
	tsalaire_heure.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Float>("salaire_heure"));
   tmontant.setCellValueFactory(new PropertyValueFactory <Paie_Prof,Double>("Montant"));
   date.setCellValueFactory(new PropertyValueFactory <Paie_Prof,String>("Dtae"));
    action.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, Boolean>,
                ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Object, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        action.setCellFactory(new Callback<TableColumn<Object, Boolean>, TableCell<Object, Boolean>>() {
            @Override
            public TableCell<Object, Boolean> call(TableColumn<Object, Boolean> p) {
                return new ButtonCell(table);
            }
        });
 listData=sf.getListePaiement();
        //table.setItems(listData);
        //listData = FXCollections.observableArrayList();
        
        statusCode = "0";
        statusClick = "0";
        showData();
        autoId();
        //table.getSelectionModel().clearSelection();
     /*  table.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 1) {
        tableDataClick();
    }
}); */   
     FilteredList<Paie_Prof> filteredData = new FilteredList<>(listData, p -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        serch.textProperty().addListener((observable, oldValue, newValue) -> {
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
                }else if(person.getMode_reg().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                        }
                return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Paie_Prof> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
       //  mainpane.getChildren().add(table);
        table.setItems(sortedData);
    
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
    public void dialog(Alert.AlertType alertType,String s){
        Alert alert = new Alert(alertType,s);
        alert.initStyle(StageStyle.UTILITY);
        alert.setTitle("Info");
        alert.showAndWait();
    }
   
    public void clear(){
        cin.clear();
        montant.clear();
        nbre_heure.clear();
        salaire_heure.clear();
        
        statusCode = "0";
    }
   
    public void showData(){
        listData = sf.getListePaiement();
        table.setItems(listData);
    }
   
    public void autoId(){
       Paie_Prof m = new Paie_Prof();
      


    }

    
    public void actionSave(ActionEvent event) {
        Paie_Prof m = new Paie_Prof();
           String idd=tid.getText();
           String e=nbre_heure.getText();
           String ee=salaire_heure.getText();
int cinn=Integer.parseInt(idd);
float p=Float.parseFloat(e);
 float pp=Float.parseFloat(ee);       
        m.setNum_paiment(cinn);
      m.setNbre_heure(p);
      m.setSalaire_heure(pp);
      //  m.setBirthDate(Date.valueOf(cmbDate.getValue()));
     sf.Update(m);
        dialog(Alert.AlertType.INFORMATION, "Data has saved");
        showData();
        clear();
        autoId();
       
    }

  
   public void tableDataClick() {
   if (table.getSelectionModel().getSelectedItem() != null) {
        Paie_Prof selectedPerson = table.getSelectionModel().getSelectedItem();
       // nom.setText(selectedPerson.getNom());
        //  prenom.setText(selectedPerson.getPrenom());
         String str1 = Double.toString(selectedPerson.getMontant());
      montant.setText(str1);
      String nb = Float.toString(selectedPerson.getNbre_heure());
      nbre_heure.setText(nb);
      String h =Float.toString(selectedPerson.getSalaire_heure());
      salaire_heure.setText(h);
      String c = Integer.toString(selectedPerson.getCin());
      cin.setText(c);
       // String str1l = Integer.toString(selectedPerson.getNum_paiment());

     // tid.setText(str1l);
   }
       
    }
   /* public void tableDataClick(MouseEvent event) {
        if (statusClick.equals("1")) {
            statusCode = "1";
            try {
                Paie_Prof click = table.getSelectionModel().getSelectedItems().get(0);
             //   montant.setValue();
               // txtName.setText(click.getName());
                //txtAddress.setText(click.getAddress());
                //cmbDate.setValue(LocalDate.parse(click.getBirthDate().toString()));
            } catch (Exception e) {
            }
        }
    }*/
    public void actionSearch(KeyEvent event) {
        listData = sf.likeByName(serch.getText());
        table.setItems(listData);
    }

    public void actionRefresh(ActionEvent event) {
        clear();
        showData();
        //autoId();
    }
   
    public class ButtonCell extends TableCell<Object, Boolean> {
        final Hyperlink cellButtonDelete = new Hyperlink("Delete");
        final Hyperlink cellButtonEdit = new Hyperlink("Edit");
        final HBox hb = new HBox(cellButtonDelete,cellButtonEdit);
        ButtonCell(final TableView tblView){
            hb.setSpacing(4);
           
            //cell delete
            cellButtonDelete.setOnAction((ActionEvent t) -> {
                statusClick = "1";
                int row = getTableRow().getIndex();
                table.getSelectionModel().select(row);
                   
                tableDataClick();
                Paie_Prof m = new Paie_Prof();
               // m.setNum_paiment(id.getText());
                sf.supprimerPaiement(row);
               showData();
                clear();
                autoId();
                dialog(Alert.AlertType.INFORMATION, "Data has successfully removed");
                statusClick = "0";
                statusCode = "0";
            });
           
            //cell edit
            cellButtonEdit.setOnAction((ActionEvent eventt) -> {
                statusClick = "1";
                int row = getTableRow().getIndex();
                table.getSelectionModel().select(row);
                //table.setOnMouseClicked((MouseEvent event) -> {
    //if (event.getClickCount() > 1) {
        tableDataClick();
       
               
               // dialog(Alert.AlertType.INFORMATION, "Data has successfully updated");
    //}
});    
               // tableDataClick();
                statusClick = "0";
           // });
        }

        
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(hb);
            }else{
                setGraphic(null);
            }
        }
        
}
        public void add(ActionEvent e) throws IOException {
	Stage primaryStage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("Add_paie_prof.fxml"));
	Scene scene = new Scene(root,647.0,400);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
	
}
    

   
  
   
  
   
}
 
 
    


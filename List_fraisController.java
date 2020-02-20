/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import static com.jfoenix.svg.SVGGlyphLoader.clear;
import entitie.Frais;
import entitie.Paiment_eleve;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import static javafx.scene.input.KeyCode.S;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import service.GenererPDF;
import service.ServiceFrais;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class List_fraisController implements Initializable {
@FXML
    private TableView<Frais> table;
    @FXML
    private TableColumn<Frais, String> frais;
    @FXML
    private TableColumn<Frais, Float> montant;
    @FXML
    private TableColumn<Frais, Integer> id;
    @FXML
    private TextField tfrais;
      @FXML
    private TextField tmontant;
           @FXML
    private TextField tid;
        @FXML
    private Button modifier;
 @FXML
    private Button ajouter;
   @FXML
    private Button supprimer
           ;

    /**
     * Initializes the controller class.
     */
   Frais f=new Frais();
      ServiceFrais crudData = new ServiceFrais();
    ObservableList<Frais> listData;
    private String statusCode,statusClick;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        frais.setCellValueFactory((TableColumn.CellDataFeatures<Frais, String> cellData) ->
                        cellData.getValue().fraisProperty());
           montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
          id.setCellValueFactory(new PropertyValueFactory<>("id"));
      listData=crudData.getAll();
      table.setItems(listData);
     
                      
        
    
table.setOnMouseClicked((MouseEvent event) -> {
    if (event.getClickCount() > 1) {
        onEdit();
    }
});
    }
public void onEdit() {
    // check the table's selected item and get selected item
    if (table.getSelectionModel().getSelectedItem() != null) {
        Frais selectedPerson = table.getSelectionModel().getSelectedItem();
        tfrais.setText(selectedPerson.getType_frais());
         String str1 = Double.toString(selectedPerson.getMontant());
      tmontant.setText(str1);
        String str1l = Integer.toString(selectedPerson.getId());

      tid.setText(str1l);
    } } 
 public void addFrais(ActionEvent event) throws IOException {

String type_frai=tfrais.getText();

String ci=tmontant.getText();
//String r=tid.getText();
Float cinn=Float.parseFloat(ci);
//int remise=Integer.parseInt(r);
  Frais p=new Frais();
p.setFris(type_frai);
//p.setId(remise);
p.setMontant(cinn);
  int status=crudData.addFrais(f);
	
	
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Data Insert");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data Insert");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	
    }
 public void update(ActionEvent e)throws IOException,ParseException{
     	String sidd=tid.getText();
	int idt=Integer.parseInt(sidd);
	String sid=tmontant.getText();
	float idp=Float.parseFloat(sid);
	String type_frais=tfrais.getText();

f.setId(idt);
f.setFris(type_frais);
f.setMontant(idp);
        
	
	
	int status=crudData.updateFrais(f);
	
	
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Data UPDATED");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		clear();
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data not updated");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	

}
     public void clear(){
        tid.clear();
        tmontant.clear();
        tfrais.clear();

        
        statusCode = "0";
    }
   
    public void showData(){
        listData =crudData.getAll();
        table.setItems(listData);
    }
    public void actionRefresh(ActionEvent event) {
        clear();
        showData();
        //autoId();
    }
public void delete(ActionEvent e)throws IOException,ParseException{
	String sid=tid.getText();
	int id=Integer.parseInt(sid);
	//DBinfo.delete(id);
	int status=crudData.deleteFrais(id);
	if(status>0) {
		Alert alert=new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Data UPDATED");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
		
	}else {
		Alert alert=new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Data not updated");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	
}
public void PDF(ActionEvent e){
    Frais f=new Frais();
    Connection connection;
		try {
			// - Connexion à la base
			connection =MyDB.getInstance().getConnexion();
			// - Chargement et compilation du rapport
		
			JasperDesign jasperDesign = JRXmlLoader
					.load("C:\\Users\\Dhoha\\Documents\\NetBeansProjects\\Pi_dev\\src\\service\\Frais.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			// - Paramètres à envoyer au rapport
			Map parameters = new HashMap();

			// - Execution du rapport
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, connection);
			// - Création du rapport au format PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"C:\\Users\\Dhoha\\Documents\\Frais.pdf");
			JasperViewer.viewReport(jasperPrint, false); // L'affichage du
															// rapport en
															// utilisant
															// JRViewer
			System.out.println("success");

		}

		catch (JRException ee) {
			System.out.println("erreur de compilation" + ee.getMessage());
		}

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
            public void refresh() {
        table.setItems(crudData.getAll());
        System.out.println("Liste des paiments rafraichie.");
    }
            public void Email(){
     String to = "elkameldhoha@gmail.com";//change accordingly  
      String from = "elkameldhoha@gmail.com";
      String host = "smtp.gmail.com";//or IP address  
      final String username = "elkameldhoha@gmail.com";
    final String password = "zaynebdhoha";
     //Get the session object  
      Properties props = System.getProperties();  
      props.setProperty("mail.smtp.host", host);  
       props.put("mail.smtp.starttls.enable","true");
        /* mail.smtp.ssl.trust is needed in script to avoid error "Could not convert socket to TLS"  */
        props.setProperty("mail.smtp.ssl.trust", host);
        props.put("mail.smtp.auth", "true");      
            // props.put("mail.smtp.user", username);
      //  props.put("mail.smtp.password", password);
       
        props.put("mail.smtp.port", "587");
     Session session = Session.getDefaultInstance(props, 
      new javax.mail.Authenticator() {
           
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from));  
         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
         message.setSubject("Rappel pour le paiment");  
         message.setText("Hello, il faut payé les frais de l'ecole ");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
     
}
}


    


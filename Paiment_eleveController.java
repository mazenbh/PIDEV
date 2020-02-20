/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import entitie.Paiment_eleve;
import entitie.User;
import java.io.IOException;
//import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Properties;
//import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import service.ServicePaiment_eleve;
import service.serviceuser;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Dhoha
 */
public class Paiment_eleveController implements Initializable {

    /**
     * Initializes the controller class.
     */
           Paiment_eleve p=new Paiment_eleve();
    ServicePaiment_eleve sv =new ServicePaiment_eleve();
 
 @FXML
    private TextField num_recu;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField remise;
    @FXML
    private TextField cin;
      @FXML
    private TextField montant;
         @FXML
    private ComboBox <String> mode_paiment;
         @FXML
    private TextField mois;
               @FXML
    private ComboBox <String> mode_reglement;
                 @FXML
    private ComboBox <String> type_frais;
      @FXML
    private TextField reste;
        @FXML
    private TextField email;
     int oblist;
    //ObservableList<String> oblist;
    @FXML
    private Button ajouterPaiment;
// String value = (String) mode_reglement.getValue();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         serviceuser pdao = new serviceuser();
       //oblist = pdao.gid();
       // System.out.println(oblist);
        //cin.setItems(oblist);
              //  mode_reglement.setItems(options);
         mode_reglement.getItems().add("par mois");
   mode_reglement.getItems().add("Annuelle");
   String value = (String) mode_reglement.getValue();
         mode_reglement.setEditable(true);
     mode_paiment.getItems().add("cheque");
   mode_paiment.getItems().add("espece");
   String value1 = (String) mode_paiment.getValue();
         mode_paiment.setEditable(true);
              type_frais.getItems().add("foyer");
   type_frais.getItems().add("cantine");
   String value2 = (String) type_frais.getValue();
        type_frais.setEditable(true);
        
    }   
     public void getEmployeeId(ActionEvent e)throws IOException,ParseException{
			String sid=cin.getText();
			int id=Integer.parseInt(sid);
			User emp=MyDB.getEmpoyeeId(id);
		nom.setText(emp.getNom());
	        prenom.setText(emp.getPrenom());
                email.setText(emp.getEmail());
			
		}
 public void getE(ActionEvent e)throws IOException,ParseException{
			String sid=cin.getText();
			int id=Integer.parseInt(sid);
			User emp=MyDB.getEmpoyeeId(id);
		nom.setText(emp.getNom());
	        prenom.setText(emp.getPrenom());
                email.setText(emp.getEmail());
 }
    public void retourVersListe(ActionEvent event) throws IOException {
  	Stage secondeStage=new Stage();
	Parent root=FXMLLoader.load(getClass().getResource("Liste_paiment.fxml"));
	Scene scene = new Scene(root);
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	secondeStage.setScene(scene);
        secondeStage.show();
         Stage secondStage= (Stage)( (Node) (event.getSource())).getScene().getWindow();
  secondStage.close();
    }
    public void addProjet(ActionEvent event) throws IOException {
 
String ci=cin.getText();
String r=remise.getText();
String rr=nom.getText();
String rrr=prenom.getText();
String oo=email.getText();
//int cinn=Integer.parseInt(ci);
int remis=Integer.parseInt(r);
int cinn=Integer.parseInt(ci);
  Paiment_eleve p=new Paiment_eleve();
  p.setMode_paiment(mode_paiment.getValue());
  
  p.setEmail(oo);
 p.setCin(cinn);
  p.setNom(rr);
  p.setPrenom(rrr);
  p.setMode_reglement(mode_reglement.getValue());
  p.setRemise(remis);
  p.setType_frais(type_frais.getValue());
  int status=sv.ajouterPaiement(p);
	
	
	if(status>0) {
		Alert alert=new Alert(AlertType.INFORMATION);
		alert.setTitle("Data Insert");
		alert.setHeaderText("info");
		alert.setContentText("Record save");
		alert.showAndWait();
		System.out.println("record saaved");
                //Email(p.getEmail());
		
	}else {
		Alert alert=new Alert(AlertType.ERROR);
		alert.setTitle("Data Insert");
		alert.setHeaderText("ERROR");
		alert.setContentText("Record ERROR");
		alert.showAndWait();
		System.out.println("record ERROR");
		
	}
	
    }
       /* private void retourVersListe() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MesArticles.fxml"));
            Parent root = loader.load();
            MesArticlesController icrl = loader.getController();
            texteTextField.getScene().setRoot(root);

        } catch (IOException ex) {
            Logger.getLogger(MesArticlesController.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
 */
     public void Email(String to){
     //String to = "elkameldhoha@gmail.com";//change accordingly  
      String from = "elkameldhoha@gmail.com";
      String host = "smtp.gmail.com";//or IP address  
      final String username = "elkameldhoha@gmail.com";
    final String password = "pcasus2019";
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
         message.setSubject("Paiment de l'ecole");  
         message.setText("Hello, Felicitation vous avez paye les frais de l'ecole  ");  
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
     
}

}

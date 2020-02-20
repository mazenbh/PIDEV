/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataBase.Mail;
import DataBase.TrayIconDemo;
import java.awt.AWTException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author pubkhalil
 */
public class MailController implements Initializable {

    @FXML
    TextField des;
    @FXML
    TextArea desc;
    @FXML
    private Button mail;
    @FXML
    private TextField suj;
    
    
    
    
    
    
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
       
        
        
        
        
        
        
    } 
    Mail u=new Mail();

    @FXML
    private void send(ActionEvent event) {
        
        u.sendMail(des.getText(),suj.getText(),desc.getText());
        
        
        String[]args21 = null;

       try {
            TrayIconDemo.main(args21);
                    
        } catch (AWTException ex) {
           Logger.getLogger(MailController.class.getName()).log(Level.SEVERE, null, ex);
       }
        
    }
    
    
    
    
    
    
    
    
}

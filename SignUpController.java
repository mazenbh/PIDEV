/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class SignUpController implements Initializable {

    @FXML
    private VBox signup;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    



    @FXML
    private void signupbtn(ActionEvent event) throws IOException {
             Stage stage1 = (Stage) signup.getScene().getWindow();
                System.out.println("redirection to login");
                stage1.close();
          
      
           Stage stage = new Stage();
         Parent root = FXMLLoader.load(getClass().getResource("/Fxml/ChoixType.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import entities.UserC;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import GUI.AnimationGenerator;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Iheb Memmiche
 */
public class FXMLMainMenuController implements Initializable {

    @FXML
    private Button EventBtn;
     @FXML
    private VBox menu;
    @FXML
    private VBox nav;
    @FXML
    private Circle userImage;
    @FXML
    private AnchorPane content;
    private AnimationGenerator anim;
    
    public static AnchorPane contnt = null;
    private VBox sidebar, secondSidebar;
    private double xOffset = 0;
    private double yOffset = 0;
    boolean menuFlag, navFlag;
    
    @FXML
    private AnchorPane parent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
        
        // TODO
    }    

    

   

    
    

    
    @FXML
    private void goToReclamation(ActionEvent event) {
        
         try
        {
         
        FXMLLoader loader =new FXMLLoader(getClass().getResource("BReclamation.fxml"));
            Parent root;
            root=loader.load();
            EventBtn.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void open_menu(MouseEvent event) {
        if (!menuFlag) {
            anim.applyFadeAnimationOn(menu, 1500, 0f, 1.0f, null);
            menu.setPrefWidth(150.0);
            menu.getChildren().setAll(sidebar);
            menuFlag = true;
        } else {
            anim.applyFadeAnimationOn(menu, 1500, 1.0f, 0f, (e) -> {
                menu.getChildren().clear();
                menu.setPrefWidth(0.0);
            });
            menuFlag = false;
        }
    }
    
    @FXML
    private void open_nav(MouseEvent event) {
        if (!navFlag) {
            anim.applyFadeAnimationOn(nav, 1500, 0f, 1.0f, null);
            nav.setPrefWidth(50.0);
            nav.getChildren().setAll(secondSidebar);
            navFlag = true;
        } else {
            anim.applyFadeAnimationOn(nav, 1500, 1.0f, 0f, (e) -> {
                nav.getChildren().clear();
                nav.setPrefWidth(0.0);
            });
            navFlag = false;
        }
    }
    
    @FXML
    private void minimize_emu(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (!stage.isIconified()) {
            stage.setIconified(true);
            
        } else {
            stage.setIconified(false);
        }
        
    }
    
    @FXML
    private void maximize_emu(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (!stage.isMaximized()) {
            stage.setMaximized(true);
        } else {
            stage.setMaximized(false);
        }
        
    }
    
    @FXML
    private void close_emu(MouseEvent event) {
        System.exit(0);
    }
    
    public void makeStageDrageable() {

        parent.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        
    }
    
}








/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DataBase.MyConnection;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author mazen
 */
public class StatController implements Initializable {

    @FXML
    private BarChart<String, Integer> barchart;
    @FXML
    private JFXButton load;
    private Connection connection;
    /**
     * Initializes the controller class.
     */
 @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void loadChart(ActionEvent event) throws SQLException
    {
        String query ="SELECT dateR ,count(*) FROM reclamation group by dateR ";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        
          Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(query);
          while (rs.next()){
              
              series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
              
          }
          barchart.getData().add(series);
          }
    }


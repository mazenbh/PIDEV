/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Utils.DataBase;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class StatcantineController implements Initializable {

    @FXML
    private BarChart<String, Integer> chart;
    @FXML
    private JFXButton loadChart;

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
        String query ="SELECT type_abonement,count(id_c) FROM cantine group by type_abonement ";
        XYChart.Series<String,Integer> series = new XYChart.Series<>();
        
         Connection c = DataBase.getInstance().getConnection();
         ResultSet rs = c.createStatement().executeQuery(query);
          while (rs.next()){
              
              series.getData().add(new XYChart.Data<>(rs.getString(1),rs.getInt(2)));
              
          }
          chart.getData().add(series);
          }
    }



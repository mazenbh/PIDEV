/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import Utils.Database2;

/**
 * FXML Controller class
 *
 * @author Haytham
 */
public class eventChartController implements Initializable {

    @FXML
    private BarChart<String ,Integer> chart;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         try{
            try {
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                String sql=" select  COUNT( DISTINCT evenement_participant.idUser) as nb  ,evenement.designation FROM evenement_participant INNER JOIN evenement on evenement_participant.idEvenement=evenement.idEvenement GROUP BY evenement_participant.idEvenement;  ";
                Connection con = Database2.connect();
                ResultSet rs = con.createStatement().executeQuery(sql);
                while (rs.next()) {
                    series.getData().add(new XYChart.Data(rs.getString("designation"),rs.getInt("nb")));
                }
                chart.getData().add(series);
            } catch (SQLException ex) {
                Logger.getLogger(eventChartController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String sql=" select MAX(nb),designation  from (select COUNT( DISTINCT evenement_participant.idUser) as nb ,evenement.designation FROM evenement_participant INNER JOIN evenement on evenement_participant.idEvenement=evenement.idEvenement GROUP BY evenement_participant.idEvenement ORDER BY nb DESC) as vv ; ";
            Connection con = Database2.connect();
            ResultSet rs = con.createStatement().executeQuery(sql);
      
         
            while (rs.next()) {
                msg.setText("L'evenement \n"+ rs.getString("designation")+ " \n a le plus grand nombre de participants" );
            }

              } catch (SQLException ex) {
            Logger.getLogger(eventChartController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
       
    }}    
    


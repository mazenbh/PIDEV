/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 
package Services;

import com.jfoenix.controls.JFXListView;

import DataBase.MyConnection;
import entities.Reclamation;
import entities.ReclamationType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;


/**
 *
 * @author pubkhalil
 */
public class FAQ1Crud {
   
    
 public ObservableList<PieChart.Data> Stats() {
        String requete = "SELECT date, COUNT(*) FROM `reclamation` GROUP BY date";
        try {
            Statement st2 = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st2.executeQuery(requete);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            while (rs.next()) {
                pieChartData.add(new PieChart.Data(rs.getString(1), rs.getInt(2)));

            }

            return pieChartData;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }
    
    
 
 
 
  
        
        
        
        
    
        
        
        
        
        
        
        

    }
     
     
     
     
     
    
      
    
    
    


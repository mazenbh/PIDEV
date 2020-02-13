/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.*; 
import java.sql.*; 
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.general.DefaultPieDataset;
import utils.DB;

public class PieChart_DB {
 public static void main( String[ ] args )throws Exception {
      
      String mobilebrands[] = {
         "IPhone 5s",   
         "SamSung Grand",   
         "MotoG",            
         "Nokia Lumia" 
      };
      
      /* Create MySQL Database Connection */
      Class.forName( "com.mysql.jdbc.Driver" );
      Connection connect = DriverManager.getConnection( 
         "jdbc:mysql://localhost:3306/symfony" ,     
         "root",     
         "");
      
      Statement statement = connect.createStatement( );
      ResultSet resultSet = statement.executeQuery("SELECT * FROM `fos_user`" );
      DefaultPieDataset dataset = new DefaultPieDataset( );
      
      while( resultSet.next( ) ) {
         dataset.setValue( 
         resultSet.getString( "sexe" ) ,
         Double.parseDouble( resultSet.getString( "id" )));
      }
      
      JFreeChart chart = ChartFactory.createPieChart(
         "Statistiques",   // chart title           
         dataset,          // data           
         true,             // include legend          
         true,           
         false );

      int width = 560;    /* Width of the image */
      int height = 370;   /* Height of the image */ 
      File pieChart = new File( "noppe.jpeg" );
      ChartUtilities.saveChartAsJPEG( pieChart , chart , width , height );
   }
}
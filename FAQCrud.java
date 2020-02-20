/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import com.jfoenix.controls.JFXListView;

import DataBase.MyConnection;

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
 * @author lazre
 */
public class FAQCrud {

   
    public void supprimerFAQ(int id) {
        String requete3 = "DELETE FROM faq where id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete3);

            pst.setInt(1, id);
            pst.executeUpdate();

            System.out.println("FAQ supprimé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    

    

}

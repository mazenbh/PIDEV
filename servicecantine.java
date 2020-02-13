/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.abonneC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DB;

/**
 *
 * @author mazen
 */
public class servicecantine {
     private Connection con;
    private Statement ste;

    public servicecantine() {
        con = DB.getInstance().getConnection();

    }
    
    

    public void ajouterAbonné(abonneC p) {
        Statement st;
        try {
            st = con.createStatement();
            String req = "INSERT INTO `cantine`(`type_abonement`,`date_debut`,`date_fin`,`cin`) VALUES  ('"+ p.getType_abonement()+"','" + p.getDate_debut()+ "','" + p.getDate_fin()+ "','" + p.getCin()+ "')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void afficherAbonné() {
        PreparedStatement pt;
        try {

            pt = con.prepareStatement("select * from cantine");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Personne [id_c:" + rs.getInt(1) + ",tyoe_abonement" + rs.getString(2) +",date_debut" + rs.getString(3) + ",date_fin" + rs.getString(4)+ ",cin" + rs.getInt(4));
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void modifierAbonné(int id_c, String type_abonement) {
        try {
            PreparedStatement pt = con.prepareStatement("update cantine set type_abonement=?where id_c=?");
            pt.setString(1, type_abonement);
            pt.setInt(2, id_c);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void supprimerAbonné(abonneC p) {
        PreparedStatement pt;
        try {
            pt = con.prepareStatement("delete from cantine where cin=? ");
            pt.setInt(1, p.getCin());
            pt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(servicecantine.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}

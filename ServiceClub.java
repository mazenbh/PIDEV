/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Club;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import Utils.Database2;

/**
 *
 * @author Haytham
 */
public class ServiceClub {

    public static void InsertClub(String nomClub, String dateCreation, String emailClub, Integer idPresident, String imageClub) throws SQLException {
        String sql = "insert into club (nomClub,dateCreation,emailClub,idPresident,imageClub) values ('" + nomClub + "', '" + dateCreation + "', '" + emailClub + "', '" + idPresident + "', '" + imageClub + "')";

        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }

    public static void SuppClub(Integer id) throws SQLException {
        String sql = "DELETE FROM `club` WHERE `club`.`idClub` =" + Integer.toString(id);
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }

    public static void ModifClub(Integer idClub, String nomClub, String dateCreation, String emailClub, Integer idPresident, String imageClub) throws SQLException {
        String sql = "update club set nomClub= '" + nomClub + "',dateCreation ='" + dateCreation + "',emailClub='" + emailClub + "',idPresident='" + idPresident + "',imageClub='" + imageClub + "'  where idClub ='" + idClub + "' ";
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "User inexisistant");

            throw e;
        }

    }

}

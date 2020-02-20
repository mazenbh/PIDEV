/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import entities.Reclamation2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DataBase.MyConnection;
import DataBase.connection;

/**
 *
 * @author Haytham
 */
public class ServiceReclamation2 {

    public static void InsertClub(String nomClub, String imageClub, String dateCreation, String emailClub, String idPresident ,String imageClub1) throws SQLException {
        String sql = "insert into reclamation (sujet,contenu,dateR,email,etat,typerec) values ('" + nomClub + "', '" + imageClub + "', '" + dateCreation + "', '" +  emailClub + "', '" + idPresident+ "', '" + imageClub1 + "')";

        try {
            
             connection.dbExecuteQuery(sql);
            
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }

    public static void SuppClub(Integer id) throws SQLException {
        String sql = "DELETE FROM `reclamation` WHERE `reclamation`.`id` =" + Integer.toString(id);
        try {
                 connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }

    public static void ModifClub(Integer idClub, String nomClub, String dateCreation, String emailClub, String idPresident, String imageClub,String imageClub1) throws SQLException {
        String sql = "update reclamation set etat= '" + idPresident+ "',dateR ='" + dateCreation + "',email='" + emailClub + "',sujet='" + nomClub + "',contenu='" + imageClub + "' ,typerec='" + imageClub1 + "' where id ='" + idClub + "' ";
        try {
      connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de modification" + e);
            throw e;
        }

    }

}


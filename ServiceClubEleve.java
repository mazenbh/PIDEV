/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import Utils.Database2;

/**
 *
 * @author Haytham
 */
public class ServiceClubEleve {
      public static void InsertClubEleve(Integer idClub, Integer idUser, String etat) throws SQLException {
        String sql = "insert into club_eleve (idClub,idUser,etat) values ('" + idClub + "', '" + idUser +"', '" + "inactif" +  "')";

        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null,"Vous etes déja inscrit");
            throw e;
        }
    }
}

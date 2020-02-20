/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import DataBase.MyConnection;
import Interfaces.IReclamationType;
import entities.ReclamationType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class ReclamationTypeServices implements IReclamationType{

    @Override
    public void insert(ReclamationType r) {
        
        String requete = " INSERT INTO type_reclamation (nom)"
                + "VALUES('" + r.getNom() + "')";

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.err.println("type Ajouté !");

        } catch (SQLException ex) {
            System.err.println("statement fail !");
        }
    }

    @Override
    public void update(ReclamationType r) {
        
        String requete = " UPDATE type_reclamation SET nom=? WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, r.getNom());
            pst.setInt(2, r.getIdType() );

            pst.executeUpdate();
            System.err.println("type Modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    
    }

    @Override
    public void delete(ReclamationType r) {
        String requete = " DELETE FROM type_reclamation WHERE id=? ";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getIdType());
            pst.executeUpdate();
            System.err.println("type Supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<ReclamationType> selectAll() {

         List<ReclamationType> types = new ArrayList();

        try {
            String requete = " SELECT * From type_reclamation";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                ReclamationType r = new ReclamationType(res.getInt("id"));
                r.setNom(res.getString("nom"));
                types.add(r);
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return types;
    }
    
   
    
}

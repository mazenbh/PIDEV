/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.Club;
import Entites.Evenement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import Utils.Database2;

/**
 *
 * @author Haytham
 */
public class ServiceEvenement {

    private Connection con = Database2.getInstance().getCnx();
    private Statement statement;

    public static void InsertEvenement(Integer idClub, String designation, Integer nbPlaces, String etat, String dateDebut, String dateFin, String description, String typeEvenement) throws SQLException {
        String sql = "insert into evenement (idClub,designation,nbPlaces,etat,dateDebut,dateFin,description,typeEvenement) values ('" + idClub + "', '" + designation + "','" + nbPlaces + "', '" + "Pas encore accepté" + "', '" + dateDebut + "', '" + dateFin + "', '" + description + "', '" + typeEvenement + "')";

        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }

    public static void SuppEvenement(Integer id) throws SQLException {
        String sql = "DELETE FROM `evenement` WHERE `evenement`.`idEvenement` =" + Integer.toString(id);
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }

    public static void ModifEvenement(Integer idEvenement, Integer idClub, String designation, Integer nbPlaces, String etat, String dateDebut, String dateFin, String description, String typeEvenement) throws SQLException {
        String sql = "update evenement set idClub= '" + idClub + "',designation ='" + designation + "',nbPlaces='" + nbPlaces + "',etat='" + etat + "',dateDebut='" + dateDebut + "',dateFin='" + dateFin + "',description='" + description + "',typeEvenement='" + typeEvenement + "'  where idEvenement ='" + idEvenement + "' ";
        try {
            Database2.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de modification" + e);
            throw e;
        }

    }

}

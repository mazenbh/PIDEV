/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.sql.SQLException;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import Utils.Database2;

/**
 *
 * @author Haytham
 */
public class Club {

    private int idClub;
    private String nomClub;
    private Date dateCreation;
    private String emailClub;
    private int idPresident;
    private String imageClub;

    public Club() {

    }
public Club(String nomClub) {
        this.nomClub = nomClub;
    }
    public Club(int idClub, String nomClub, Date dateCreation, String emailClub, int idPresident, String imageClub) {
        this.idClub = idClub;
        this.nomClub = nomClub;
        this.dateCreation = dateCreation;
        this.emailClub = emailClub;
        this.idPresident = idPresident;
        this.imageClub = imageClub;
    }

    public Club(String nomClub, Date dateCreation, String emailClub, String imageClub) {
        this.nomClub = nomClub;
        this.dateCreation = dateCreation;
        this.emailClub = emailClub;
        this.imageClub = imageClub;
    }

    public Club(int idClub, String nomClub) {
        this.idClub = idClub;
        this.nomClub = nomClub;
    }

    public Club(String nomClub, Date dateCreation, String emailClub, int idPresident, String imageClub) {
        this.nomClub = nomClub;
        this.dateCreation = dateCreation;
        this.emailClub = emailClub;
        this.idPresident = idPresident;
        this.imageClub = imageClub;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public String getNomClub() {
        return nomClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getEmailClub() {
        return emailClub;
    }

    public void setEmailClub(String emailClub) {
        this.emailClub = emailClub;
    }

    public int getIdPresident() {
        return idPresident;
    }

    public void setIdPresident(int idPresident) {
        this.idPresident = idPresident;
    }

    public String getImageClub() {
        return imageClub;
    }

    public void setImageClub(String imageClub) {
        this.imageClub = imageClub;
    }

}

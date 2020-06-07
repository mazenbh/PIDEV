/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;


/**
 *
 * @author Haytham
 */
public class Club {

    @Override
    public String toString() {
        return "Club{" + "idClub=" + idClub + ", nomClub=" + nomClub + ", dateCreation=" + dateCreation + ", emailClub=" + emailClub + ", idPresident=" + idPresident + ", imageClub=" + imageClub + ", updated_at=" + updated_at + '}';
    }

    private int idClub;
    private String nomClub;
    private String dateCreation;
    private String emailClub;
    private int idPresident;
    private String imageClub;
    private Date updated_at;

  
    public Club() {

    }
public Club(String nomClub) {
        this.nomClub = nomClub;
    }
//    public Club(int idClub, String nomClub, Date dateCreation, String emailClub, int idPresident, String imageClub) {
//        this.idClub = idClub;
//        this.nomClub = nomClub;
//        this.dateCreation = dateCreation;
//        this.emailClub = emailClub;
//        this.idPresident = idPresident;
//        this.imageClub = imageClub;
//    }
//
//    public Club(String nomClub, Date dateCreation, String emailClub, String imageClub) {
//        this.nomClub = nomClub;
//        this.dateCreation = dateCreation;
//        this.emailClub = emailClub;
//        this.imageClub = imageClub;
//    }

    public Club(int idClub, String nomClub) {
        this.idClub = idClub;
        this.nomClub = nomClub;
    }

    public Club(String nomClub, String dateCreation, String emailClub, int idPresident, String imageClub) {
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

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
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
  public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

}

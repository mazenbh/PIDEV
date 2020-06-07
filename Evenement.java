package entity;

import java.io.Serializable;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Haytham
 */
public class Evenement implements Serializable{

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", idClub=" + idClub + ", designation=" + designation + ", nbPlaces=" + nbPlaces + ", etat=" + etat + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", description=" + description + ", typeEvenement=" + typeEvenement + '}';
    }


  


  
private int idEvenement ; 


private String designation ;

private int nbPlaces ; 
private String etat ; 
private String dateDebut ; 
private String dateFin ; 
private String description ;
private String typeEvenement ;
private int idClub ; 
   public Evenement(String designation, int nbPlaces, String dateDebut, String dateFin, String description, String typeEvenement , int idClub) {
        this.designation = designation;
        this.nbPlaces = nbPlaces;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.typeEvenement = typeEvenement;
        this.idClub=idClub;
    }

    public Evenement(int idEvenement, String designation, int nbPlaces, String etat, String dateDebut, String dateFin, String description, String typeEvenement, int idClub) {
        this.idEvenement = idEvenement;
        this.designation = designation;
        this.nbPlaces = nbPlaces;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.typeEvenement = typeEvenement;
        this.idClub = idClub;
    }

    public Evenement(String designation, int nbPlaces, String etat, String description, String typeEvenement, int idClub) {
        this.designation = designation;
        this.nbPlaces = nbPlaces;
        this.etat = etat;
        this.description = description;
        this.typeEvenement = typeEvenement;
        this.idClub = idClub;
    }
        public Evenement(String designation, int nbPlaces, String description, String typeEvenement, int idClub) {
        this.designation = designation;
        this.nbPlaces = nbPlaces;
        this.description = description;
        this.typeEvenement = typeEvenement;
        this.idClub = idClub;
    }
   public Evenement(String designation) {
        this.designation = designation;
    }
    public Evenement(String designation, String etat , String dateDebut, String dateFin , String typeEvenement) {
        
        this.designation = designation;
this.etat=etat;
this.dateDebut=dateDebut;
this.dateFin=dateFin;
this.typeEvenement=typeEvenement;
    }
    public Evenement(int idEvenement,  String designation) {
        this.idEvenement = idEvenement;
        this.designation = designation;
    }

  public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

  

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

   

    public int getNbPlaces() {
        return nbPlaces;
    }

    public void setNbPlaces(int nbPlaces) {
        this.nbPlaces = nbPlaces;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeEvenement() {
        return typeEvenement;
    }

    public void setTypeEvenement(String typeEvenement) {
        this.typeEvenement = typeEvenement;
    }
    public Evenement(){
        
    }
    
    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }
    public Evenement(int idEvenement, String designation, int nbPlaces, String etat,String dateDebut, String dateFin, String description, String typeEvenement) {
        this.idEvenement = idEvenement;
        this.designation = designation;
        this.nbPlaces = nbPlaces;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.typeEvenement = typeEvenement;
    }
//        public Evenement(int idEvenement, String designation) {
//        this.idEvenement = idEvenement;
//        this.designation = designation;
//    }
            public Evenement(int idEvenement, String designation, int nbPlaces) {
        this.idEvenement = idEvenement;
        this.designation = designation;
        this.nbPlaces = nbPlaces ; 
    }
   

}


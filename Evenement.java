package Entites;

import java.sql.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Haytham
 */
public class Evenement {


  


  
private int idEvenement ; 


private int idClub ; 
private String designation ;
private int nbPlaces ; 
private String etat ; 
private Date dateDebut ; 
private Date dateFin ; 
private String description ;
private String typeEvenement ;

   public Evenement(String designation) {
        this.designation = designation;
    }
    public Evenement(String designation, String etat , Date dateDebut, Date dateFin , String typeEvenement) {
        
        this.designation = designation;
this.etat=etat;
this.dateDebut=dateDebut;
this.dateFin=dateFin;
this.typeEvenement=typeEvenement;
    }
    public Evenement(int idEvenement, int idClub, String designation) {
        this.idEvenement = idEvenement;
        this.idClub = idClub;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
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
    public Evenement(int idEvenement, int idClub, String designation, int nbPlaces, String etat, Date dateDebut, Date dateFin, String description, String typeEvenement) {
        this.idEvenement = idEvenement;
        this.idClub = idClub;
        this.designation = designation;
        this.nbPlaces = nbPlaces;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.typeEvenement = typeEvenement;
    }
        public Evenement(int idEvenement, String designation) {
        this.idEvenement = idEvenement;
        this.designation = designation;
    }
            public Evenement(int idEvenement, String designation, int nbPlaces) {
        this.idEvenement = idEvenement;
        this.designation = designation;
        this.nbPlaces = nbPlaces ; 
    }
      public Evenement(Integer idClub, String designation, int nbPlaces, String etat, Date dateDebut, Date dateFin, String description, String typeEvenement) {
        this.idClub = idClub;
        this.designation = designation;
        this.nbPlaces = nbPlaces;
        this.etat = etat;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.typeEvenement = typeEvenement;
    }


}


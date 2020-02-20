package entities;
import entities.ReclamationType;
import java.sql.Date;
import java.sql.SQLException;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class Reclamation2 {

 private int id;

    public void setIdReclamation(int id) {
        this.id = id;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(Date dateR) {
        this.dateR = dateR;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setType(String type) {
        this.typerec = typerec;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public void setDateArchive(String dateArchive) {
        this.dateArchive = dateArchive;
    }

    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }

    public int getIdReclamation() {
        return id;
    }

    public String getSujet() {
        return sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public Date getDate() {
        return dateR;
    }

    public int getType_id() {
        return type_id;
    }

    public int getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getEtat() {
        return etat;
    }

    public String getType() {
        return typerec;
    }

    public int getArchive() {
        return archive;
    }

    public String getDateArchive() {
        return dateArchive;
    }

    public int getCorbeille() {
        return corbeille;
    }
    private String sujet;
    private String contenu;
   private Date dateR;
    private int type_id;
private int cin;
private String email;
private String etat ;

    public Reclamation2(int id, String sujet, String contenu, Date dateR, String etat, String email, String type) {
        this.id = id;
        this.sujet = sujet;
        this.contenu = contenu;
        this.dateR = dateR;
        
       
        this.email = email;
        this.typerec = typerec;
          this.etat = etat;
    }
    
       public Reclamation2( String sujet, String contenu, Date dateR, String etat, String email, String typerec) {
       
        this.sujet = sujet;
        this.contenu = contenu;
        this.dateR = dateR;
        
       
        this.email = email;
        this.typerec = typerec;
          this.etat = etat;
    }
    
    
    
    
    
    
    
  private String typerec;
 
    private int archive = 0;
    private String dateArchive = null;
    private int corbeille = 0;





}










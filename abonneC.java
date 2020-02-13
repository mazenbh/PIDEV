/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author mazen
 */
public class abonneC {
    int id_c;
    String type_abonement;	
    String date_debut;	
    String date_fin;
    int cin;

    public abonneC() {
    }

    public abonneC(String type_abonement, String date_debut, String date_fin, int cin) {
        this.type_abonement = type_abonement;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.cin = cin;
    }

    @Override
    public String toString() {
        return "abonneC{" + "id_c=" + id_c + ", type_abonement=" + type_abonement + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", cin=" + cin + '}';
    }
    

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public String getType_abonement() {
        return type_abonement;
    }

    public void setType_abonement(String type_abonement) {
        this.type_abonement = type_abonement;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

   
    
    
}

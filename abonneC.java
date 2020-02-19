/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author mazen
 */
public class abonneC {
     int id_c;
    int cin;
    String type_abonement;	
    String dureé;	
    String nom;
    String prenom;

    public abonneC() {
    }

    public abonneC(int id_c, int cin, String type_abonement, String dureé, String nom, String prenom) {
        this.id_c = id_c;
        this.cin = cin;
        this.type_abonement = type_abonement;
        this.dureé = dureé;
        this.nom = nom;
        this.prenom = prenom;
    }

    public abonneC(int cin, String type_abonement, String dureé, String nom, String prenom) {
        this.cin = cin;
        this.type_abonement = type_abonement;
        this.dureé = dureé;
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId_c() {
        return id_c;
    }

    public void setId_c(int id_c) {
        this.id_c = id_c;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getType_abonement() {
        return type_abonement;
    }

    public void setType_abonement(String type_abonement) {
        this.type_abonement = type_abonement;
    }

    public String getDureé() {
        return dureé;
    }

    public void setDureé(String dureé) {
        this.dureé = dureé;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "abonneC{" + "id_c=" + id_c + ", cin=" + cin + ", type_abonement=" + type_abonement + ", dure\u00e9=" + dureé + ", nom=" + nom + ", prenom=" + prenom + '}';
    }
    
    
    
    



   
    
    
}

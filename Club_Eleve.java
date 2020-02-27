/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Haytham
 */
public class Club_Eleve {



    private int idClub ;
    private int id_User ;
    private String etat ; 
    public Club_Eleve(){
        
    }
    public Club_Eleve(int idClub, int id_User, String etat) {
        this.idClub = idClub;
        this.id_User = id_User;
        this.etat = etat;
    }
        public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public int getId_User() {
        return id_User;
    }

    public void setId_User(int id_User) {
        this.id_User = id_User;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}

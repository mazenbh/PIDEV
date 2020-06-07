/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Haytham
 */
public class Evenement_participant {

  public Evenement_participant(){
        
    }
    
    private int id ;
        private int idEvenement ; 

    private int idUser ; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Evenement_participant(int idEvenement) {
        this.idEvenement = idEvenement;
    }
        public Evenement_participant( int idUser,int idEvenement) {
        this.idEvenement = idEvenement;
        this.idUser = idUser;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    
}

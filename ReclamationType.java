/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.control.CheckBox;

/**
 *
 * @author Asus
 */
public class ReclamationType {

    private static int id = 10;
    private final int idType;
    private String nom;
    private CheckBox check = new CheckBox();

    public ReclamationType(int idType) {
        this.idType = idType;
    }

    public ReclamationType(String nom) {
        id += 1;
        idType = id;
        this.nom = nom;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        ReclamationType.id = id;
    }

    public int getIdType() {
        return idType;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }
    
     

}

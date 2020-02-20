/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ihebb
 */
public class TypeReclamation {
    private int id;
    private String nom;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public TypeReclamation(String nom) {
        this.nom = nom;
    }

    public TypeReclamation() {
    }

    @Override
    public String toString() {
        return  nom;
    }
    
    
}


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
public class Menu {
    
    private String petitdej;
    private String dej;
    private String dinner;
       private String jour;

    public Menu() {
    }

    public Menu(String petitdej, String dej, String dinner, String jour) {
        this.petitdej = petitdej;
        this.dej = dej;
        this.dinner = dinner;
        this.jour = jour;
    }

    public Menu(String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getPetitdej() {
        return petitdej;
    }

    public void setPetitdej(String petitdej) {
        this.petitdej = petitdej;
    }

    public String getDej() {
        return dej;
    }

    public void setDej(String dej) {
        this.dej = dej;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    @Override
    public String toString() {
        return "Menu{" + "petitdej=" + petitdej + ", dej=" + dej + ", dinner=" + dinner + ", jour=" + jour + '}';
    }


    
    
    
}

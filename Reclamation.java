/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author Asus
 */
public class Reclamation {

    private int idReclamation;
    private String sujet;
    private String contenu;
    private final java.sql.Date dateC = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
    private String dateR = dateC.toString();
    private int type_id;
    private int event_id;
    private int organisateur_id;
    private int user_id;
    private int traite = 0;
    private int archive = 0;
    private String dateArchive = null;
    private int corbeille = 0;
    private String dateCorbeille = null;

    private ReclamationType type;
    private UserC user;
    private UserC organisateur;
    private event event;
    private CheckBox check = new CheckBox();

    public Reclamation() {
    }

    public Reclamation(String sujet, String contenu) {

        this.sujet = sujet;
        this.contenu = contenu;

    }

    public Reclamation(ReclamationType type, String sujet, String contenu) {

        this.sujet = sujet;
        this.contenu = contenu;

        this.type = type;
        this.type_id = type.getIdType();
    }

    public int getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(int idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDateR() {
        return dateR;
    }

    public void setDateR(String dateR) {
        this.dateR = dateR;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public int getOrganisateur_id() {
        return organisateur_id;
    }

    public void setOrganisateur_id(int organisateur_id) {
        this.organisateur_id = organisateur_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTraite() {
        return traite;
    }

    public void setTraite(int traite) {
        this.traite = traite;
    }

    public int getArchive() {
        return archive;
    }

    public void setArchive(int archive) {
        this.archive = archive;
    }

    public String getDateArchive() {
        return dateArchive;
    }

    public void setDateArchive(String dateArchive) {
        this.dateArchive = dateArchive;
    }

    public int getCorbeille() {
        return corbeille;
    }

    public void setCorbeille(int corbeille) {
        this.corbeille = corbeille;
    }

    public String getDateCorbeille() {
        return dateCorbeille;
    }

    public void setDateCorbeille(String dateCorbeille) {
        this.dateCorbeille = dateCorbeille;
    }

    public ReclamationType getType() {
        return type;
    }

    public void setType(ReclamationType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idReclamation=" + idReclamation + ", sujet=" + sujet + ", contenu=" + contenu + ", dateR=" + dateR + ", type_id=" + type_id + ", user_id=" + user_id + '}';
    }

    public CheckBox getCheck() {
        return check;
    }

    public void setCheck(CheckBox check) {
        this.check = check;
    }

    public UserC getUser() {
        return user;
    }

    public void setUser(UserC user) {
        this.user = user;
    }

    public UserC getOrganisateur() {
        return organisateur;
    }

    public void setOrganisateur(UserC organisateur) {
        this.organisateur = organisateur;
    }

    public event getEvent() {
        return event;
    }

    public void setEvent(event event) {
        this.event = event;
    }

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;



import DataBase.connection;
import java.sql.SQLException;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Chambre {

    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");

    public IntegerProperty idProperty() {
        return id;
    }

    public final Integer getId() {
        return idProperty().get();
    }

    public final void setId(Integer id) {
        idProperty().set(id);
    }

    private final StringProperty style = new SimpleStringProperty(this, "Style");

    public StringProperty styleProperty() {
        return style;
    }

    public final String getStyle() {
        return styleProperty().get();
    }

    public final void setStyle(String style) {
        styleProperty().set(style);
    }

    private final StringProperty description = new SimpleStringProperty(this, "Description");

    public StringProperty descriptionProperty() {
        return description ;
    }

    public final String getDescription () {
        return descriptionProperty().get();
    }

    public final void setDescription(String description ) {
        descriptionProperty().set(description );
    }

    private final IntegerProperty numero = new SimpleIntegerProperty(this, "numero");

    public IntegerProperty numeroProperty() {
        return numero;
    }

    public final Integer getnumero() {
        return numeroProperty().get();
    }

    public final void setnumero(Integer numero) {
       numeroProperty().set(numero);
    }
    private final StringProperty nbpersonne = new SimpleStringProperty(this, "Nbpersonne");

    public StringProperty nbpersonneProperty() {
        return nbpersonne;
    }

    public final String getNbpersonne() {
        return nbpersonneProperty().get();
    }

    public final void setNbpersonne(String nbpersonne) {
        nbpersonneProperty().set(nbpersonne);
    }

    private final FloatProperty prix = new SimpleFloatProperty(this, "Prix");

    public FloatProperty prixProperty() {
        return prix;
    }

    public final Float getPrix() {
        return prixProperty().get();
    }

    public final void setPrix(Float prix) {
        prixProperty().set(prix);
    }

    private final StringProperty etat = new SimpleStringProperty(this, "Etat");

    public StringProperty etatProperty() {
        return etat;
    }

    public final String getEtat() {
        return etatProperty().get();
    }

    public final void setEtat(String etat) {
        etatProperty().set(etat);
    }

    public Chambre() {
    }

    public Chambre(Integer id, String style, String description, Integer numero, String nbpersonne, Float prix, String etat) {
        setId(id);
        setStyle(style);
        setDescription(description);
        setnumero(numero);
        setNbpersonne(nbpersonne);
        setPrix(prix);
        setEtat(etat);
    }

    public Chambre(Integer id, String description, Integer numero, String nbpersonne, Float prix) {
        setId(id);

        setDescription(description);
        setnumero(numero);
        setNbpersonne(nbpersonne);
        setPrix(prix);
    }

    public static void InsertChambre(String style, String description, Integer numero, String nbpersonne, Float prix, String etat) throws SQLException {
        String sql = "insert into foyer (style,description,numero,nbpersonne,prix,etat) values ('" + style + "', '" + description + "', '" + numero + "', '" + nbpersonne + "', '" + prix + "', '" + etat + "')";

        try {
            connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur dinsertion" + e);
            throw e;
        }
    }

    public static void SuppChambre(Integer id) throws SQLException {
        String sql = "DELETE FROM `foyer` WHERE `foyer`.`id` =" + Integer.toString(id);
        try {
            connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de suppression" + e);
            throw e;
        }
    }

    public static void ModifChambre(int id, String style, String description, Integer numero, String nbpersonne, Float prix, String etat) throws SQLException {
        String sql = "update foyer set style= '" + style + "',description ='" + description + "',numero='" + numero + "',nbpersonne='" + nbpersonne + "',prix='" + prix + "',etat='" + etat + "' where id ='" + id + "' ";
        try {
            connection.dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Erreur de modification" + e);
            throw e;
        }

    }

}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import entities.UserC;
import entities.event;
import entities.ReclamationType;
import DataBase.MyConnection;
import Interfaces.IReclamation;
import entities.Reclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class ReclamationServices implements IReclamation {

    public void insert1(Reclamation r) {

        String requete = " INSERT INTO reclamation ( sujet , contenu , traite ,dateR , archive , corbeille , type_id, event_id , organisateur_id , user_id , dateArchive , dateCorbeille)"
                + "VALUES('" + r.getSujet() + ""
                + "," + r.getContenu() + ","
                + "," + r.getTraite() + ","
                + "," + r.getDateR() + ","
                + "," + r.getArchive() + ","
                + "," + r.getCorbeille() + ","
                + "," + r.getType_id() + ","
                + "," + r.getEvent_id() + ","
                + "," + r.getOrganisateur_id() + ","
                + "," + r.getUser_id() + ","
                + "," + r.getDateArchive() + ","
                + "," + r.getDateCorbeille() + "')";

        try {
            Statement st = MyConnection.getInstance().getCnx().createStatement();
            st.executeUpdate(requete);
            System.err.println("reclamation Ajouté !");

        } catch (SQLException ex) {
            System.out.println("statement fail !");
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void insert(Reclamation r) {

        String requete = " INSERT INTO reclamation ( sujet , contenu , traite ,dateR , archive , corbeille , type_id, event_id , organisateur_id , user_id) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, r.getSujet());
            pst.setString(2, r.getContenu());
            pst.setInt(3, r.getTraite());
            pst.setString(4, r.getDateR());
            pst.setInt(5, r.getArchive());
            pst.setInt(6, r.getCorbeille());
            pst.setInt(7, r.getType_id());
            pst.setInt(8, r.getEvent_id());
            pst.setInt(9, r.getOrganisateur_id());
            pst.setInt(10, r.getUser_id());

            pst.executeUpdate();
            System.err.println("reclamation Ajouté !");

        } catch (SQLException ex) {
            System.out.println("statement fail !");
            System.err.println(ex.getMessage());

        }
    }

    @Override
    public void update(Reclamation r) {

        String requete = " UPDATE reclamation SET sujet=? , contenu=?, type_id=?, event_id=? , organisateur_id=?) WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setString(1, r.getSujet());
            pst.setString(2, r.getContenu());
            pst.setObject(3, r.getType_id());
            pst.setInt(4, r.getEvent_id());
            pst.setInt(5, r.getOrganisateur_id());
            pst.setInt(6, r.getIdReclamation());

            pst.executeUpdate();
            System.err.println("reclamation Modifié !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public void delete(Reclamation r) {
        String requete = " DELETE FROM reclamation WHERE id=? ";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getIdReclamation());
            pst.executeUpdate();
            System.err.println("reclamation Supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Reclamation> selectAll() {

        ArrayList<Reclamation> reclamations = new ArrayList();

        try {
            String requete = " SELECT * From reclamation where archive = 0 and  corbeille = 0 ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setIdReclamation(res.getInt("id"));
                r.setSujet(res.getString("sujet"));
                r.setContenu(res.getString("contenu"));
                r.setTraite(res.getInt("traite"));
                r.setDateR(res.getString("dateR"));
                r.setArchive(res.getInt("archive"));
                r.setCorbeille(res.getInt("corbeille"));
                r.setType_id(res.getInt("type_id"));
                r.setEvent_id(res.getInt("event_id"));
                r.setOrganisateur_id(res.getInt("organisateur_id"));
                r.setUser_id(res.getInt("user_id"));
                r.setDateArchive(res.getString("dateArchive"));
                r.setDateCorbeille(res.getString("dateCorbeille"));

                r.setUser(selectUser(r.getUser_id()));
                r.setOrganisateur(selectUser(r.getOrganisateur_id()));
                r.setType(selectType(r.getType_id()));

                reclamations.add(r);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectAll()");
        }

        return reclamations;
    }

    @Override
    public void toCorbeille(Reclamation r) {

        String requete = " UPDATE reclamation SET corbeille = 1,dateCorbeille=?,archive = 0,dateArchive=null WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            java.sql.Date dateA = new java.sql.Date(Calendar.getInstance().getTimeInMillis());

            pst.setString(1, dateA.toString());
            pst.setInt(2, r.getIdReclamation());

            pst.executeUpdate();
            System.err.println("reclamation est envoyé au corbeille !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void toArchive(Reclamation r) {
        String requete = " UPDATE reclamation SET archive = 1, dateArchive= ? ,corbeille =0 ,dateCorbeille=null WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            java.sql.Date dateA = new java.sql.Date(Calendar.getInstance().getTimeInMillis());
            pst.setString(1, dateA.toString());
            pst.setInt(2, r.getIdReclamation());

            pst.executeUpdate();
            System.err.println("reclamation est envoyé à l'archive !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void restaurer(Reclamation r) {

        String requete = " UPDATE reclamation SET archive = 0, dateArchive=null ,corbeille =0 ,dateCorbeille=null WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getIdReclamation());

            pst.executeUpdate();
            System.err.println("reclamation est restaurer !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }

    @Override
    public ArrayList<Reclamation> selectCorbeille() {

        ArrayList<Reclamation> reclamations = new ArrayList();

        try {
            String requete = " SELECT * From reclamation where corbeille = 1 ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setIdReclamation(res.getInt("id"));
                r.setSujet(res.getString("sujet"));
                r.setContenu(res.getString("contenu"));
                r.setTraite(res.getInt("traite"));
                r.setDateR(res.getString("dateR"));
                r.setArchive(res.getInt("archive"));
                r.setCorbeille(res.getInt("corbeille"));
                r.setType_id(res.getInt("type_id"));
                r.setEvent_id(res.getInt("event_id"));
                r.setOrganisateur_id(res.getInt("organisateur_id"));
                r.setUser_id(res.getInt("user_id"));
                r.setDateArchive(res.getString("dateArchive"));
                r.setDateCorbeille(res.getString("dateCorbeille"));

                r.setUser(selectUser(r.getUser_id()));
                r.setOrganisateur(selectUser(r.getOrganisateur_id()));
                r.setType(selectType(r.getType_id()));

                reclamations.add(r);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectAll()");
        }

        return reclamations;

    }

    @Override
    public ArrayList<Reclamation> selectArchive() {

        ArrayList<Reclamation> reclamations = new ArrayList();

        try {
            String requete = " SELECT * From reclamation where archive = 1 ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                Reclamation r = new Reclamation();
                r.setIdReclamation(res.getInt("id"));
                r.setSujet(res.getString("sujet"));
                r.setContenu(res.getString("contenu"));
                r.setTraite(res.getInt("traite"));
                r.setDateR(res.getString("dateR"));
                r.setArchive(res.getInt("archive"));
                r.setCorbeille(res.getInt("corbeille"));
                r.setType_id(res.getInt("type_id"));
                r.setEvent_id(res.getInt("event_id"));
                r.setOrganisateur_id(res.getInt("organisateur_id"));
                r.setUser_id(res.getInt("user_id"));
                r.setDateArchive(res.getString("dateArchive"));
                r.setDateCorbeille(res.getString("dateCorbeille"));

                r.setUser(selectUser(r.getUser_id()));
                r.setOrganisateur(selectUser(r.getOrganisateur_id()));
                r.setType(selectType(r.getType_id()));

                reclamations.add(r);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectAll()");
        }

        return reclamations;

    }

    public UserC selectUser(int id) {

        UserC u = new UserC();

        try {
            String requete = " SELECT * From fos_user where id = ? ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);

            ResultSet res = pst.executeQuery();

            while (res.next()) {

                u.setId(res.getInt("id"));
                u.setUsername(res.getString("name"));
                u.setLastname(res.getString("lastname"));
                u.setEmail(res.getString("email"));
                u.setName(u.getUsername() + " " + u.getLastname());

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectUser()");
        }

        return u;

    }

    public ReclamationType selectType(int id) {

        ReclamationType type = new ReclamationType(id);

        try {
            String requete = " SELECT * From type_reclamation where id = ? ";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);

            ResultSet res = pst.executeQuery();

            while (res.next()) {

                type.setId(res.getInt("id"));
                type.setNom(res.getString("nom"));

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectUser()");
        }

        return type;

    }

    public ArrayList<UserC> selectListOrg(int id) {

        ArrayList<UserC> orgsList = new ArrayList();

        try {
            String requete = " SELECT  fos_user.* "
                    + " FROM evenement__participant , event , fos_user "
                    + " WHERE evenement__participant.idParticipant = ? "
                    + " AND  evenement__participant.idEvent = event.id  "
                    + " AND  event.idorganisateur = fos_user.id";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                UserC u = new UserC();

                u.setId(res.getInt("id"));
                u.setUsername(res.getString("name"));
                u.setLastname(res.getString("lastname"));
                u.setEmail(res.getString("email"));
                u.setName(u.getUsername() + " " + u.getLastname());

                orgsList.add(u);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectListOrg()");
        }

        return orgsList;

    }

    public ArrayList<String> selectListEventReclames(int id) {

        ArrayList<String> eventList = new ArrayList();

        try {
            //String requete = " SELECT  *  FROM  event WHERE idorganisateur = ? ";
            String requete = " SELECT  event.*"
                    + " FROM event , reclamation "
                    + " WHERE reclamation.organisateur_id = ? "
                    + " AND  reclamation.event_id = event.id  ";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                event e = new event();

                e.setId(res.getInt("id"));
                e.setName(res.getString("name"));
                e.setIdorganisateur(res.getString("idorganisateur"));
                e.setNbReclamation(nbrReclamationPerEvent(e.getId()));

                if (eventList.contains("" + e.getId())) {
                } else {
                    eventList.add("" + e.getId());
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectListEventReclames()");
        }

        return eventList;

    }

    public int nbrReclamationPerEvent(int id) {

        int nb = 0;

        try {

            String requete = " SELECT  *"
                    + " FROM  reclamation "
                    + " WHERE reclamation.event_id = ? ";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                nb += 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb;
    }

    public ArrayList<event> selectEventReclames(int id) {

        ArrayList<event> eventList = new ArrayList();

        try {
            //String requete = " SELECT  *  FROM  event WHERE idorganisateur = ? ";
            String requete = " SELECT  event.*"
                    + " FROM event , reclamation "
                    + " WHERE reclamation.organisateur_id = ? "
                    + " AND  reclamation.event_id = event.id  ";

            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                event e = new event();

                e.setId(res.getInt("id"));
                e.setName(res.getString("name"));
                e.setIdorganisateur(res.getString("idorganisateur"));
                e.setNbReclamation(nbrReclamationPerEvent(e.getId()));

                if (eventList.contains(e)) {
                } else {
                    eventList.add(e);
                }

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectListEventReclames()");
        }

        return eventList;

    }
    
    public void BanneOrganisateur(UserC r) {

        String requete = " UPDATE fos_user SET enabled=? , dateBan=?, nb_ban=? WHERE id=?";

        try {
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, r.getEnabled());
            pst.setString(2, r.getDateBan());
            pst.setObject(3, r.getNb_ban());
            pst.setInt(4, r.getId());

            pst.executeUpdate();
            System.err.println("organisateur modifie !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

    }
    
}

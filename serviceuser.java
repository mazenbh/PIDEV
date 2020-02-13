/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.teknikindustries.bulksms.SMS;
import entite.user;
import java.awt.AWTException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DB;

/**
 *
 * @author mazen
 */
public class serviceuser {
    private Connection con;
    private Statement ste;

    public serviceuser() {
        con = DB.getInstance().getConnection();

    }
    
    

    public void ajouterUser(user u) throws SQLException
    {
        try {
            
            String x =encrypt(u.getPassword(), u);
            PreparedStatement pre=con.prepareStatement("INSERT INTO `fos_user`( `username`,  `email`,  `password`, `roles`, `nom`, `prenom`, `num_tel`, `photo`,`cin`,`sexe`)   VALUES ( '"+u.getUsername()+"','"+u.getEmail()+"','"+x+"','"+u.getRoles()+"','"+u.getNom()+"','"+u.getPrenom()+"','"+u.getNum_tel()+"','"+u.getPhoto()+"','"+u.getCin()+"','"+u.getSexe()+"');");
                
    pre.executeUpdate();
            System.out.println("ajout avec succes");
        } catch (Exception e) {
        }
    }
    public void afficherUser() {
        PreparedStatement pt;
        try {

            pt = con.prepareStatement("select * from fos_user");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {
                System.out.println("Personne [id :" + rs.getInt(1) + ",username : " + rs.getString(2) + ",email :" + rs.getString(4)+ ",password :" + rs.getString(8)+ ",roles :" + rs.getString(12));
            }
        } catch (SQLException ex) {
            Logger.getLogger(serviceuser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void supprimerUser(user p) throws AWTException {
        PreparedStatement pt;
        try {
            String[] args1 = null;
             TrayIconDemo.main(args1);
            pt = con.prepareStatement("delete from fos_user where username=? ");
            pt.setString(1, p.getUsername());
            pt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(serviceuser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void modifierUser(int id, String username,String password,String email,String nom,String prenom ) {
        try {
            
            String query = "update fos_user set username='" + username +  "',password='" + password + "',email='" + email + "',nom='" + nom +
                        "',prenom='" + prenom + "' where id=" + id;
                       

                ste = con.createStatement();

                ste.executeUpdate(query);
                System.out.println("bien modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(serviceuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        

            
 
    }
    
    
    
    public void trier() {
        PreparedStatement pt;
        String req = "select * from fos_user ORDER BY username";
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);
            System.out.println("tri par username: ");

            while (rs.next()) {
                System.out.println(rs.getString("username"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur tri");
        }
    }
    
    
    public void rechercherUtilisateurparcaractere(String caractere) {

        String req = "select * from fos_user where nom  LIKE '" + caractere + "%'";
        try {
            ste = con.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                System.out.println("le nom que vous rechercher: " + rs.getString("nom"));

            }

        } catch (SQLException ex) {
            System.out.println("erreur recherche");
        }
    }
    public void filtre(String caractere) {

        
       String req = "  SELECT * FROM fos_user WHERE username  like '"+ "%"+caractere+"%"+"' or nom like '"+ "%"+caractere+"%"+"' ORDER BY id DESC " ;
        try {
            ste = con.createStatement();
             ResultSet rs = ste.executeQuery(req);
            System.out.println("filtre par id desc: ");

            while (rs.next()) {
                
                System.out.println("Id : " + rs.getInt("id") + "  username :" + rs.getString("username") + "  nom:" + 
                        rs.getString("nom") + "  email :" + rs.getString("email")
                        +"  prenom :"+rs.getString("prenom"));
            

            }

        } catch (SQLException ex) {
            System.out.println("erreur tri");
        }
    }
    public String encrypt(String password,user u ){
        String passwordToHash = u.getPassword();
         String generatedPassword = null;
          try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordToHash.getBytes());
        byte[] bytes = md.digest();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
        return generatedPassword;
        
        
    }
    
    public void rechercherUtilisateurparUsername(String username) {
        String usernamee = "'" + username + "'";
        String req = "select * from fos_user where username =" + usernamee;
        try {
            ste = con.createStatement();
            
             ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                System.out.println("rechecrhe par username");
                System.out.println("le nom  est: " + rs.getString("nom") + " ,l'email est: " + rs.getString("email"));

            }

        } catch (SQLException ex) {
            System.out.println("Il nexiste aucun utulisateur avec ce username");
        }
    }
            

   
}

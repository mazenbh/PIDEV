/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entites.TrayIconDemo;
import Entites.User;
import Utils.DataBase;
import java.awt.AWTException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;


/**
 *
 * @author mazen
 */
public class UserService {
    
     Connection c = DataBase.getInstance().getConnection();
       
    Statement ste;

    public UserService() {
        try {
            ste = c.createStatement();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
     public int authentification(User u){
        int test = 0;
        
          try {
          String req2="SELECT `id`, `username`, `password` FROM `fos_user`";
        

          ResultSet res=  ste.executeQuery(req2);
          
          while (res.next() && (test==0)) {
            if (u.getUsername().equals((res.getString("username"))) && (res.getString("password").equals(u.getPassword()))){
                System.err.println("1");
                 test=res.getInt(1);
                 System.err.println("2");
                 String query = "update fos_user set demande='connected' where id='"+test+"'";
                 System.err.println("3");
                 int res1=  ste.executeUpdate(query);
                 System.err.println("4");
                 System.err.println(test);
                 
            }
            
            else{
           System.err.println("erreur");

            test=0;
            }
            }
    }   catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        }
     
     
      public void creerUser(User u) throws SQLException{
            //String x =encrypt(u.getPassword(), u);
            String req1="INSERT INTO `fos_user` (`username`, `password`, `roles`, `email`, `nom`, `prenom`, `cin`, `num_tel`, `sexe`) VALUES ('"+u.getUsername()+"', '"+u.getPassword()+"', '"+u.getRoles()+"', '"+u.getEmail()+"', '"+u.getNom()+"', '"+u.getPrenom()+"', '"+u.getCin()+"', '"+u.getNum_tel()+"', '"+u.getSexe()+"');";
            
        try{

         
            ste.executeUpdate(req1);
            System.out.println("elment insert");     
        }catch (SQLException e) {

        JOptionPane.showMessageDialog(null,"existe deja");
        throw e ;
        }
          
    }
      
      
       public ObservableList<User>  afficherUser() throws SQLException{
        ObservableList<User> data = FXCollections.observableArrayList();
//Statement st= C.createStatement() ;
String req = "SELECT `id`,`nom`, `prenom`,`email`, `cin`, `num_tel`, `sexe` FROM `fos_user`" ;
ResultSet rs= ste.executeQuery(req) ;
while (rs.next()){
    //LocalTime t1 = rs.getTime(5).toLocalTime()  ;
            //LocalTime t2 = rs.getTime(6).toLocalTime();
           
    //LocalDate d= rs.getDate(7).toLocalDate();
    User e= new User (rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getString(6),rs.getString(7));
   /* System.out.println("**************************************");
    System.out.println(e.getH_debut());
    System.out.println(e.getH_fin());   
    */
    data.add(e);
    
}

return data ;
}
    
    public ArrayList<User> getAllPE() throws SQLException{
        ArrayList<User> retour = new ArrayList<User>();
        Statement stm = c.createStatement();
        User per=null;
        String req = "SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` FROM `fos_user`where roles='etudiant' or roles='abonne'" ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
          
                    
      per=new User(resultat.getInt(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),resultat.getString(5),resultat.getString(6));
  
      retour.add(per);
        }
  
  return retour;
  }
    
    public ArrayList<User> getAllPEn() throws SQLException{
        ArrayList<User> retour = new ArrayList<User>();
        Statement stm = c.createStatement();
        User per=null;
        String req = "SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` FROM `fos_user`where roles='enseignant'" ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
          
                    
      per=new User(resultat.getInt(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),resultat.getString(5),resultat.getString(6));
  
      retour.add(per);
        }
  
  return retour;
  }
    
    public ArrayList<User> getAllPP() throws SQLException{
        ArrayList<User> retour = new ArrayList<User>();
        Statement stm = c.createStatement();
        User per=null;
        String req = "SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` FROM `fos_user`where roles='parent'" ;
        ResultSet resultat = stm.executeQuery(req);
      while (resultat.next()) {   
          
                    
      per=new User(resultat.getInt(1),resultat.getString(2),resultat.getString(3),resultat.getString(4),resultat.getString(5),resultat.getString(6));
  
      retour.add(per);
        }
  
  return retour;
  }
    
  
    public ArrayList<User> rechercheUser(String rech) throws SQLException {

        ArrayList<User> off = new ArrayList<>();
String req = "SELECT * FROM fos_user where nom Like '%"+rech+"%'  ";     
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

                      
   User   per= new User (rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getInt(5),rst.getString(6),rst.getString(7));
  

            off.add(per);
        }
        return off;
    }
        
     public String rechercherparrole(int id) {
        String test = null;
        String req = "SELECT roles from fos_user where id='"+id+"'";
             
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
               test= stp.getString(1);
                System.out.println(test);
            }
            else
            {
                test="not found";
                System.out.println(test);
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return test;
        
    } 
     
         /*public ArrayList<User> rechercherUser(String rech) throws SQLException {

        ArrayList<User> off = new ArrayList<>();
String req = "SELECT `id` ,`nom`,`prenom`,`email`,`cin`,`sexe` FROM fos_user where `nom` Like '%"+rech+"%'  ";     
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {
                int id=rst.getInt("id");
                String nom=rst.getString("nom");
                String prenom=rst.getString("prenom");
                String email = rst.getString("email");
                String cin = rst.getString("cin");
               
                String sexe = rst.getString("sexe");  
                
                User u = new User(id, nom, prenom, email,cin,sexe);
        off.add(u);
        }
        return off;
    }*/
     
      
     
           public ArrayList<User> rechercherNomP(String rech) throws SQLException {

        ArrayList<User> off = new ArrayList<>();
           User e = null;
        String req = "SELECT `id` ,`nom`,`prenom`,`email`,`cin`,`sexe` FROM fos_user where nom Like '%"+rech+"%' and roles='parent'";  
         
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);
        

        while (rst.next()) {
                              e = new User();
                
              
                e.setId(rst.getInt("id"));
                e.setNom(rst.getString("nom"));
                e.setPrenom(rst.getString("prenom"));
                e.setEmail(rst.getString("email"));
                e.setCin(rst.getString("cin"));
                e.setSexe (rst.getString("sexe"));
            
        

             
                
                      
   User per = new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
  

            off.add(e);
        }
        return off;
    } 
       
            public ArrayList<User> rechercherNomEn(String rech) throws SQLException {

        ArrayList<User> off = new ArrayList<>();
           User e = null;
        String req = "SELECT `id` ,`nom`,`prenom`,`email`,`cin`,`sexe` FROM fos_user where nom Like '%"+rech+"%' and roles='enseignant'";  
         
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);
        

        while (rst.next()) {
                              e = new User();
                
              
                e.setId(rst.getInt("id"));
                e.setNom(rst.getString("nom"));
                e.setPrenom(rst.getString("prenom"));
                e.setEmail(rst.getString("email"));
                e.setCin(rst.getString("cin"));
                e.setSexe (rst.getString("sexe"));
            
        

             
                
                      
   User per = new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
  

            off.add(e);
        }
        return off;
    } 
    
             public ArrayList<User> rechercherNomEt(String rech) throws SQLException {

        ArrayList<User> off = new ArrayList<>();
           User e = null;
        String req = "SELECT `id` ,`nom`,`prenom`,`email`,`cin`,`sexe` FROM fos_user where nom Like '%"+rech+"%' and roles='etudiant'";  
         
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);
        

        while (rst.next()) {
                              e = new User();
                
              
                e.setId(rst.getInt("id"));
                e.setNom(rst.getString("nom"));
                e.setPrenom(rst.getString("prenom"));
                e.setEmail(rst.getString("email"));
                e.setCin(rst.getString("cin"));
                e.setSexe (rst.getString("sexe"));
            
        

             
                
                      
   User per = new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
  

            off.add(e);
        }
        return off;
    } 
             
               
            public User getUserByid(int id) throws SQLException {
        User emp = null;
        Statement stm = c.createStatement();
        String req = "SELECT * FROM `fos_user` WHERE  id ='"+id+"'";
        ResultSet resultat = stm.executeQuery(req);
        if (resultat.next()) {
            int iid= resultat.getInt("id");
            String username = resultat.getString("username");
            String password = resultat.getString("password");
            String email = resultat.getString("email");
            String nom = resultat.getString("nom");
            String prenom = resultat.getString("prenom");
            String cin = resultat.getString("cin");
            String sexe = resultat.getString("sexe");
            String role = resultat.getString("roles");
            
            emp = new User(iid, username, password,email, nom, prenom, cin, sexe, role);
            
        }          
         return emp;
     }
            
            
            
             public void modifieruser(int id,User u) {
        
         
        
   String sql="UPDATE fos_user SET `nom`=?,`prenom`=?,`email`=?,`cin`=?,`sexe`=? WHERE `fos_user`.`id`='" + id + "' ";
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
           
            ps.setString(1, u.getNom());
            ps.setString(2, u.getPrenom());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getCin());
            ps.setString(4, u.getSexe());
            
            ps.executeUpdate();
            int rowsUpdated=ps.executeUpdate();
            if (rowsUpdated>0){
            System.out.println("La modification de "+id+" a été éffectué avec succée ");
            }}
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
   
     
     
     public ArrayList<User> recherche(String rech) throws SQLException {

        ArrayList<User> off = new ArrayList<>();
String req = "SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` FROM fos_user where nom Like '%"+rech+"%'  ";     
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);

        while (rst.next()) {

                      
   User   per=new User(rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
  

            off.add(per);
        }
        return off;
    }
      
    public void delete(User r) throws AWTException {
        String requete = " DELETE FROM fos_user WHERE id=? ";

        try {
             String[] args1 = null;
             TrayIconDemo.main(args1);
            PreparedStatement pst = DataBase.getInstance().getConnection().prepareStatement(requete);
            pst.setInt(1, r.getId());
            pst.executeUpdate();
            System.err.println("user Supprimé !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
        
    }

         public void modifierUser(User u) {
        
         
        
   String sql="UPDATE fos_user SET `username`=?,`password`=?,`email`=? ,`nom`=? ,`prenom`=? WHERE cin Like '"+u.getCin()+"'";
   PreparedStatement ps;
        try {
            ps = c.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getEmail());
            ps.setString(4, u.getNom());
            ps.setString(5, u.getPrenom());
            //ps.setString(6, u.getCin());

            
            ps.executeUpdate();
           // int rowsUpdated=ps.executeUpdate();
           // if (rowsUpdated>0){
            System.out.println("La modification de user"+u.getCin()+" a été éffectué avec succée ");
            }
        catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
         /* public String encrypt(String password,User u ){
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
          */
          
    public void toArchive(User r) {
        String requete = " UPDATE fos_user SET demande = '1'  WHERE id=?";

        try {
            PreparedStatement pst = DataBase.getInstance().getConnection().prepareStatement(requete);
            
            
            pst.setInt(1, r.getId());

            pst.executeUpdate();
            System.err.println("reclamation est envoyé à l'archive !");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
     public ArrayList<User> selectArchive() {

        ArrayList<User> User = new ArrayList();

        try {
            String requete = " SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` From fos_user where demande = '1' ";
            PreparedStatement pst = DataBase.getInstance().getConnection().prepareStatement(requete);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                User r = new User();
                r.setId(res.getInt("id"));
                r.setNom(res.getString("nom"));
                r.setPrenom(res.getString("prenom"));
                r.setEmail(res.getString("email"));
                r.setCin(res.getString("cin"));
                r.setSexe(res.getString("string"));
               

                
              
               

                User.add(r);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.ReclamationServices.selectAll()");
        }

        return User;

    }
     
     
     
     ///////////////////////////////////////
     
     
    
    public ArrayList<User> selectAll() {

        ArrayList<User> listtt = new ArrayList();

        try {
            String requete = " SELECT `id`,`nom`, `prenom`,`email`,`cin`,`sexe` From fos_user ";
            PreparedStatement pst = DataBase.getInstance().getConnection().prepareStatement(requete);

            ResultSet res = pst.executeQuery();

            while (res.next()) {
                User r = new User();
                r.setId(res.getInt("id"));
                r.setNom(res.getString("nom"));
                r.setPrenom(res.getString("prenom"));
                r.setEmail(res.getString("email"));
                r.setCin(res.getString("cin"));
                r.setSexe(res.getString("sexe"));
              
                listtt.add(r);

            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("Services.UserService.selectAll()");
        }

        return listtt;
    }
    
    
    public void signout() throws SQLException{
          String query = "update fos_user set status='not'";
        
                ste = c.createStatement();
                

                ste.executeUpdate(query);
    }
     
     
     
     
     /////////////////////////////////////////
    
   /* public ArrayList<User> mdpoublie(String rech) throws SQLException
    {
          ArrayList<User> off = new ArrayList<>();
           User e = null;
        String req = "SELECT `id`,`nom`, `prenom`,`email`,`cin`,`password`  FROM fos_user where cin='"+rech+"'";  
         
        Statement stm = c.createStatement();
        ResultSet rst = stm.executeQuery(req);
        

        while (rst.next()) {
                              e = new User();
                
              
                 e.setId(rst.getInt("id"));
                e.setNom(rst.getString("nom"));
                e.setPrenom(rst.getString("prenom"));
                e.setEmail(rst.getString("email"));
                e.setCin(rst.getString("cin"));
                e.setPassword(rst.getString("password"));
           
            
          User per = new User(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6));
  

            off.add(e);
        }
        return off;
    }*/
    
    
    
    ////////////////////////////////////////////
      public User rechercheparcin(String cin) {
        String test = null;
        String password=null;
        String email=null;
        String Sex=null;
        String req = "SELECT cin,password,email from fos_user where cin='"+cin+"'";
              User u = new User();
        try {
           
            ResultSet stp=ste.executeQuery(req);
            stp.last();
            if(stp.getRow()!=0)
            {
               test= stp.getString(1);
               password= stp.getString(2);
               email= stp.getString(3);
               

                u.setCin(test);
                u.setPassword(password);
                u.setEmail(email);
                u.setSexe(Sex);

               
                System.out.println(test);
            }
            else
            {
                test="not found";
                System.out.println(test);
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        
    } 
     
    
    
    ////////////////////////////////////////////////
     
   
}
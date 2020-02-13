/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mazen
 */
public class DB {
     String url = "jdbc:mysql://localhost:3306/symfony";
     String login = "root";
     String pwd = "";
    public  static DB db;
    public Connection con;
    public DB() {
         try {
             con=DriverManager.getConnection(url, login, pwd);
             System.out.println("connexion etablie");
         } catch (SQLException ex) {
             System.out.println(ex);
         }
    }
    
    public Connection  getConnection()
    {
    return con;
    }     
    public static DB getInstance()
    {if(db==null)
        db=new DB();
    return db;
    }     
    
}

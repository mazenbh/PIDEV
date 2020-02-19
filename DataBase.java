/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author mazen
 */
public class DataBase {
    private static DataBase data;
    private Connection con;
    String url = "jdbc:mysql://localhost:3306/symfony";
    String login = "root";
    String pwd = "";

    private DataBase() {
        try {
            con = DriverManager.getConnection(url, login, pwd);
            System.out.println("connexion etablie");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
   

    public Connection getConnection() {
        return con;
    }

    public static DataBase getInstance() {
        if (data == null) {
            data = new DataBase();
        }
        return data;
    }
    
}

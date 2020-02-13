/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.teknikindustries.bulksms.SMS;
import entite.abonneC;
import entite.user;
import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Create_QR;
import service.JavaMailUtil;
import service.Read_QR;
import service.TrayIconDemo;
import service.Webcamtest;
import service.Write_to_excel_file_from_my_sql_database;
import service.pdf;
import service.servicecantine;
import service.serviceuser;

/**
 *
 * @author mazen
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
    user p1 = new user("mazen", "mazen.benhmida@esprit.tn",  "nopass4u", "admin", "nom", "prenom", 28384150," nophoto",132,"femme");
    user p2 = new user("hamza", "hamza.benhmida@esprit.tn",  "nopass4u", "parent", "nom", "prenom", 28384150," nophoto",10032,"femme");
     user p3 = new user("pepsi", "pepsi.benhmida@esprit.tn",  "nopass4u", "etudiant", "nom", "prenom", 28384150," nophoto",132,"femme");
     user p4 = new user("aman", "aman.benhmida@esprit.tn",  "non", "parent", "nom", "prenom", 28384150," nophoto",13552,"hommee");
     user p5 = new user("wew", "aman.benhmida@esprit.tn",  "non", "parent", "hajkefta", "prenom", 28384150," nophoto",132,"femme");
      user p6 = new user("lili", "sirine.benhmida@esprit.tn",  "non", "parent", "hiridi", "prenom", 28384150," nophoto",71342,"homme");
      user p7 = new user("wew25", "aman.benhmida@esprit.tn",  "non", "parent", "hiridi", "prenom", 28384150," nophoto",17732,"femme");
      abonneC a1=new abonneC("demi pension","10/01/2020","11/01/2020",123);
      abonneC a2=new abonneC("demi complete","10/01/2020","11/01/2020",456);
    user p44 = new user("mazen", "mazen.benhmida@esprit.tn",  "abcde", "admin", "nom", "prenom", 28384150," nophoto",132,"femme");
        serviceuser srv = new serviceuser();
        servicecantine srv1 = new servicecantine();
     
            // srv.ajouterUser(p3);
            //srv.ajouterUser(p1);
            // srv.ajouterUser(p2);
            //srv.ajouterUser(p4);
            //srv.ajouterUser(p5);
            //srv.ajouterUser(p6);
            //srv.ajouterUser(p44);
            // srv.afficherUser();
            //srv.supprimerUser(p3);
            //srv.modifierUser(25, "vld25", "aaaa", "email@email.com", "maz", "en");
            //srv.trier();
            //srv.rechercherUtilisateurparcaractere("h");
            //srv1.ajouterAbonné(a1);
            //srv1.ajouterAbonné(a2);
            //srv1.afficherAbonné();
            //srv1.supprimerAbonné(a2);
             //srv1.modifierAbonné(4, "new new");
            
            //srv.filtre("m");
             //mail
           // JavaMailUtil.sendmail("hamzaayaakoubi@gmail.com");
            
            //notif
            // String[] args1 = null;
            //TrayIconDemo.main(args1);
            //pdf
            //String[] args1 = null;
             //pdf.main(args1);
    
     int choice=0;
      
        Scanner sc =new Scanner(System.in);
        do {   
        System.out.println("menu");
        System.out.println("1:ajouterUser");
        System.out.println("2:modifierUser");
        System.out.println("3:supprimerUser");
        System.out.println("4:afficherUser");
        System.out.println("5:recherche par username");
        System.out.println("6:recherche par caractre");
        System.out.println("7:tri par username");
        System.out.println("8:filtre par caractere");
        System.out.println("9:statistique par sexe");
        System.out.println("10:Take a photo for your profil ");
        System.out.println("11:Display list excel");
        System.out.println("12:Create QR code");
        System.out.println("13:Read QR code");
        System.out.println("14:mail");
        System.out.println("15:pdf");
        System.out.println("Enter choice");
        choice=sc.nextInt();
        
        switch (choice)
        {
            case 1:
                srv.ajouterUser(p6);
                break;
            case 2:
                srv.modifierUser(30, "mazenbh", "password", "@", "mz", "bh");
                break;
            case 3:
                srv.supprimerUser(p6);//suppression par id
                break;
            case 4:
                srv.afficherUser();
                break;
            case 5:
                srv.rechercherUtilisateurparUsername("mazen");//rechercher par username
                break; 
            case 6:
                srv.rechercherUtilisateurparcaractere("h");//rechercher par caractere FIRSTNAME
                break;
            case 7:
                srv.trier();//tri des utulisateur par username
                break;
            case 8:
                srv.filtre("m");//filtre par caractere
                break;
            case 9:
                 {
            String[] args1 = null;
            PieChart_DB.main(args1);
        }
                break;
            case 10:
                String[] args1 = null;
            Webcamtest.main(args1);
                break;
            case 11:
        String[] args2 = null;
            Write_to_excel_file_from_my_sql_database.main(args2);
                 break;
            case 12:
        {
            String[] args3 = null;
            Create_QR.main(args3);
        }
                break;
            case 13:
        {
             String[] args3 = null;
            Read_QR.main(args3);
           
        }
          
                break;
            case 14:
        {
           JavaMailUtil.sendmail("mohamedamine.mbarki@esprit.tn");
        }
        break;
            case 15:
        {
           String[] args5 = null;
             pdf.main(args5);
        }
                
            default :
                System.out.println("not in menu");       
        }
        
            
        } while (choice!=0);
    }
    
}

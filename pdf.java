/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.itextpdf.text.BadElementException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author mazen
 */
public class pdf {
     
         public static void main(String args[]) throws FileNotFoundException, DocumentException, BadElementException, IOException 
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream("xixo.pdf"));
        document.open();
        Image img =Image.getInstance("firstcapture.png");
        document.add(new Paragraph("carte etudiant"));
        document.add(img);
        document.close();
        
    }
    }
    
    
    
    

    
        
    


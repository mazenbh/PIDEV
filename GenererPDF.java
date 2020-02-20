/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Dhoha

    



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import entitie.Paiment_eleve;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


import utils.MyDB;

/**
 * 
 * @salma
 */
public class GenererPDF extends JFrame {
	@SuppressWarnings("unchecked")
	public GenererPDF(Paiment_eleve res) {
		Connection connection;
		try {
			// - Connexion à la base
			connection =MyDB.getInstance().getConnexion();
			// - Chargement et compilation du rapport
		
			JasperDesign jasperDesign = JRXmlLoader
					.load("C:\\Users\\Dhoha\\Documents\\NetBeansProjects\\Pi_dev\\src\\service\\newReport1.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			// - Paramètres à envoyer au rapport
			Map parameters = new HashMap();

			parameters.put("num_recu", new Integer(res.getNum_recu()));
			parameters.put("mode_reglement", res.getMode_paiment());
			parameters.put("nbre_mois", res.calculmontant());
			parameters.put("montant", res.getNbre_moins());
			parameters.put("type_frais", res.getMode_reglement());
			parameters.put("remise", res.getMois());
			parameters.put("montant", res.getReste());
parameters.put("cin", res.getReste());
parameters.put("mois", res.getReste());
parameters.put("modde_paiment", res.getReste());
parameters.put("reste", res.getReste());
parameters.put("date", res.getReste());
			// - Execution du rapport
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, connection);
			// - Création du rapport au format PDF
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"C:\\Users\\Dhoha\\Documents\\Report1.pdf");
			JasperViewer.viewReport(jasperPrint, false); // L'affichage du
															// rapport en
															// utilisant
															// JRViewer
			System.out.println("success");

		}

		catch (JRException e) {
			System.out.println("erreur de compilation" + e.getMessage());
		}

	}

}
/**/
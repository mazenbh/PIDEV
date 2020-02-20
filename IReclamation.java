/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.Reclamation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IReclamation {
     public void insert(Reclamation r);
     public void update(Reclamation r);
     public void delete(Reclamation r);
     public ArrayList<Reclamation> selectAll();
     public ArrayList<Reclamation> selectCorbeille();
     public ArrayList<Reclamation> selectArchive();
     
     public void toCorbeille(Reclamation r);
     public void toArchive(Reclamation r);
     public void restaurer(Reclamation r);
    
}

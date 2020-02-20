/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import entities.ReclamationType;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IReclamationType {
    
    public void insert(ReclamationType r);
    public void update(ReclamationType r);
    public void delete(ReclamationType r);
    public List<ReclamationType> selectAll();
    
}

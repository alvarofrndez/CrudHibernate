/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import models.Familias;
import org.hibernate.Session;

/**
 *
 * @author alvaro
 */
public class FamiliasController {
    public List getFamilias(Session ss){
        return ss.createQuery("from Familias").list();
    }
    
    public String[] getColumnsName(){
        return new Familias().getColumnsName();
    }
    
    public Boolean checkValues(List<String> values){
        if(values.size() > 2){
            if(!(values.get(0) instanceof String))
                return false;
            if(!(values.get(1) instanceof String))
                return false;
            if(!(values.get(2) instanceof String))
                return false;
        }else{
            if(!(values.get(0) instanceof String))
                return false;
            if(!(values.get(1) instanceof String))
                return false;
        }
        return true;
    }
    
    public Familias addFamilia(List<String> values){
        String id = values.get(0);
        String nomFam = values.get(1);
        String desFam = values.get(2);
        Date fechaCreacion = new Date();
        
        return new Familias(id,nomFam,desFam,fechaCreacion, new HashSet<>());
    }
    
    public void updateFamilia(Familias familia, List<String> values){
        familia.setNomFam(values.get(0));
        familia.setDesFam(values.get(1));
    }
}

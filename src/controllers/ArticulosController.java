/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import models.Articulos;
import models.Familias;
import org.hibernate.Session;

/**
 *
 * @author alvaro
 */
public class ArticulosController {
    public List getArticulos(Session ss){
        return ss.createCriteria(Articulos.class).list();
    }
    
    public String[] getColumnsName(){
        return new Articulos().getColumnsName();
    }
    
    public Boolean checkValues(List<String> values){
        return true;
    }
    
    public Articulos addArticulo(List<String> values, Familias familia){
        String id = values.get(0);
        String nomArt = values.get(1);
        String desArt = values.get(2);
        BigDecimal stock = new BigDecimal(values.get(3));

        return new Articulos(id, familia, nomArt,desArt,stock, new HashSet<>());
    }
    
    public ArrayList getArticuloById(Session ss, String id){
        Articulos articulo = (Articulos) ss.get(Articulos.class, id);
        ArrayList result = new ArrayList<>(articulo.getFacturases());
        return result;
    }
    
    public void updateArticulo(Articulos articulo, List<String> values){
        articulo.setNomArt(values.get(0));
        articulo.setDesArt(values.get(1));
        articulo.setStock(new BigDecimal(values.get(2)));
    }
}

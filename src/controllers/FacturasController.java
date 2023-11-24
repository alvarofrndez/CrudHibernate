/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import models.Articulos;
import models.Clientes;
import models.Facturas;
import org.hibernate.Session;

/**
 *
 * @author alvaro
 */
public class FacturasController {
    public List getFacturas(Session ss){
        return ss.createQuery("from Facturas").list();
    }
    
    public String[] getColumnsName(){
        return new Facturas().getColumnsName();
    }
    
    public Boolean checkValues(List<String> values){
        if(values.size() > 2){
            if(!(values.get(0) instanceof String))
                return false;
            try{
                new BigDecimal(values.get(1));
            }catch (Exception e){
                return false;
            }
            if(!(values.get(2) instanceof String))
                return false;
        }else{
            try{
                new BigDecimal(values.get(0));
            }catch (Exception e){
                return false;
            }
            if(!(values.get(1) instanceof String))
                return false;
        }
        return true;
    }
    
    public Facturas addFactura(List<String> values, Clientes cliente, Articulos articulo){
        String id = values.get(0);
        Date fechaFac = new Date();
        BigDecimal totalFac = new BigDecimal(values.get(1));
        String metodoPago = values.get(2);
        
        Facturas factura = new Facturas(id, cliente, fechaFac,totalFac,metodoPago, new HashSet<>());
        factura.getArticuloses().add(articulo);
        
        return factura;
    }
    
    public ArrayList getFacturaById(Session ss, String id){
        Facturas factura = (Facturas) ss.get(Facturas.class, id);
        ArrayList result = new ArrayList<>(factura.getArticuloses());
        return result;
    }
    
    public void updateFactura(Facturas factura, List<String> values){
        factura.setTotalFac(new BigDecimal(values.get(0)));
        factura.setMetodoPago(values.get(1));
    }
}

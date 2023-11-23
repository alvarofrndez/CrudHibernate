/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.math.BigDecimal;
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
        return true;
    }
    
    public Facturas addFactura(List<String> values, Clientes cliente, Articulos articulo){
        String id = values.get(0);
        Date fechaFac = new Date();
        BigDecimal totalFac = new BigDecimal(values.get(2));
        String metodoPago = values.get(3);
        
        Facturas factura = new Facturas(id, cliente, fechaFac,totalFac,metodoPago, new HashSet<>());
        factura.getArticuloses().add(articulo);
        
        return factura;
    }
}

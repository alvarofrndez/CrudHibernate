/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import org.hibernate.Session;
import java.util.List;
import java.util.Set;
import models.Articulos;
import models.Clientes;
import models.Facturas;
import models.Familias;
import org.hibernate.Transaction;

/**
 *
 * @author alvaro
 */
public class SessionController {

    private Session ss;
    private Transaction ts;
    private Boolean is_connected;
    
    private final FamiliasController familias_ctrl = new FamiliasController();
    private final FacturasController facturas_ctrl = new FacturasController();
    private final ArticulosController articulos_ctrl = new ArticulosController();
    private final ClientesController clientes_ctrl = new ClientesController();

    
    public boolean haveConnection() {
        is_connected = false;
        try {
            openSession();
            is_connected = this.ss.isConnected();
        } catch (Exception e) {
        } finally {
            closeSession();
        }
        return is_connected;
    }

    public void closeSession() {
        this.ss.close();
    }
    
    public void openSession(){
        this.ss = NewHibernateUtil.getSessionFactory().openSession();
    }
    
    public List getTable(String table){
        if(!haveConnection()){
            return null;
        }else{
            List result = null;
            openSession();
            switch (table.toLowerCase()) {
                case "familias":
                    result = familias_ctrl.getFamilias(this.ss);
                    break;
                case "facturas":
                    result = facturas_ctrl.getFacturas(this.ss);
                    break;
                case "articulos":
                    result = articulos_ctrl.getArticulos(this.ss);
                    break;
                case "clientes":
                    result = clientes_ctrl.getClientes(this.ss);
                    break;
            }
            closeSession();
            return result;
        }
    }
    
    public Object[] getById(String id, String table){
        if(!haveConnection()){
            return null;
        }else{
            Object[] result = null;
            openSession();
            switch (table.toLowerCase()) {
                case "familias":
                    Familias familia = (Familias) ss.get(Familias.class, id);
                    result = familia.convertToObjectArray();
                    break;
                case "articulos":
                    Articulos articulo = (Articulos) ss.get(Articulos.class, id);
                    result = articulo.convertToObjectArray();
                    break;
                case "facturas":
                    Facturas factura = (Facturas) ss.get(Facturas.class, id);
                    result = factura.convertToObjectArray();
                    break;
                case "clientes":
                    Clientes cliente = (Clientes) ss.get(Clientes.class, id);
                    result = cliente.convertToObjectArray();
                    break;
            }
            closeSession();
            return result;
        }
    }
    
    public List getTableById(String id, String table){
        if(!haveConnection()){
            return null;
        }else{
            List result = null;
            openSession();
            switch (table.toLowerCase()) {
                case "familias":
                    Familias familia = (Familias) ss.get(Familias.class, id);
                    result = new ArrayList<>(familia.getArticuloses());
                    break;
                case "articulos":
                    Articulos articulo = (Articulos) ss.get(Articulos.class, id);
                    result = new ArrayList<>(articulo.getFacturases());
                    break;
                case "facturas":
                    Facturas factura = (Facturas) ss.get(Facturas.class, id);
                    result = new ArrayList<>(factura.getArticuloses());
                    break;
                case "clientes":
                    Clientes cliente = (Clientes) ss.get(Clientes.class, id);
                    result = new ArrayList<>(cliente.getFacturases());
                    break;
            }
            closeSession();
            return result;
        }
    }
    
    public List getOthers(String id, String table){
        if(!haveConnection()){
            return null;
        }else{
            List result = null;
            openSession();
            switch (table.toLowerCase()) {
                case "familias":
                    Familias familia = (Familias) ss.get(Familias.class, id);
                    List all_fam = articulos_ctrl.getArticulos(ss);
                    
                    all_fam.removeAll(familia.getArticuloses());
                    result = all_fam;
                    break;
                case "articulos":
                    Articulos articulo = (Articulos) ss.get(Articulos.class, id);
                    List all_art = facturas_ctrl.getFacturas(ss);
                    
                    all_art.removeAll(articulo.getFacturases());
                    result = all_art;
                    break;
                case "facturas":
                    Facturas factura = (Facturas) ss.get(Facturas.class, id);
                    List all_fac = articulos_ctrl.getArticulos(ss);
                    
                    all_fac.removeAll(factura.getArticuloses());
                    result = all_fac;
                    break;
                case "clientes":
                    Clientes cliente = (Clientes) ss.get(Clientes.class, id);
                    List all_cli = facturas_ctrl.getFacturas(ss);
                    
                    all_cli.removeAll(cliente.getFacturases());
                    result = all_cli;
                    break;
            }
            closeSession();
            return result;
        }
    }
    
    public String[] getcolumnsName(String table_selected){
        switch(table_selected.toLowerCase()){
            case "familias":
                return familias_ctrl.getColumnsName();
            case "articulos":
                return articulos_ctrl.getColumnsName();
            case "facturas":
                return facturas_ctrl.getColumnsName();
            case "clientes":
                return clientes_ctrl.getColumnsName();
            default:
                return null;
        }
    }
    
    public Boolean addRegister(List<String> values, String table_selected){
        if(haveConnection()){
            try{
                openSession();
                ts = ss.beginTransaction();
                
                switch(table_selected.toLowerCase()){
                    case "familias":
                        Familias familia = familias_ctrl.addFamilia(values);
                        ss.save(familia);
                        ts.commit();
                        break;
                    case "articulos":
                        Familias familia_foreing = (Familias) ss.get(Familias.class, values.get(4).split("-")[0].trim());
                        Articulos articulo = articulos_ctrl.addArticulo(values, familia_foreing);
                        ss.save(articulo);
                        ts.commit();
                        break;
                    case "facturas":
                        Clientes cliente_foreing = (Clientes) ss.get(Clientes.class, values.get(3).split("-")[0].trim());
                        Articulos articulo_foreing = (Articulos) ss.get(Articulos.class, values.get(4).split("-")[0].trim());
                        Facturas factura = facturas_ctrl.addFactura(values, cliente_foreing, articulo_foreing);
                        ss.save(factura);
                        ts.commit();
                        break;
                    case "clientes":
                        Clientes cliente = clientes_ctrl.addCliente(values);
                        ss.save(cliente);
                        ts.commit();
                        break;
                }
                return true;
            }catch (Exception e) {
                ts.rollback();
                return false;
            }finally{
                closeSession();
            }
        }
        return false;
    }
    
    public Boolean associateRegister(String id, String id_table, String table_selected){
        if(haveConnection()){
            try{
                openSession();
                ts = ss.beginTransaction();
                
                switch(table_selected.toLowerCase()){
                    case "familias":
                        Familias familia = (Familias) ss.get(Familias.class, id_table);
                        Articulos art_fam = (Articulos) ss.get(Articulos.class, id);
  
                        if(familia != null && art_fam != null && art_fam.getFamilias() == null){
                            art_fam.setFamilias(familia);
                            familia.getArticuloses().add(art_fam);
                        
                            ss.update(familia);
                            ss.update(art_fam);
                            ts.commit();
                            return true;
                        }
                        return false;
                    case "articulos":
                        Articulos articulo = (Articulos) ss.get(Articulos.class, id_table);
                        Facturas fac_art = (Facturas) ss.get(Facturas.class, id);
                        
                        if(articulo != null && fac_art != null){
                            articulo.getFacturases().add(fac_art);
                            fac_art.getArticuloses().add(articulo);
                            
                            ss.update(articulo);
                            ss.update(fac_art);
                            ts.commit();
                            return true;
                        }
                        return false;
                    case "facturas":
                        Facturas factura = (Facturas) ss.get(Facturas.class, id_table);
                        Articulos art_fac = (Articulos) ss.get(Articulos.class, id);
                        
                        if(factura != null && art_fac != null){
                            factura.getArticuloses().add(art_fac);
                            art_fac.getFacturases().add(factura);
                            
                            ss.update(factura);
                            ss.update(art_fac);
                            ts.commit();
                            return true;
                        }
                        return false;
                    case "clientes":
                        Clientes cliente = (Clientes) ss.get(Clientes.class, id_table);
                        Facturas fac_cli = (Facturas) ss.get(Facturas.class, id);
                        
                        if(cliente != null && fac_cli != null && fac_cli.getClientes() == null){
                            cliente.getFacturases().add(fac_cli);
                            fac_cli.setClientes(cliente);
                            
                            ss.update(cliente);
                            ss.update(fac_cli);
                            ts.commit();
                            return true;
                        }
                        return false;
                }
            }catch (Exception e) {
                if(ts != null)
                    ts.rollback();
                return false;
            }finally{
                closeSession();
            }
        }
        return false; 
    }
    
    public void updateRegister(List<String> values, String table_selected, String id){
        if(haveConnection()){
            try{
                openSession();
                ts = ss.beginTransaction();
                
                switch(table_selected.toLowerCase()){
                    case "familias":
                        Familias familia = (Familias) ss.get(Familias.class, id);
                        familias_ctrl.updateFamilia(familia, values);
                        ss.update(familia);
                        ts.commit();
                        break;
                    case "articulos":
                        Articulos articulo = (Articulos) ss.get(Articulos.class, id);
                        articulos_ctrl.updateArticulo(articulo, values);
                        ss.save(articulo);
                        ts.commit();
                        break;
                    case "facturas":
                        Facturas factura = (Facturas) ss.get(Facturas.class, id);
                        facturas_ctrl.updateFactura(factura, values);
                        ss.save(factura);
                        ts.commit();
                        break;
                    case "clientes":
                        Clientes cliente = (Clientes) ss.get(Clientes.class, id);
                        clientes_ctrl.updateCliente(cliente, values);
                        ss.save(cliente);
                        ts.commit();
                        break;
                }
            }catch (Exception e) {
                ts.rollback();
            }finally{
                closeSession();
            }
        }
    }
    
    public void deleteRegister(String id, String table_selected){
        if(haveConnection()){
            try{
                openSession();
                ts = ss.beginTransaction();
                
                switch(table_selected.toLowerCase()){
                    case "familias":
                        Familias familia = (Familias) ss.get(Familias.class, id);
                        for(Object art : familia.getArticuloses()){
                            Set<Facturas> facturas = ((Articulos) art).getFacturases();
                            for(Facturas fac : facturas) {
                                fac.getArticuloses().remove(art);
                            }

                            if(art != null){
                                ss.delete(art);
                                ts.commit();
                                ts = ss.beginTransaction();
                            }
                        }
                        
                        if(familia != null){
                            ss.delete(familia);
                            ts.commit();
                        }
                        break;
                    case "articulos":
                        Articulos articulo = (Articulos) ss.get(Articulos.class, id);
                        Set<Facturas> facturas = articulo.getFacturases();
                        for(Facturas fac : facturas) {
                            fac.getArticuloses().remove(articulo);
                        }

                        if(articulo != null){
                            ss.delete(articulo);
                            ts.commit();
                        }
                        break;
                    case "facturas":
                        Facturas factura = (Facturas) ss.get(Facturas.class, id);
                        Set<Articulos> articulos = factura.getArticuloses();
                        for(Articulos art : articulos) {
                            art.getFacturases().remove(factura);
                        }

                        if(factura != null){
                            ss.delete(factura);
                            ts.commit();
                        }
                        break;
                    case "clientes":
                        Clientes cliente = (Clientes) ss.get(Clientes.class, id);
                        
                        if(cliente != null){
                            ss.delete(cliente);
                            ts.commit();
                        }
                        break;
                }
            }catch (Exception e) {
                if(ts != null)
                    ts.rollback();
            }finally{
                closeSession();
            }
        }
    }
    
    public void disassociateRegister(String id, String id_table, String table_selected){
        if(haveConnection()){
            try{
                openSession();
                ts = ss.beginTransaction();
                
                switch(table_selected.toLowerCase()){
                    case "familias":
                        Familias familia = (Familias) ss.get(Familias.class, id_table);
                        Articulos art_fam = (Articulos) ss.get(Articulos.class, id);
  
                        if(familia != null && art_fam != null){
                            art_fam.setFamilias(null);
                            familia.getArticuloses().remove(art_fam);
                        
                            ss.update(familia);
                            ss.update(art_fam);
                            ts.commit();
                        }
                        break;
                    case "articulos":
                        Articulos articulo = (Articulos) ss.get(Articulos.class, id_table);
                        Facturas fac_art = (Facturas) ss.get(Facturas.class, id);
                        
                        if(articulo != null && fac_art != null){
                            articulo.getFacturases().remove(fac_art);
                            fac_art.getArticuloses().remove(articulo);
                            
                            ss.update(articulo);
                            ss.update(fac_art);
                            ts.commit();
                        }
                        break;
                    case "facturas":
                        Facturas factura = (Facturas) ss.get(Facturas.class, id_table);
                        Articulos art_fac = (Articulos) ss.get(Articulos.class, id);
                        
                        if(factura != null && art_fac != null){
                            factura.getArticuloses().remove(art_fac);
                            art_fac.getFacturases().remove(factura);
                            
                            ss.update(factura);
                            ss.update(art_fac);
                            ts.commit();
                        }
                        break;
                    case "clientes":
                        Clientes cliente = (Clientes) ss.get(Clientes.class, id_table);
                        Facturas fac_cli = (Facturas) ss.get(Facturas.class, id);
                        
                        if(cliente != null && fac_cli != null){
                            cliente.getFacturases().remove(fac_cli);
                            fac_cli.setClientes(null);
                            
                            ss.update(cliente);
                            ss.update(fac_cli);
                            ts.commit();
                        }
                        break;
                }
            }catch (Exception e) {
                if(ts != null)
                    ts.rollback();
            }finally{
                closeSession();
            }
        }
    }
    
    public FamiliasController getFamilias_ctrl() {
        return familias_ctrl;
    }

    public FacturasController getFacturas_ctrl() {
        return facturas_ctrl;
    }

    public ArticulosController getArticulos_ctrl() {
        return articulos_ctrl;
    }

    public ClientesController getClientes_ctrl() {
        return clientes_ctrl;
    }
}

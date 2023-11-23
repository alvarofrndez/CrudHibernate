/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.ArrayList;
import org.hibernate.Session;
import java.util.List;
import models.Articulos;
import models.Clientes;
import models.Facturas;
import models.Familias;
import org.hibernate.Query;
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
    
    public List getTableById(String id, String table){
        if(!haveConnection()){
            return null;
        }else{
            List result = null;
            openSession();
            switch (table.toLowerCase()) {
                case "facturas":
                    Articulos articulo = (Articulos) ss.get(Articulos.class, id);
                    result = new ArrayList<>(articulo.getFacturases());
                    break;
                case "articulos":
                    Facturas factura = (Facturas) ss.get(Facturas.class, id);
                    result = new ArrayList<>(factura.getArticuloses());
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
    
    public void addRegister(List<String> values, String table_selected){
        if(haveConnection()){
            try{
                openSession();
                ts = ss.beginTransaction();
                
                switch(table_selected.toLowerCase()){
                    case "familias":
                        Familias familia = familias_ctrl.addFamilia(values);
                        ss.save(familia);
                        ts.commit();
                    case "articulos":
                        Familias familia_foreing = (Familias) ss.get(Familias.class, values.get(4).split("-")[0].trim());
                        Articulos articulo = articulos_ctrl.addArticulo(values, familia_foreing);
                        ss.save(articulo);
                        ts.commit();
                        break;
                    case "facturas":
                        Clientes cliente_foreing = (Clientes) ss.get(Clientes.class, values.get(4).split("-")[0].trim());
                        Articulos articulo_foreing = (Articulos) ss.get(Articulos.class, values.get(5).split("-")[0].trim());
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
            }catch (Exception e) {
                e.printStackTrace();
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
                        
                        if(familia != null){
                            ss.delete(familia);
                            ts.commit();
                        }
                    case "articulos":
                        Articulos articulo = (Articulos) ss.get(Articulos.class, id);
                        
                        if(articulo != null){
                            ss.delete(articulo);
                            ts.commit();
                        }
                        break;
                    case "facturas":
                        Facturas factura = (Facturas) ss.get(Facturas.class, id);
                        
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
                System.out.println(e.getMessage());
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
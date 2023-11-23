/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.HashSet;
import java.util.List;
import models.Clientes;
import org.hibernate.Session;

/**
 *
 * @author alvaro
 */
public class ClientesController {
    public List getClientes(Session ss){
        return ss.createCriteria(Clientes.class).list();
    }
    
    public String[] getColumnsName(){
        return new Clientes().getColumnsName();
    }
    
    public Boolean checkValues(List<String> values){
        return true;
    }
    
    public Clientes addCliente(List<String> values){
        String id = values.get(0);
        String nomCli = values.get(1);
        String correoCli = values.get(2);
        String telCli = values.get(3);
        String direcCli = values.get(4);
        
        return new Clientes(id,nomCli,correoCli,telCli,direcCli, new HashSet<>());
    }
    
    public void updateCliente(Clientes cliente, List<String> values){
        cliente.setNomCli(values.get(0));
        cliente.setCorreoCli(values.get(1));
        cliente.setTelCli(values.get(2));
        cliente.setDirecCli(values.get(3));
    }
}

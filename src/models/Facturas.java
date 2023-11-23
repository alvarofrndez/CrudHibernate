package models;
// Generated Nov 22, 2023 1:40:19 PM by Hibernate Tools 4.3.1


import interfaces.Identificable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Facturas generated by hbm2java
 */
public class Facturas  implements java.io.Serializable, Identificable {

    private String idFac;
    private Clientes clientes;
    private Date fechaFac;
    private BigDecimal totalFac;
    private String metodoPago;
    private Set articuloses = new HashSet(0);
    private String[] columns_name = {"id", "fecha", "total", "metodo de pago", "clientes", "articulos"};

    public Facturas() {
    }

	
    public Facturas(String idFac, Date fechaFac) {
        this.idFac = idFac;
        this.fechaFac = fechaFac;
    }
    public Facturas(String idFac, Clientes clientes, Date fechaFac, BigDecimal totalFac, String metodoPago, Set articuloses) {
       this.idFac = idFac;
       this.clientes = clientes;
       this.fechaFac = fechaFac;
       this.totalFac = totalFac;
       this.metodoPago = metodoPago;
       this.articuloses = articuloses;
    }
   
    public String getIdFac() {
        return this.idFac;
    }
    
    public void setIdFac(String idFac) {
        this.idFac = idFac;
    }
    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }
    public Date getFechaFac() {
        return this.fechaFac;
    }
    
    public void setFechaFac(Date fechaFac) {
        this.fechaFac = fechaFac;
    }
    public BigDecimal getTotalFac() {
        return this.totalFac;
    }
    
    public void setTotalFac(BigDecimal totalFac) {
        this.totalFac = totalFac;
    }
    public String getMetodoPago() {
        return this.metodoPago;
    }
    
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    public Set getArticuloses() {
        return this.articuloses;
    }
    
    public void setArticuloses(Set articuloses) {
        this.articuloses = articuloses;
    }

    public String[] getColumnsName(){
        return this.columns_name;
    }
    
    public Object[] convertToObjectArray(){
        Object[] object_data = {idFac, fechaFac, totalFac, metodoPago, clientes.getIdentificator(), ""};
        return object_data;
    }
    
    @Override
    public String getIdentificator() {
        return this.idFac + " - " + this.metodoPago;
    }
}



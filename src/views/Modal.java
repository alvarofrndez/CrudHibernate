/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import interfaces.Identificable;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author alvaro
 */
public class Modal extends javax.swing.JPanel {
    private List<JTextField> list_fields;
    List<JComboBox<String>> dropdowns = new ArrayList<>();
    
    /**
     * Creates new form Modal
     * @param fields
     * @param foreings
     */
    public Modal(List<String> fields) {
        initComponents();
        start(fields);
    }
    
    public Modal(List<String> fields, List<List<Object>> foreings) {
        initComponents();
        startForeings(fields, foreings);
    }
    
    public void start(List<String> fields){
        list_fields = new ArrayList<>();
        setLayout(new GridLayout(0, 2));

        for(String field : fields){
            addField(field);
        }
    }
    
    public void startForeings(List<String> fields, List<List<Object>> foreings){
        list_fields = new ArrayList<>();
        setLayout(new GridLayout(0, 2));

        for(String field : fields){
            addField(field);
        }
        
        for(List<Object> foreing : foreings){
            add(new JLabel("Seleccione:"));
            ArrayList<String> options_array = new ArrayList<>();
            for (Object register : foreing){
                if (register instanceof Identificable) {
                    options_array.add(((Identificable) register).getIdentificator());
                }
            }
            String[] options = options_array.toArray(new String[0]);
            JComboBox<String> dropdown = new JComboBox<>(options);
            dropdowns.add(dropdown);
            add(dropdown);
        }
    }
    
    public void addField(String field){
        if(!field.equals("familias") && !field.equals("clientes") && !field.equals("lineas") && !field.equals("fecha")){
            add(new JLabel(field));
            JTextField text_field = new JTextField();
            add(text_field);
            list_fields.add(text_field);
        }
    }
    
    public List<String> getValues(Boolean type) {
        List<String> values = new ArrayList<>();

        for (JTextField textField : list_fields) {
            values.add(textField.getText());
        }

        if(type)
            for (JComboBox<String> currentDropdown : dropdowns) {
                values.add((String) currentDropdown.getSelectedItem());
            }
        return values;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

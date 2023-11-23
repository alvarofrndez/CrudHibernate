/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import controllers.SessionController;
import interfaces.Identificable;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import models.Articulos;
import models.Clientes;
import models.Facturas;
import models.Familias;

/**
 *
 * @author alvaro
 */
public class Window extends javax.swing.JFrame {
    private final SessionController session_ctrl = new SessionController();
    private String table_selected; 
    private List registers_list;
    private String[] columns_name;
    private int num_windows;
    
    /**
     * Creates new form Window
     * @param table_selected
     * @param num_windows
     */
    public Window(String table_selected, int num_windows) {
        initComponents();
        this.table_selected = table_selected;
        this.num_windows = num_windows + 1;
        this.setVisible(true);
        startProgram();
    }
    
    public void startProgram(){
        // TODO: refactorizar para poner global la varibale que lleva el numero de ventanas
        setEventWindow();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        refreshTableTrue();
    }
    
    public void setEventWindow(){
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeWindow();
            }
        });
    }
    
    public void closeWindow(){
        num_windows--;
        if (num_windows == 0) {
            System.exit(0);
        }
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        button_families = new javax.swing.JButton();
        button_invoices = new javax.swing.JButton();
        button_articles = new javax.swing.JButton();
        button_clients = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_crud = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        title_table = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_families.setText("Familias");
        button_families.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_familiesActionPerformed(evt);
            }
        });

        button_invoices.setText("Facturas");
        button_invoices.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_invoicesActionPerformed(evt);
            }
        });

        button_articles.setText("Articulos");
        button_articles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_articlesActionPerformed(evt);
            }
        });

        button_clients.setText("Clientes");
        button_clients.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_clientsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(121, Short.MAX_VALUE)
                .addComponent(button_invoices)
                .addGap(32, 32, 32)
                .addComponent(button_articles)
                .addGap(32, 32, 32)
                .addComponent(button_clients)
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(22, 22, 22)
                    .addComponent(button_families)
                    .addContainerGap(333, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_clients, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(button_articles, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(button_invoices, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(button_families, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        table_crud.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_crud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_crudMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table_crud);

        title_table.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        title_table.setText("jLabel1");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(title_table)
                        .addGap(153, 153, 153))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(193, 193, 193))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_table)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(53, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(249, 249, 249)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 792, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_familiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_familiesActionPerformed
        if(table_selected.equals("familias")){
            new DialogMessages("Cambio de tabla", "Esta tabla ya está seleccionada", 1).showMessage();
        }else{
            String option = new DialogMessages("Cambio de tabla", "¿Desea abrirla en una nueva ventana?", 1).showMessageSelection();
            if(option.equals("other")){
                new Window("familias", num_windows);
            } else if(option.equals("this")){
                table_selected = "familias";
                refreshTableTrue();
            }
        }
    }//GEN-LAST:event_button_familiesActionPerformed

    private void button_invoicesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_invoicesActionPerformed
        if(table_selected.equals("facturas")){
            new DialogMessages("Cambio de tabla", "Esta tabla ya está seleccionada", 1).showMessage();
        }else{
            String option = new DialogMessages("Cambio de tabla", "¿Desea abrirla en una nueva ventana?", 1).showMessageSelection();
            if(option.equals("other")){
                new Window("facturas", num_windows);
            } else if(option.equals("this")){
                table_selected = "facturas";
                refreshTableTrue();
            }
        }
    }//GEN-LAST:event_button_invoicesActionPerformed

    private void button_articlesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_articlesActionPerformed
        if(table_selected.equals("articulos")){
            new DialogMessages("Cambio de tabla", "Esta tabla ya está seleccionada", 1).showMessage();
        }else{
            String option = new DialogMessages("Cambio de tabla", "¿Desea abrirla en una nueva ventana?", 1).showMessageSelection();
            if(option.equals("other")){
                new Window("articulos", num_windows);
            } else if(option.equals("this")){
                table_selected = "articulos";
                refreshTableTrue();
            }
        }
    }//GEN-LAST:event_button_articlesActionPerformed

    private void button_clientsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_clientsActionPerformed
        if(table_selected.equals("clientes")){
            new DialogMessages("Cambio de tabla", "Esta tabla ya está seleccionada", 1).showMessage();
        }else{
            String option = new DialogMessages("Cambio de tabla", "¿Desea abrirla en una nueva ventana?", 1).showMessageSelection();
            if(option.equals("other")){
                new Window("clientes", num_windows);
            } else if(option.equals("this")){
                table_selected = "clientes";
                refreshTableTrue();
            }
        }
    }//GEN-LAST:event_button_clientsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        addRegister();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void table_crudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_crudMouseClicked
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = table_crud.rowAtPoint(evt.getPoint());

            showPopUp(evt.getComponent(), evt.getX(), evt.getY(), row, (String) table_crud.getValueAt(row, 0));
        }
    }//GEN-LAST:event_table_crudMouseClicked

    public void showPopUp(Component component, int x, int y, int selectedRow, String id){
        JPopupMenu popup_menu = new JPopupMenu();

        JMenuItem update = new JMenuItem("Editar");
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(component, "Editar fila " + id);
            }
        });

        JMenuItem delete = new JMenuItem("Borrar");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = new DialogMessages("Eliminar registro", "¿Desea borrar el registro " + id + " ?", 2).showMessageConfirm();
                
                if(result == JOptionPane.OK_OPTION){
                    session_ctrl.deleteRegister(id, table_selected);
                    refreshTableFalse();
                }
            }
        });
        
        if(table_selected.equals("articulos")){
            JMenuItem view = new JMenuItem("Ver facturas");
            view.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new TableForeings(id, "facturas");
                    JOptionPane.showMessageDialog(null, panel, "", JOptionPane.OK_OPTION);
                }
            });
            
            popup_menu.add(view);
        }else if(table_selected.equals("facturas")){
            JMenuItem view = new JMenuItem("Ver articulos");
            view.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new TableForeings(id, "articulos");
                    JOptionPane.showMessageDialog(null, panel, "", JOptionPane.OK_OPTION);
                }
            });
            
            popup_menu.add(view);
        }

        popup_menu.add(update);
        popup_menu.add(delete);

        popup_menu.show(component, x, y);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window("", 0).setVisible(true);
            }
        });
    }
    
    public void chooseInitial(){
        String[] options = {"Familias", "Articulos", "Facturas", "Clientes"};
        table_selected = new DialogMessages("Seleccionar tabla","Escoja con que tabla iniciar: ", options).showMessageOption();
        
        if(table_selected == null){
            new DialogMessages("Error tabla", "Error al cargar la tabla", 0).showMessage();
            System.exit(0);
            return;
        }
        
        initComponents();
        
        refreshTableTrue();
    }
    
    public void updateTable(Boolean modify_columns) {
        DefaultTableModel model = (DefaultTableModel) table_crud.getModel();
        
        if(modify_columns){
            model.setColumnCount(0);
            
            for(String column : columns_name){
                model.addColumn(column);
            }
        }
        
        model.setRowCount(0);

        for(Object register : registers_list){
            if(register instanceof Familias){
                model.addRow(((Familias) register).convertToObjectArray());
            }else if(register instanceof Facturas){
                Object[] rowData = ((Facturas) register).convertToObjectArray();
                rowData[rowData.length - 1] = createDropdown(((Facturas) register).getArticuloses());
                model.addRow(rowData);
            }else if(register instanceof Clientes){
                model.addRow(((Clientes) register).convertToObjectArray());
            }else if(register instanceof Articulos){
                model.addRow(((Articulos) register).convertToObjectArray());
            }
        }
        
        
        table_crud.getColumnModel().getColumn(model.getColumnCount() - 1).setCellEditor(new DefaultCellEditor(createDropdown(new HashSet<>())));
        table_crud.getColumnModel().getColumn(model.getColumnCount() - 1).setCellRenderer(new DropdownCellRenderer());
    }
    
    private JComboBox<String> createDropdown(Set values) {
        ArrayList<String> values_list = new ArrayList<>();
        
        for (Object register : values){
            if (register instanceof Identificable) {
                values_list.add(((Identificable) register).getIdentificator());
            }
        }
        
        return new JComboBox<>(values_list.toArray(new String[0]));
    }
    
    private static class DropdownCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof JComboBox) {
                return (JComboBox<?>) value;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }
    
    public void refreshTableTrue(){
        registers_list = session_ctrl.getTable(table_selected);
        columns_name = getColumnsName();
        updateTable(true);
        title_table.setText(table_selected);
    }
    
    public void refreshTableFalse(){
        registers_list = session_ctrl.getTable(table_selected);
        updateTable(false);
    }
    
    public String[] getColumnsName(){
        return session_ctrl.getcolumnsName(table_selected);
    }

    public void addRegister(){
        Modal inputPanel = null;
        
        if(table_selected.equals("articulos")){
            List<List<Object>> list = new ArrayList<>();
            list.add(session_ctrl.getTable("familias"));
            list.add(session_ctrl.getTable("facturas"));
            inputPanel = new Modal(Arrays.asList(columns_name), list);
        }else if(table_selected.equals("facturas")){
            List<List<Object>> list = new ArrayList<>();
            list.add(session_ctrl.getTable("clientes"));
            list.add(session_ctrl.getTable("articulos"));
            inputPanel = new Modal(Arrays.asList(columns_name), list);
        }
        else{
            inputPanel = new Modal(Arrays.asList(columns_name));
        }

        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Introduzca los valores",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            List<String> values = inputPanel.getValues(false);
            if(table_selected.equals("familias")){
                if(session_ctrl.getFamilias_ctrl().checkValues(values))
                    session_ctrl.addRegister(values, table_selected);
            }else if(table_selected.equals("facturas")){
                if(session_ctrl.getFacturas_ctrl().checkValues(values))
                    session_ctrl.addRegister(inputPanel.getValues(true), table_selected);
            }else if(table_selected.equals("clientes")){
                if(session_ctrl.getClientes_ctrl().checkValues(values))
                    session_ctrl.addRegister(values, table_selected);
            }else if(table_selected.equals("articulos")){
                if(session_ctrl.getArticulos_ctrl().checkValues(values))
                    session_ctrl.addRegister(inputPanel.getValues(true), table_selected);
            }
            
            refreshTableFalse();
        }

        JOptionPane.getRootFrame().dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_articles;
    private javax.swing.JButton button_clients;
    private javax.swing.JButton button_families;
    private javax.swing.JButton button_invoices;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_crud;
    private javax.swing.JLabel title_table;
    // End of variables declaration//GEN-END:variables
}
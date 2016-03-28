/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladores.MaterialesController;
import Pojo.Materiales;
import static Validaciones.Metodos.limpiarTabla;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author donaldo
 */
public class Buscar_Material extends javax.swing.JInternalFrame {

    Interfaz_GestionMateriales Gestor;

    public Buscar_Material(Interfaz_GestionMateriales Gestor) {
        initComponents();
        this.Gestor = Gestor;
        Cargar();
    }

    public void Cargar() {
        for (Materiales m : new MaterialesController().getAll()) {
            ((DefaultTableModel) this.jTable1.getModel()).addRow(new Object[]{m.getId(), m.getNombre_material()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Buscar material");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Material", "Nombre Material"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Buscar");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setText("Seleccionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int fila = this.jTable1.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showInternalMessageDialog(this, "Debe seleccionar una fila primero", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(this.ContieneMaterial(((DefaultTableModel) Gestor.DependenciasTable.getModel()), Integer.parseInt(jTable1.getValueAt(fila, 0).toString()))){
            JOptionPane.showInternalMessageDialog(this, "Ese material ya ha sido agregado" , "Error" , JOptionPane.ERROR_MESSAGE);
            return;
        }
        Object[] o = new Object[]{this.jTable1.getValueAt(fila, 0), this.jTable1.getValueAt(fila, 1)};
        ((DefaultTableModel) Gestor.DependenciasTable.getModel()).addRow(o);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped

    }//GEN-LAST:event_jTextField1KeyTyped

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String texto = this.jTextField1.getText();
        limpiarTabla((DefaultTableModel)this.jTable1.getModel());
        if (texto.trim().isEmpty()) {
            this.Cargar();
            return;
        }
        for (Materiales m : new MaterialesController().getAll()) {
            if (m.getNombre_material().startsWith(texto) || m.getNombre_material().contains(texto)
                    || m.getNombre_material().endsWith(texto) || m.getNombre_material().equalsIgnoreCase(texto)) {
                ((DefaultTableModel) this.jTable1.getModel()).addRow(new Object[]{m.getId(), m.getNombre_material()});

            }
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    
    
    public boolean ContieneMaterial(DefaultTableModel dtm , int id){
        for(int  i = 0; i <dtm.getRowCount(); i++){
            if(Integer.parseInt(dtm.getValueAt(i, 0).toString()) == id){
                return true;
            }
        }
        
        
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}

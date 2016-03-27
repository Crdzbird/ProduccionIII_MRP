/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Controladores.MaterialesController;
import Implementaciones.TextInBox;
import Implementaciones.TextInBoxNode;
import Implementaciones.TextInBoxTreePane;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;

/**
 *
 * @author donaldo
 */
public class Arbol_dependencia extends javax.swing.JInternalFrame {

    /**
     * Creates new form Arbol_dependencia
     */
    public Arbol_dependencia(int id) {
        initComponents();
        this.setLayout(new FlowLayout());
        Cargar(id);
        
    }
    
    public void Cargar(int id) {
        MaterialesController m = new MaterialesController(); 
        
        TextInBox root = new TextInBox(m.getById(id).getNombre_material(), 60, 60);
        DefaultTreeForTreeLayout<TextInBox> tree
                = new DefaultTreeForTreeLayout<TextInBox>(root);
        
        AgregarDependencia(tree, root , id);

        double gapBetweenLevels = 50;
        double gapBetweenNodes = 10;
        DefaultConfiguration<TextInBox> configuration = new DefaultConfiguration<TextInBox>(
                gapBetweenLevels, gapBetweenNodes);

        TextInBoxNode nodeExtentProvider = new TextInBoxNode();

        TextInBoxTreePane pane;

        TreeLayout treeLayout;
        treeLayout = new TreeLayout(tree, nodeExtentProvider, configuration);
        pane = new TextInBoxTreePane(treeLayout);

        this.add(pane);
        this.pack();
    }
    
    public void AgregarDependencia(DefaultTreeForTreeLayout<TextInBox> tree, TextInBox root, int idPadre){
        MaterialesController m = new MaterialesController(); 
        for(Object [] obj : m.getDependencias(idPadre)){
            TextInBox n = new TextInBox(obj[1].toString(), 60, 60);
            tree.addChild(root, n);
            AgregarDependencia(tree, n, Integer.parseInt(obj[3].toString()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setClosable(true);
        setResizable(true);
        setTitle("Arbol de dependencia");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

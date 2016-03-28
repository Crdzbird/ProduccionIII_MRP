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
import org.abego.treelayout.TreeLayout;
import org.abego.treelayout.util.DefaultConfiguration;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;

/**
 *
 * @author donaldo
 */
public class prueba_arbol extends javax.swing.JFrame {

    /**
     * Creates new form prueba_arbol
     */
    public prueba_arbol(int id) {
        initComponents();
        this.setLayout(new BorderLayout());
        Cargar(2);
    }

    public void Cargar(int id) {
        MaterialesController m = new MaterialesController(); 
        
        TextInBox root = new TextInBox(m.getById(id).getNombre_material(), 60, 60);
        DefaultTreeForTreeLayout<TextInBox> tree
                = new DefaultTreeForTreeLayout<TextInBox>(root);
        
        AgregarDependencia(tree, root , id);
//        TextInBox root = new TextInBox("root", 40, 20);
//        TextInBox n1 = new TextInBox("n1", 30, 20);
//        TextInBox n1_1 = new TextInBox("n1.1\n(first node)", 80, 36);
//        TextInBox n1_2 = new TextInBox("n1.2", 40, 20);
//        TextInBox n1_3 = new TextInBox("n1.3\n(last node)", 80, 36);
//        TextInBox n2 = new TextInBox("n2", 30, 20);
//        TextInBox n2_1 = new TextInBox("n2", 30, 20);
//        DefaultTreeForTreeLayout<TextInBox> tree
//                = new DefaultTreeForTreeLayout<TextInBox>(root);
//        tree.addChild(root, n1);
//        tree.addChild(n1, n1_1);
//        tree.addChild(n1, n1_2);
//        tree.addChild(n1, n1_3);
//        tree.addChild(root, n2);
//        tree.addChild(n2, n2_1);

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

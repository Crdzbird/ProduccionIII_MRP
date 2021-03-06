/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Pojo.Usuario_Conectado;
import Validaciones.ImagenFondoDesktop;
import com.javaswingcomponents.accordion.JSCAccordion;
import com.javaswingcomponents.accordion.TabOrientation;
import static Implementaciones.Metodos.Centrar;
import static Implementaciones.Metodos.Maximizar;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import org.jb2011.lnf.beautyeye.BeautyEyeLookAndFeelCross;

/**
 *
 * @author crdzbird
 */
public class InterfazPrincipal extends javax.swing.JFrame {

    private int opcion;
    GraphicsDevice grafica = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    JSCAccordion accordion = new JSCAccordion();
    JPanel panelMateriales = new JPanel();
    JPanel panelProveedores = new JPanel();
    JPanel panelInformacionGeneral = new JPanel();
    JButton btnGestionarMateriales = new JButton();
    JButton btnArbolMaterial = new JButton();
    JButton btnVisualizarMateriales_Composicion = new JButton();
    JButton btnGestionarProveedores = new JButton();
    JButton btnVisualizarProveedores = new JButton();
    JButton btnInformacionGeneral = new JButton();
    JButton btnSalir = new JButton();

    private boolean internalActivo(Object object) {
        JInternalFrame[] activos = this.Escritorio.getAllFrames();
        boolean mostrar = true;
        for (int a = 0; a < Escritorio.getComponentCount(); a++) {
            if (object.getClass().isInstance(Escritorio.getComponent(a))) {
                mostrar = false;
            }
        }
        return mostrar;
    }

    public final void RellenarPaneles() {
        panelMateriales.setLayout(new GridLayout(4, 1));
        panelProveedores.setLayout(new GridLayout(3, 1));
        panelInformacionGeneral.setLayout(new GridLayout(2, 1));

        btnGestionarMateriales.setText("Administrar Materiales");
        btnArbolMaterial.setText("Orden de produccion");
        btnVisualizarMateriales_Composicion.setText("Visualizar Composicion Mat.");
        btnGestionarProveedores.setText("Administrar Proveedores");
        btnVisualizarProveedores.setText("Visualizar Proveedores");
        btnInformacionGeneral.setText("Datos de Desarrolladores");

        btnGestionarMateriales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Interfaz_GestionMateriales a = new Interfaz_GestionMateriales();
                if (internalActivo(a)) {
                    Centrar(a, Escritorio);
                    try {
                        Maximizar(a);
                    } catch (PropertyVetoException ex) {
                        Logger.getLogger(InterfazPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        btnArbolMaterial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrarOrdenProduccion r = new RegistrarOrdenProduccion();
                
                Centrar(r, Escritorio);
            }

        });

        panelMateriales.add(btnGestionarMateriales);
        panelMateriales.add(btnArbolMaterial);
        panelMateriales.add(btnVisualizarMateriales_Composicion);

        panelProveedores.add(btnGestionarProveedores);
        panelProveedores.add(btnVisualizarProveedores);

        panelInformacionGeneral.add(btnInformacionGeneral);
    }

    public InterfazPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(MAXIMIZED_BOTH);
        this.Usuario.setText(Usuario_Conectado.getUser());
        RellenarPaneles();
        this.jPanel3.setLayout(new BorderLayout());
        this.jPanel3.add(MenuAcordion(), BorderLayout.CENTER);
        this.jPanel3.add(AgregarBotonSalir(), BorderLayout.PAGE_END);
        this.Escritorio.setBorder(new ImagenFondoDesktop());
        this.add(BarraEstado);
    }

    private Component MenuAcordion() {
        accordion.setTabOrientation(TabOrientation.VERTICAL);
        agregarTabsProduccion(accordion);
        add(accordion);
        return accordion;
    }

    private void agregarTabsProduccion(JSCAccordion accordion) {
        panelMateriales.setOpaque(true);
        panelMateriales.setBackground(Color.CYAN);
        panelProveedores.setOpaque(true);
        panelProveedores.setBackground(Color.CYAN);
        panelInformacionGeneral.setOpaque(true);
        panelInformacionGeneral.setBackground(Color.CYAN);

        accordion.addTab("Administracion de Materiales", panelMateriales);
        accordion.addTab("Administracion de Proveedores", panelProveedores);
        accordion.addTab("Informacion del Sistema", panelInformacionGeneral);
    }

    public void CerrarMDI() {
        System.exit(0);
    }

    private Component AgregarBotonSalir() {
        btnSalir.setText("Salir del Sistema");
        btnSalir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                opcion = JOptionPane.showConfirmDialog(null, "Esta seguro que desea salir", "Advertencia", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
                if (opcion == 0) {
                    CerrarMDI();
                }
            }
        });
        return btnSalir;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BarraEstado = new javax.swing.JPanel();
        Usuario = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Escritorio = new javax.swing.JDesktopPane();

        BarraEstado.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        Usuario.setText("Usuario");

        Fecha.setText("Fecha");

        javax.swing.GroupLayout BarraEstadoLayout = new javax.swing.GroupLayout(BarraEstado);
        BarraEstado.setLayout(BarraEstadoLayout);
        BarraEstadoLayout.setHorizontalGroup(
            BarraEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraEstadoLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(Usuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 454, Short.MAX_VALUE)
                .addComponent(Fecha)
                .addGap(33, 33, 33))
        );
        BarraEstadoLayout.setVerticalGroup(
            BarraEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BarraEstadoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(BarraEstadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Usuario)
                    .addComponent(Fecha))
                .addGap(5, 5, 5))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 510, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, java.awt.BorderLayout.LINE_START);

        javax.swing.GroupLayout EscritorioLayout = new javax.swing.GroupLayout(Escritorio);
        Escritorio.setLayout(EscritorioLayout);
        EscritorioLayout.setHorizontalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 780, Short.MAX_VALUE)
        );
        EscritorioLayout.setVerticalGroup(
            EscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 514, Short.MAX_VALUE)
        );

        jPanel1.add(Escritorio, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        new Hora().start();
    }//GEN-LAST:event_formWindowOpened

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
            UIManager.setLookAndFeel(new BeautyEyeLookAndFeelCross());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(InterfazPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterfazPrincipal().setVisible(true);
            }
        });
    }

    class Hora extends Thread {

        public void run() {
            while (true) {
                Date Hoy = new java.util.Date();
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a  EEEEEE, dd 'de' MMMM 'de' yyyy");
                Fecha.setText(sdf.format(Hoy));
                esperar();
            }
        }

        public void esperar() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
            }
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BarraEstado;
    private javax.swing.JDesktopPane Escritorio;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Usuario;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}

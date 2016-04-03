package Interfaces;

import Controladores.MaterialesController;
import Pojo.Materiales;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author donaldo
 */
public class Calendario extends javax.swing.JInternalFrame {

    int idOrden;
    int idPrincipal;
    List<int[]> listaInventariosIniciales = new ArrayList<>();
    List<int[]> listaEntradas = new ArrayList<>();
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public Calendario() {
        initComponents();

    }

    Calendario(int idOrden) {
        initComponents();
        this.idOrden = idOrden;
        this.jTable1.setModel(dtm);
        dtm.addColumn("Material");
        dtm.addColumn("id");
        PanelEntrada.setBackground(Color.BLUE);
        PanelInventario.setBackground(Color.YELLOW);

        Cargar();
        CargarMateriales();
        CargarRequerimientos();
        AgregarEntrada();

        jTable1.setDefaultRenderer(Object.class, new MiRender());
        GenerarTablas();
        setViewColumn(0, false);
    }

    public void setViewColumn(int size, boolean view) {
        this.jTable1.getColumnModel().getColumn(1).setMaxWidth(size);
        this.jTable1.getColumnModel().getColumn(1).setMinWidth(size);
        this.jTable1.getColumnModel().getColumn(1).setPreferredWidth(size);
        this.jTable1.getColumnModel().getColumn(1).setResizable(view);
    }

    public void Cargar() {
        MaterialesController m = new MaterialesController();
        Object[] o = m.getOrdenById(idOrden);
        try {
            String dateString = o[3].toString();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date parsed = df.parse(dateString);

            Calendar newCalendar = Calendar.getInstance();
            newCalendar.setTime(parsed);

            Calendar actual = Calendar.getInstance();
            actual.setTime(new Date());

            for (int i = actual.get(Calendar.WEEK_OF_YEAR) - 1; i < newCalendar.get(Calendar.WEEK_OF_YEAR); i++) {
                dtm.addColumn(i);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Calendario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean ComprobarInsercionMaterial(String nombre) {
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (nombre.equals(dtm.getValueAt(i, 0))) {
                return true;
            }
        }

        return false;
    }

    public void CargarMateriales() {
        MaterialesController m = new MaterialesController();
        int idMaterialPrincipal = Integer.parseInt(m.getOrdenById(idOrden)[1].toString());
        Materiales material = m.getById(idMaterialPrincipal);
        dtm.addRow(new Object[]{material.getNombre_material(), idMaterialPrincipal, material.getCantidad_material()});
        this.listaInventariosIniciales.add(new int[]{dtm.getRowCount() - 1, 2});
        AgregarDependencia(idMaterialPrincipal);
        this.idPrincipal = idMaterialPrincipal;
    }

    public void CargarRequerimientos() {
        MaterialesController m = new MaterialesController();
        Object[] orden = m.getOrdenById(idOrden);

        Materiales material = m.getById(this.idPrincipal);
        int fila = getFilaMaterial(material.getNombre_material());
        int column = dtm.getColumnCount() - material.getTiempo_espera();

        if (column <= 2) {
            JOptionPane.showMessageDialog(null, "Error no hay suficiente espacio de tiempo para desarrollar todo el plan de produccion", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        dtm.setValueAt(orden[2], fila, column);
        AgregarRequerimientos(material.getId(), Integer.parseInt(orden[2].toString()), column);

    }

    public void AgregarRequerimientos(int idPadre, int CantidadPadre, int colPadre) {

        MaterialesController m = new MaterialesController();
        for (Object[] obj : m.getDependencias(idPadre)) {

            int fila = getFilaMaterial(obj[1].toString());
            int column = colPadre - Integer.parseInt(obj[6].toString());

            if (column <= 2) {
                JOptionPane.showMessageDialog(null, "Error no hay suficiente espacio de tiempo para desarrollar todo el plan de produccion", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int Cantidad = CantidadPadre * Integer.parseInt(obj[4].toString());

            Object o = dtm.getValueAt(fila, column);

            if (o == null) {
                dtm.setValueAt(Cantidad, fila, column);
            } else {
                dtm.setValueAt(Cantidad + Integer.parseInt(o.toString()), fila, column);
            }
            AgregarRequerimientos(Integer.parseInt(obj[3].toString()), Cantidad, column);
        }
    }

    public void AgregarDependencia(int idPadre) {
        MaterialesController m = new MaterialesController();
        for (Object[] obj : m.getDependencias(idPadre)) {
            if (!this.ComprobarInsercionMaterial(obj[1].toString())) {
                Materiales material = m.getById(Integer.parseInt(obj[3].toString()));
                dtm.addRow(new Object[]{obj[1], obj[3], material.getCantidad_material()});
                this.listaInventariosIniciales.add(new int[]{dtm.getRowCount() - 1, 2});
            }

            AgregarDependencia(Integer.parseInt(obj[3].toString()));
        }
    }

    public void AgregarEntrada() {
        MaterialesController m = new MaterialesController();
        for (Object[] obj : m.getEntradas(idOrden)) {
            try {

                String dateString = obj[2].toString();

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = df.parse(dateString);
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.setTime(parsed);

                int col = ColumnSemana(newCalendar.get(Calendar.WEEK_OF_YEAR));
                
                if(col <=2){
                    return;
                }
                int fila = getFilaMaterial(m.getById(Integer.parseInt(obj[1].toString())).getNombre_material());

                Object o = dtm.getValueAt(fila, col);

                if (o != null) {

                    String[] registro = o.toString().split(",");//registro de la forma entrada, requerimiento
                    if (registro.length == 1) {//comprobas si hay entrada y requerimiento en la celda
                        int cantidadAnterior = Integer.parseInt(o.toString());
                        if (this.isEntradaProgramada(fila, col)) {
                            dtm.setValueAt(cantidadAnterior + Integer.parseInt(obj[3].toString()), fila, col);
                        } else {
                            dtm.setValueAt(Integer.parseInt(obj[3].toString()) + "," + cantidadAnterior, fila, col);
                        }

                    } else {
                        dtm.setValueAt(Integer.parseInt(registro[0]) + Integer.parseInt(obj[3].toString()) + "," + registro[1], fila, col);
                    }
                } else {
                    dtm.setValueAt(obj[3], fila, col);
                }

                this.listaEntradas.add(new int[]{fila, col});
            } catch (ParseException ex) {
                Logger.getLogger(Calendario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void GenerarTablas() {
        for (int i = 0; i < dtm.getRowCount(); i++) {
            JPanel panel = new JPanel();
            JPanel panelBoton = new JPanel();
            JScrollPane scroll = new JScrollPane();
            JTable tabla = new JTable();
            JButton boton = new JButton("Generar reporte");

            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };

            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            FlowLayout f = new FlowLayout();
            f.setAlignment(FlowLayout.CENTER);
            panelBoton.setLayout(f);

            tabla.setModel(model);
            scroll.setViewportView(tabla);
            panel.add(scroll);

            panelBoton.add(boton);
            panel.add(panelBoton);
            this.jTabbedPane1.add(panel);
            this.jTabbedPane1.setTitleAt(i + 1, this.dtm.getValueAt(i, 0).toString());

            model.addColumn("Semana");
            model.addColumn("Inventario incial");
            model.addColumn("Necesidad");
            model.addColumn("Pedido");
            model.addColumn("Inventario final");

            for (int j = 2; j < this.dtm.getColumnCount(); j++) {
                MaterialesController mc = new MaterialesController();
                Materiales m = mc.getById(Integer.parseInt(dtm.getValueAt(i, 1).toString()));
                if (j == 2) {
                    model.addRow(new Object[]{dtm.getColumnName(j), m.getCantidad_material(), 0, 0, m.getCantidad_material()});
                } else {
                    int InventarioInicial = Integer.parseInt(model.getValueAt(j - 3, 4).toString());

                    Object nes = dtm.getValueAt(i, j);
                    int Necesidad = 0;

                    if (this.isEntradaProgramada(i, j)) {
                        String[] registro = nes.toString().split(",");

                        if (registro.length > 1) {
                            Necesidad = Integer.parseInt(registro[1]);
                        }
                    } else if (nes == null) {
                        Necesidad = 0;
                    } else {
                        Necesidad = Integer.parseInt(nes.toString());
                    }

                    int Pedido = Necesidad - InventarioInicial;
                    int InventarioFinal;
                    if (Pedido < 0) {
                        Pedido = 0;
                    } else if (m.getCantidad_lote() > 0) {
                        int multiplicidad = (int) Math.ceil((double) Pedido / (double) m.getCantidad_lote());

                        Pedido = multiplicidad * m.getCantidad_lote();
                    }

                    InventarioFinal = Pedido + InventarioInicial - Necesidad;

                    model.addRow(new Object[]{dtm.getColumnName(j), InventarioInicial, Necesidad, Pedido, InventarioFinal});
                }

            }

        }

    }

    public int ColumnSemana(int semana) {
        int col = dtm.getColumnCount();

        for (int i = 2; i < col; i++) {
            String name = dtm.getColumnName(i);
            if (semana == Integer.parseInt(name)) {
                return i;
            }
        }

        return 0;
    }

    public int getFilaMaterial(String nombre) {
        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (nombre.equals(dtm.getValueAt(i, 0))) {
                return i;
            }
        }
        return 0;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        PanelInventario = new javax.swing.JPanel();
        PanelEntrada = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.Y_AXIS));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTable2);

        jPanel3.add(jScrollPane2);

        jPanel4.setLayout(new java.awt.BorderLayout());

        jButton1.setText("El texto que yo quiera");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1, java.awt.BorderLayout.PAGE_END);

        jPanel3.add(jPanel4);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Calendario de materiales");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Leyenda"));

        PanelInventario.setBackground(new java.awt.Color(255, 194, 0));
        PanelInventario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelInventario.setToolTipText("Inventario inicial");

        javax.swing.GroupLayout PanelInventarioLayout = new javax.swing.GroupLayout(PanelInventario);
        PanelInventario.setLayout(PanelInventarioLayout);
        PanelInventarioLayout.setHorizontalGroup(
            PanelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        PanelInventarioLayout.setVerticalGroup(
            PanelInventarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        PanelEntrada.setBackground(new java.awt.Color(129, 41, 164));
        PanelEntrada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PanelEntrada.setToolTipText("Entradas programadas");

        javax.swing.GroupLayout PanelEntradaLayout = new javax.swing.GroupLayout(PanelEntrada);
        PanelEntrada.setLayout(PanelEntradaLayout);
        PanelEntradaLayout.setHorizontalGroup(
            PanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        PanelEntradaLayout.setVerticalGroup(
            PanelEntradaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 12, Short.MAX_VALUE)
        );

        jLabel1.setText("entrada, requerimiento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(PanelInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PanelEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(PanelEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Calendario principal", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public class MiRender extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (isInventarioInicial(row, column)) {
                this.setBackground(Color.YELLOW);
                this.setForeground(Color.BLACK);
            } else if (isEntradaProgramada(row, column)) {
                this.setBackground(Color.BLUE);
                this.setForeground(Color.white);
            } else if (isSelected) {
                this.setBackground(new Color(hex("0281d8")));

            } else {
                this.setBackground(Color.white);
                this.setForeground(Color.BLACK);
            }

            return cell;
        }
    }

    public boolean isInventarioInicial(int row, int column) {
        for (int[] pos : this.listaInventariosIniciales) {
            if (pos[0] == row && pos[1] == column) {
                return true;
            }
        }

        return false;
    }

    public boolean isEntradaProgramada(int row, int column) {
        for (int[] pos : this.listaEntradas) {
            if (pos[0] == row && pos[1] == column) {
                return true;
            }
        }

        return false;
    }

    public static int hex(String color_hex) {
        return Integer.parseInt(color_hex, 16);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelEntrada;
    private javax.swing.JPanel PanelInventario;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}

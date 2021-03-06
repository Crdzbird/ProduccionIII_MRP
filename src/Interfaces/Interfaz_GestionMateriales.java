package Interfaces;

import Controladores.MaterialesController;
import Implementaciones.GhostText;
import Pojo.Materiales;
import Validaciones.Metodos;
import static Validaciones.Metodos.limpiarTabla;
import Validaciones.Validar;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import static javax.swing.ListSelectionModel.SINGLE_SELECTION;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author crdzbird
 */
public class Interfaz_GestionMateriales extends javax.swing.JInternalFrame {

    DefaultTableModel dtm;
    DefaultTableModel datos = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    boolean Actualizar = false;
    int CantMaterialesActualizar = 0;
    int idActualizar = -1;

    public Interfaz_GestionMateriales() {
        initComponents();
        this.txtTiempoEspera.setTransferHandler(null);
        this.txtCantidadEstimada.setTransferHandler(null);
        this.txtTiempoEspera.setTransferHandler(null);
        this.txtNombreMaterial.setTransferHandler(null);
        new GhostText(txtTiempoEspera, "Numero de...");
        dtm = (DefaultTableModel) this.DependenciasTable.getModel();
        this.DependenciasTable.setModel(dtm);

        datos.addColumn("Id_material");
        datos.addColumn("Nombre Material");
        datos.addColumn("Cantidad inventario");
        datos.addColumn("Tamaños de lotes");
        datos.addColumn("Tiempo espera");
        datos.addColumn("Perido espera");
        datos.addColumn("Estado");
        this.jTable1.setModel(datos);

        //cargar columna
        JComboBox comboBox = new JComboBox(new Object[]{"Activo", "Inactivo"});
        DefaultCellEditor defaultCellEditor = new DefaultCellEditor(comboBox);
        this.DependenciasTable.getColumnModel().getColumn(3).setCellEditor(defaultCellEditor);

        setViewColumn(0, false);
        CargarMateriales();
        
        this.jTable1.setSelectionMode(SINGLE_SELECTION);
        this.DependenciasTable.setSelectionMode(SINGLE_SELECTION);
        
        this.txtCantidadEstimada.setTransferHandler(null);
        this.txtInventarioInicial.setTransferHandler(null);
        this.txtTiempoEspera.setTransferHandler(null);

    }

    public void setViewColumn(int size, boolean view) {
        this.DependenciasTable.getColumnModel().getColumn(3).setMaxWidth(size);
        this.DependenciasTable.getColumnModel().getColumn(3).setMinWidth(size);
        this.DependenciasTable.getColumnModel().getColumn(3).setPreferredWidth(size);
        this.DependenciasTable.getColumnModel().getColumn(3).setResizable(view);
    }

    public void CargarMateriales() {
        limpiarTabla(datos);
        MaterialesController mc = new MaterialesController();
        for (Materiales m : mc.getAll()) {
            datos.addRow(new Object[]{m.getId(), m.getNombre_material(), m.getCantidad_material(),
                m.getCantidad_lote(), m.getTiempo_espera(), m.getTipo_espera(), m.isEstado() ? "Activo" : "Inactivo"});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel14 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNombreMaterial = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        txtTiempoEspera = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        panelSlider = new javax.swing.JPanel();
        cmbEstadoMaterial = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        btnRegistrar = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtCantidadEstimada = new javax.swing.JTextField();
        chkUtilizaLote = new javax.swing.JCheckBox();
        btnCancelar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtInventarioInicial = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DependenciasTable = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        cmbBuscarParametros3 = new javax.swing.JComboBox();
        jLabel34 = new javax.swing.JLabel();
        cmbMostrarTodo3 = new javax.swing.JComboBox();
        jButton8 = new javax.swing.JButton();
        txtBusqueda3 = new javax.swing.JTextField();
        btnEditar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnReporte = new javax.swing.JButton();
        btnReporte1 = new javax.swing.JButton();

        jPanel14.setBackground(new java.awt.Color(102, 0, 255));
        jPanel14.setLayout(new javax.swing.BoxLayout(jPanel14, javax.swing.BoxLayout.Y_AXIS));

        jCheckBox1.setText("jCheckBox1");
        jPanel14.add(jCheckBox1);

        jPanel7.setBackground(new java.awt.Color(153, 255, 51));
        jPanel7.setLayout(new javax.swing.BoxLayout(jPanel7, javax.swing.BoxLayout.Y_AXIS));

        jLabel4.setText("jLabel4");
        jPanel7.add(jLabel4);

        jPanel15.setBackground(new java.awt.Color(102, 102, 102));
        jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.Y_AXIS));

        jRadioButton6.setText("jRadioButton6");
        jPanel15.add(jRadioButton6);

        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.Y_AXIS));

        jSplitPane1.setDividerLocation(600);
        jSplitPane1.setDividerSize(5);

        jLabel1.setText("Nombre del Material:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Tiempo de Espera"));

        txtTiempoEspera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoEsperaActionPerformed(evt);
            }
        });
        txtTiempoEspera.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTiempoEsperaKeyTyped(evt);
            }
        });

        jLabel2.setText("semanas");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txtTiempoEspera, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTiempoEspera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        cmbEstadoMaterial.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Disponible", "No Disponible" }));
        cmbEstadoMaterial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEstadoMaterialActionPerformed(evt);
            }
        });

        jLabel3.setText("Estado Material:");

        javax.swing.GroupLayout panelSliderLayout = new javax.swing.GroupLayout(panelSlider);
        panelSlider.setLayout(panelSliderLayout);
        panelSliderLayout.setHorizontalGroup(
            panelSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSliderLayout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEstadoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panelSliderLayout.setVerticalGroup(
            panelSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSliderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3)
                .addComponent(cmbEstadoMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        btnRegistrar.setText("Guardar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Asignacion de Tamaño"));

        jLabel5.setText("Cantidad Estimada:");

        txtCantidadEstimada.setEnabled(false);
        txtCantidadEstimada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadEstimadaKeyTyped(evt);
            }
        });

        chkUtilizaLote.setText("Utiliza Lote");
        chkUtilizaLote.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                chkUtilizaLoteStateChanged(evt);
            }
        });
        chkUtilizaLote.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkUtilizaLoteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCantidadEstimada, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkUtilizaLote)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(chkUtilizaLote)
                    .addComponent(txtCantidadEstimada, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jLabel6.setText("Inventario inicial");

        txtInventarioInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInventarioInicialKeyTyped(evt);
            }
        });

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Composicion"));

        DependenciasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id_Material", "Nombre Material", "Cantidad", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DependenciasTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(DependenciasTable);

        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Remover");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreMaterial, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                            .addComponent(txtInventarioInicial)))
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombreMaterial, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtInventarioInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(panelSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jSplitPane1.setLeftComponent(jPanel3);

        jButton1.setText("Visualizar Estructura de Materiales");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel8.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(jPanel4);

        jPanel1.add(jSplitPane1);

        jTabbedPane1.addTab("Administracion_Materiales", jPanel1);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.Y_AXIS));

        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel33.setText("Buscar por:");

        cmbBuscarParametros3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Id", "Nombre Material", "Material/Dependiente", "Material/Independiente" }));

        jLabel34.setText("Tipo de Busqueda:");

        cmbMostrarTodo3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mostrar Todo", "Mostrar Activos", "Mostrar Inactivos" }));

        jButton8.setText("Aceptar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        txtBusqueda3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBusqueda3KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMostrarTodo3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbBuscarParametros3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBusqueda3, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel34)
                    .addComponent(cmbMostrarTodo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jLabel33)
                    .addComponent(cmbBuscarParametros3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusqueda3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditar.setText("Actualizar Material");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jTable1MouseMoved(evt);
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        btnReporte.setText("Generar Reporte Material");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        btnReporte1.setText("Generar Arbol Material");
        btnReporte1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporte1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnReporte1)
                        .addGap(58, 58, 58)
                        .addComponent(btnReporte)
                        .addGap(56, 56, 56)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReporte1, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(btnReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel12);

        jTabbedPane1.addTab("Busqueda Avanzada", jPanel2);

        getContentPane().add(jTabbedPane1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txtBusqueda3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBusqueda3KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBusqueda3KeyReleased

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int fila = this.jTable1.getSelectedRow();

        if (fila == -1) {
            JOptionPane.showInternalMessageDialog(this, "Seleccione unmaterial primero porfavor", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        MaterialesController mc = new MaterialesController();
        Materiales m = mc.getById(Integer.parseInt(this.jTable1.getValueAt(fila, 0).toString()));

        this.jTabbedPane1.setSelectedIndex(0);

        this.txtNombreMaterial.setText(m.getNombre_material());
        this.txtInventarioInicial.setText(String.valueOf(m.getCantidad_material()));
        this.txtTiempoEspera.setText(String.valueOf(m.getTiempo_espera()));

        this.txtCantidadEstimada.setText(String.valueOf(m.getCantidad_lote()));

        for (Object[] obj : mc.getDependencias(m.getId())) {
            this.CantMaterialesActualizar++;
            dtm.addRow(new Object[]{obj[3], obj[1], obj[4], (boolean) obj[5] ? "Activo" : "Inactivo"});
        }

        if (m.isEstado()) {
            this.cmbEstadoMaterial.setSelectedIndex(0);
        } else {
            this.cmbEstadoMaterial.setSelectedIndex(1);
        }

        this.Actualizar = true;
        this.idActualizar = m.getId();
        this.setViewColumn(100, true);
    }//GEN-LAST:event_btnEditarActionPerformed

    private void jTable1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseMoved
        //        // TODO add your handling code here:
        //        this.Id = Integer.parseInt(dtm.getValueAt(nf, 0).toString());

    }//GEN-LAST:event_jTable1MouseMoved

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        this.btnEditar.setEnabled(true);
        this.btnReporte.setEnabled(true);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_btnReporteActionPerformed

    private void btnReporte1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporte1ActionPerformed

        int filas = this.jTable1.getSelectedRow();

        if (filas == -1) {
            JOptionPane.showInternalMessageDialog(this, "Seleccione un material primero porfavor", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int id = Integer.parseInt(this.jTable1.getValueAt(filas, 0).toString());

        Arbol_dependencia arbol = new Arbol_dependencia(id);
        this.getDesktopPane().add(arbol);
        arbol.setVisible(true);
    }//GEN-LAST:event_btnReporte1ActionPerformed

    public String RadioButtonSeleccionado(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return null;
    }

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        // slider.slideRight();
        Materiales materiales = new Materiales();
        boolean guardado;

        if (txtTiempoEspera.getText().isEmpty() || txtNombreMaterial.getText().isEmpty() || txtInventarioInicial.getText().isEmpty()) {
            JOptionPane.showInternalMessageDialog(this, "Porfavor no dejar campos vacios", "Notificacion del Sistema", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (Validar.isInt(txtInventarioInicial.getText())) {
            materiales.setCantidad_material(Integer.parseInt(txtInventarioInicial.getText()));
            
        } else {
            JOptionPane.showInternalMessageDialog(this, "Cantidad invalida en inventario incial", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (chkUtilizaLote.isSelected()) {
            if (txtCantidadEstimada.getText().isEmpty()) {
                JOptionPane.showInternalMessageDialog(this, "Porfavor digite la cantidad estimada de lote", "Notificacion del Sistema", JOptionPane.INFORMATION_MESSAGE);
                return;
            } else {
                materiales.setCantidad_lote(Integer.parseInt(txtCantidadEstimada.getText()));
            }
        } else {
            materiales.setCantidad_lote(0);
        }

        if (cmbEstadoMaterial.getSelectedIndex() == 0) {
            materiales.setEstado(true);
        } else {
            materiales.setEstado(false);
        }

        materiales.setNombre_material(txtNombreMaterial.getText());
        materiales.setTiempo_espera(Integer.parseInt(txtTiempoEspera.getText()));

        if (!this.ComposicionValida()) {
            JOptionPane.showInternalMessageDialog(this, "Error en la tabla de composicion, \n"
                    + "porfavor asegurese que en la columna de de Cantidad este correctamente digitada(numeros mayores a cero)");

            return;
        }

        materiales.setTipo_espera("Semanas");
        MaterialesController m = new MaterialesController();
        if (!Actualizar) {
            int id = m.RegistrarMaterial(materiales);
            if (id > 0) {
                List<Object[]> lista = new ArrayList<>();
                for (int i = 0; i < dtm.getRowCount(); i++) {
                    lista.add(new Object[]{dtm.getValueAt(i, 0), dtm.getValueAt(i, 2)});
                }

                guardado = m.RegistrarComposicion(lista, id);
                if (guardado) {
                    this.chkUtilizaLote.setSelected(false);
                    this.txtNombreMaterial.setText(null);
                    this.txtTiempoEspera.setText(null);
                    this.cmbEstadoMaterial.setSelectedIndex(0);
                    this.txtInventarioInicial.setText(null);

                    limpiarTabla(dtm);
                    setViewColumn(0, false);
                    this.CargarMateriales();
                    JOptionPane.showInternalMessageDialog(this, "Guardado exitosamente", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showInternalMessageDialog(this, "Error al guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            materiales.setId(this.idActualizar);
            boolean actualizado = m.ActualizarMaterial(materiales);
            if (actualizado) {

                List<Object[]> lista = new ArrayList<>();
                for (int i = this.CantMaterialesActualizar; i < dtm.getRowCount(); i++) {
                    lista.add(new Object[]{dtm.getValueAt(i, 0), dtm.getValueAt(i, 2)});
                }

                guardado = m.RegistrarComposicion(lista, materiales.getId());

                for (int i = 0; i < this.CantMaterialesActualizar; i++) {
                    int iddep = Integer.parseInt(dtm.getValueAt(i, 0).toString());
                    boolean a = m.ActualizarDependencia(materiales.getId(), iddep,
                            Integer.parseInt(dtm.getValueAt(i, 2).toString()),
                            dtm.getValueAt(i, 3).toString().equals("Activo"));

                    if (!a) {
                        System.out.println("no se pudo guardar");
                    }
                }

                this.chkUtilizaLote.setSelected(false);
                this.txtNombreMaterial.setText(null);
                this.txtTiempoEspera.setText(null);
                this.cmbEstadoMaterial.setSelectedIndex(0);
                this.txtInventarioInicial.setText(null);
                idActualizar = -1;
                this.CantMaterialesActualizar = 0;
                this.idActualizar = -1;
                Metodos.limpiarTabla(dtm);
                this.setViewColumn(0, false);
                this.CargarMateriales();
                JOptionPane.showInternalMessageDialog(this, "Actualizado exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);

            }

        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    public boolean ComposicionValida() {
        DefaultTableModel dtm = (DefaultTableModel) this.DependenciasTable.getModel();

        for (int i = 0; i < dtm.getRowCount(); i++) {
            if (dtm.getValueAt(i, 2) == null) {
                return false;
            }
            if (!Validar.isInt(dtm.getValueAt(i, 2).toString())) {

                return false;
            } else if (Integer.parseInt(dtm.getValueAt(i, 2).toString()) <= 0) {
                return false;
            }
        }
        return true;
    }

    private void txtTiempoEsperaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTiempoEsperaKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtTiempoEsperaKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkUtilizaLoteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkUtilizaLoteActionPerformed

    }//GEN-LAST:event_chkUtilizaLoteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

    }//GEN-LAST:event_jButton1ActionPerformed

    private void chkUtilizaLoteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_chkUtilizaLoteStateChanged
        if (chkUtilizaLote.isSelected()) {
            this.txtCantidadEstimada.setEnabled(true);
        } else {
            this.txtCantidadEstimada.setEnabled(false);
        }
    }//GEN-LAST:event_chkUtilizaLoteStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Buscar_Material b = new Buscar_Material(this);
        this.getDesktopPane().add(b);
        b.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmbEstadoMaterialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEstadoMaterialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEstadoMaterialActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int fila = this.DependenciasTable.getSelectedRow();

        if (fila == -1) {
            return;
        }

        if (fila + 1 > this.CantMaterialesActualizar) {
            dtm.removeRow(fila);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTiempoEsperaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoEsperaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoEsperaActionPerformed

    private void txtInventarioInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInventarioInicialKeyTyped
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtInventarioInicialKeyTyped

    private void txtCantidadEstimadaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadEstimadaKeyTyped
        char c = evt.getKeyChar();
        
        if(!Character.isDigit(c)){
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadEstimadaKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTable DependenciasTable;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnReporte1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox chkUtilizaLote;
    private javax.swing.JComboBox cmbBuscarParametros3;
    private javax.swing.JComboBox cmbEstadoMaterial;
    private javax.swing.JComboBox cmbMostrarTodo3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelSlider;
    private javax.swing.JTextField txtBusqueda3;
    private javax.swing.JTextField txtCantidadEstimada;
    private javax.swing.JTextField txtInventarioInicial;
    private javax.swing.JTextField txtNombreMaterial;
    private javax.swing.JTextField txtTiempoEspera;
    // End of variables declaration//GEN-END:variables
}

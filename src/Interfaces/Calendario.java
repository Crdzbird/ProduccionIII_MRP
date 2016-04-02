package Interfaces;

import Controladores.MaterialesController;
import Pojo.Materiales;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author donaldo
 */
public class Calendario extends javax.swing.JInternalFrame {

    int idOrden;
    int idPrincipal;
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
        Cargar();
        CargarMateriales();
        AgregarEntrada();
        CargarRequerimientos();
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
        dtm.addRow(new Object[]{m.getById(idMaterialPrincipal).getNombre_material()});
        AgregarDependencia(idMaterialPrincipal);
        this.idPrincipal = idMaterialPrincipal;
    }

    public void CargarRequerimientos(){
        MaterialesController m = new MaterialesController();
        Object [] orden = m.getOrdenById(idOrden);
        
        Materiales material = m.getById(this.idPrincipal);
        int fila =getFilaMaterial(material.getNombre_material());
        int column = dtm.getColumnCount() - material.getTiempo_espera();
        
        dtm.setValueAt(orden[2], fila, column);
        AgregarRequerimientos(material.getId(),Integer.parseInt(orden[2].toString()) , column);
    
    }
    
    public void AgregarRequerimientos(int idPadre, int CantidadPadre, int colPadre) {
        
        MaterialesController m = new MaterialesController();
        for (Object[] obj : m.getDependencias(idPadre)) {
            
            int fila =getFilaMaterial(obj[1].toString());
            int column = colPadre - Integer.parseInt(obj[6].toString()) -1 ;
            
            int Cantidad = CantidadPadre * Integer.parseInt(obj[4].toString());
            
            Object o = dtm.getValueAt(fila, column);
            
            if(o==null){
                dtm.setValueAt(Cantidad, fila, column);
            }
            else{
                dtm.setValueAt(Cantidad + Integer.parseInt(o.toString()), fila, column);
            }
            AgregarRequerimientos(Integer.parseInt(obj[3].toString()), Cantidad, column);
        }
    }

    public void AgregarDependencia(int idPadre) {
        MaterialesController m = new MaterialesController();
        for (Object[] obj : m.getDependencias(idPadre)) {
            if (!this.ComprobarInsercionMaterial(obj[1].toString())) {
                dtm.addRow(new Object[]{obj[1]});
            }
            AgregarDependencia(Integer.parseInt(obj[3].toString()));
        }
    }
    

    public void AgregarEntrada() {
        MaterialesController m = new MaterialesController();
        for (Object[] obj : m.getEntradas(idOrden)) {
            try {
                //            int column = dtm.getColumnCount() - Integer.parseInt(obj[6].toString());
//
//            int fila =getFilaMaterial(obj[1].toString());
//            
//            Object o = dtm.getValueAt(fila, column);

                String dateString = obj[2].toString();

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Date parsed = df.parse(dateString);
                Calendar newCalendar = Calendar.getInstance();
                newCalendar.setTime(parsed);
                
                int col = ColumnSemana(newCalendar.get(Calendar.WEEK_OF_YEAR));
                int fila =getFilaMaterial(m.getById(Integer.parseInt(obj[1].toString())).getNombre_material());
                
                Object o = dtm.getValueAt(fila, col);
                
                if(o!=null){
                    int cantidadAnterior = Integer.parseInt(o.toString());
                    dtm.setValueAt(cantidadAnterior + Integer.parseInt(obj[3].toString()), fila, col);
                }
                else{
                    dtm.setValueAt(obj[3], fila, col);
                }
                
                
            } catch (ParseException ex) {
                Logger.getLogger(Calendario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
    
    public int ColumnSemana(int semana){
        int col = dtm.getColumnCount();
        
        for(int i = 1 ; i< col ;i++){
            String name = dtm.getColumnName(i);
            if(semana  == Integer.parseInt(name)){
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 316, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

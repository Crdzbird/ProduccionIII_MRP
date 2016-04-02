/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

/**
 *
 * @author donaldo
 */
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 * @author leyer
 */
public class JComboBoxTable extends JFrame{
    private static final long serialVersionUID = 1L;
    protected JTable table = null;
    private JScrollPane scrollPane=null;
    private class TABLE_MODEL extends DefaultTableModel{
        public static final String INITIAL_VALUE = "DATO 1";
        private static final long serialVersionUID = 1L;
        public TABLE_MODEL(){
            this.addColumn(COLUMN1);
            this.addColumn(COLUMN2);
            this.addColumn(COLUMN3);
        }
        public static final String COLUMN1="A";
        public static final String COLUMN2="B";
        public static final String COLUMN3="C";
        public void addData(String a,String b){
            final Object rowData[]={a,b,INITIAL_VALUE};
            this.addRow(rowData);
        }
    }
    private TABLE_MODEL model=new TABLE_MODEL();
    public static final String[] DATA = { "Dato 1", "Dato 2", "Dato 3", "Dato 4" };
    public JComboBoxTable(){
        JComboBox comboBox = new JComboBox(DATA);
        DefaultCellEditor defaultCellEditor=new DefaultCellEditor(comboBox);
        table=new JTable(model);
        table.getColumnModel().getColumn(2).setCellEditor(defaultCellEditor);
        scrollPane=new JScrollPane(table);
        this.getContentPane().add(scrollPane);
        this.setSize(500,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        addData();
    }
    public void addData(){
        model.addData("valor 1", "valor 2");
        model.addData("valor 3", "valor 4");
        model.addData("valor 5", "valor 6");
    }
    public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
        JComboBoxTable j = new JComboBoxTable();
        j.setVisible(true);
        
            System.out.println("valor :" + j.table.getValueAt(0, 2));
            }
        });
    }
}
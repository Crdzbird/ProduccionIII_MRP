/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementaciones;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author crdzbird
 */
public class TablaRenderColor extends DefaultTableCellRenderer{
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column){
        
//        if (table.getValueAt(row, 1).toString().startsWith("Ocupado")) {
//            setBackground(Color.red);
//            setForeground(Color.WHITE);
//        }else{
//            setBackground(Color.yellow);
//            setForeground(Color.BLACK);
//        }
        if(row%2==0){
            setBackground(Color.WHITE);
        }
        else{
            setBackground(Color.lightGray);
        }
        super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        return this;
    }
}

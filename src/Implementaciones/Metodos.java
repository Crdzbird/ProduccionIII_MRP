package Implementaciones;

import java.beans.PropertyVetoException;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author crdzbird
 */
public class Metodos {

    public static void Centrar(JInternalFrame ia, JDesktopPane Escritorio) {
        int x = (Escritorio.getWidth() / 2) - ia.getWidth() / 2;
        int y = (Escritorio.getHeight() / 2) - ia.getHeight() / 2;
        Escritorio.add(ia);
        ia.setLocation(x, y);
        ia.setVisible(true);
    }
    
    public static void Maximizar(JInternalFrame ia) throws PropertyVetoException{
        ia.setMaximum(true);
    }

    public static void LimpiarTabla(DefaultTableModel dtm) {
        for (int i = dtm.getRowCount() - 1; i >= 0; i--) {
            dtm.removeRow(i);
        }

    }

    public static double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;

    }
}

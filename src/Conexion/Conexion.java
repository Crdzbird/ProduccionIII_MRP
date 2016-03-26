/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import Pojo.Usuario_Conectado;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author crdzbird
 */
public class Conexion {

    static String url = "jdbc:mysql://localhost:3306/SistemaMRP";
    Connection connection;
    
    public Connection Coneccion() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url,Usuario_Conectado.getUser(),Usuario_Conectado.getPass());
            return connection;
        } catch (ClassNotFoundException | SQLException io) {
            JOptionPane.showMessageDialog(null, "Error al realizar coneccion a base de datos", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void CerrarConexion(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha sido imposible cerrar la conexion", "Error de Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}

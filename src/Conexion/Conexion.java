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

    static String url = "jdbc:mysql://localhost/SistemaMRP," + Usuario_Conectado.getUser() + "," + Usuario_Conectado.getPass() + ";";
    
    public Connection Coneccion() {
        Connection connection;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            return null;
        }
        try {
            connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException io) {
            JOptionPane.showMessageDialog(null, "Error al realizar coneccion a base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}

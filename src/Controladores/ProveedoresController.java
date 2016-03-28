/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.Conexion;
import Pojo.Materiales;
import Pojo.Proveedor;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crdzbird
 */
public class ProveedoresController {

    public ProveedoresController() {
    }
    
    public int RegistrarProveedor(Proveedor proveedores) {

        try {
            Connection con = new Conexion().Coneccion();
            CallableStatement comando = con.prepareCall("{call Guardar_Proveedor(?,?,?,?,?,?,?)};");
            comando.setString("nombre_proveedor", proveedores.getNombre());
            comando.setString("apellido_proveedor", proveedores.getApellido());
            comando.setString("direccion", proveedores.getDireccion());
            comando.setString("telefono", proveedores.getTelefono());
            comando.setString("correo_electronico", proveedores.getCorreo());
            comando.setBoolean("estado_proveedor", proveedores.isEstado());

            comando.registerOutParameter(7, java.sql.Types.INTEGER);

            comando.execute();

            int id = comando.getInt(7);

            comando.close();
            con.close();
            return id;
        } catch (Exception ex) {
            return -1;
        }
    }
    
    public List<Proveedor> getAll() {
        Connection con = new Conexion().Coneccion();
        List<Proveedor> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Proveedor");

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setCorreo(rs.getString(6));
                p.setEstado(rs.getBoolean(7));
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public Proveedor getById(int Id) {
        Connection con = new Conexion().Coneccion();
       
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Proveedor where id_proveedor = "+Id);

            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setTelefono(rs.getString(5));
                p.setCorreo(rs.getString(6));
                p.setEstado(rs.getBoolean(7));
                return p;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }
    
}

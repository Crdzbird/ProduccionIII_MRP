/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.Conexion;
import Pojo.Envio_Materiales;
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
public class EnvioMaterialesController {

    public EnvioMaterialesController() {
    }
    
    public int RegistrarEnvioMateriales(Envio_Materiales envio_material) {
        
        try {
            Connection con = new Conexion().Coneccion();
            CallableStatement comando = con.prepareCall("{call Guardar_Envio(?,?,?,?,?,?)};");
            comando.setInt("id_proveedor", envio_material.getId_proveedor());
            comando.setInt("id_material", envio_material.getId_material());
            comando.setDate("fecha_entrega", envio_material.getFecha_entrega());
            comando.setDate("fecha_solicitud", envio_material.getFecha_solicitud());
            comando.setInt("cantidad_solicitada", envio_material.getCantidad_solicitada());

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
        
    public List<Envio_Materiales> getAll() {
        Connection con = new Conexion().Coneccion();
        List<Envio_Materiales> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Estado_Envio");

            while (rs.next()) {
                Envio_Materiales p = new Envio_Materiales();
                p.setId_envio(rs.getInt(1));
                p.setId_proveedor(rs.getInt(2));
                p.setId_material(rs.getInt(3));
                p.setFecha_entrega(rs.getDate(4));
                p.setFecha_solicitud(rs.getDate(5));
                p.setCantidad_solicitada(rs.getInt(6));
                lista.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public Envio_Materiales getByFechaEntrega(String fecha_entrega) {
        Connection con = new Conexion().Coneccion();
       
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Estado_Envio where fecha_entrega = "+fecha_entrega);

            while (rs.next()) {
                Envio_Materiales p = new Envio_Materiales();
                p.setId_envio(rs.getInt(1));
                p.setId_proveedor(rs.getInt(2));
                p.setId_material(rs.getInt(3));
                p.setFecha_entrega(rs.getDate(4));
                p.setFecha_solicitud(rs.getDate(5));
                p.setCantidad_solicitada(rs.getInt(6));
                return p;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }
}

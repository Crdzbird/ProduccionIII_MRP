/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Conexion.Conexion;
import Pojo.Materiales;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author donaldo
 */
public class MaterialesController {

    public MaterialesController() {

    }

    public int RegistrarMaterial(Materiales materiales) {

        try {
            Connection con = new Conexion().Coneccion();
            CallableStatement comando = con.prepareCall("{call Guardar_Material(?,?,?,?,?,?,?)};");
            comando.setString("nombre_material", materiales.getNombre_material());
            comando.setInt("tiempo_espera", materiales.getTiempo_espera());
            comando.setString("tipo_espera", materiales.getTipo_espera());
            comando.setInt("cantidad_lote", materiales.getCantidad_lote());
            comando.setInt("cantidad_material", materiales.getCantidad_material());
            comando.setBoolean("estado_material", materiales.isEstado());

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

    public boolean RegistrarComposicion(List<Object[]> list, int IdPrincipal) {

        try {
            Connection con = new Conexion().Coneccion();

            for (Object[] o : list) {
                String sql = String.format("insert into Materiales_Dependencias "
                        + "(id_material_principal,id_material_dependencia,cantidad_material_dependencia, estado_material_principal) "
                        + "values (%s,%s,%s,%s)", IdPrincipal, o[0], o[1], true);

                System.out.println(""+sql);
                Statement comando = con.createStatement();
                int filas = comando.executeUpdate(sql);

                if (filas == 0) {
                    return false;
                }
                comando.close();

            }

            con.close();
            return true;
        } catch (Exception ex) {
            System.out.println(""+ex);
            return false;
        }
    }

    public List<Materiales> getAll() {
        Connection con = new Conexion().Coneccion();
        List<Materiales> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Materiales");

            while (rs.next()) {
                Materiales m = new Materiales();
                m.setId(rs.getInt(1));
                m.setNombre_material(rs.getString(2));
                m.setTiempo_espera(rs.getInt(3));
                m.setTipo_espera(rs.getString(4));
                m.setCantidad_lote(rs.getInt(5));
                m.setCantidad_material(rs.getInt(6));
                m.setEstado(rs.getBoolean(7));
                lista.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    public Materiales getById(int Id) {
        Connection con = new Conexion().Coneccion();
       
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from Materiales where Id_Material = "+Id);

            while (rs.next()) {
                Materiales m = new Materiales();
                m.setId(rs.getInt(1));
                m.setNombre_material(rs.getString(2));
                m.setTiempo_espera(rs.getInt(3));
                m.setTipo_espera(rs.getString(4));
                m.setCantidad_lote(rs.getInt(5));
                m.setCantidad_material(rs.getInt(6));
                m.setEstado(rs.getBoolean(7));
                return m;
            }
        } catch (SQLException ex) {
            return null;
        }
        return null;
    }
    
    public List<Object[]> getDependencias(int id_principal) {
        Connection con = new Conexion().Coneccion();
        List<Object[]> lista = new ArrayList<>();
        try {
            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery("select * from VistaComposicion where id_principal = "+id_principal);

            while (rs.next()) {
                Object [] obj = new Object[5];
                
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
                lista.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
    
    
    
    
}
